package com.wxsl.rosalind.zk.leader;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * @author wxsl1997
 */
@Slf4j
public class Master implements Watcher, AutoCloseable {

    private static final Object CONTEXT = new Object();
    private static final String MASTER_PATH = "/master";
    private static final String WORKERS_PATH = "/workers";
    private static final String TASKS_PATH = "/tasks";
    private static final String ASSIGN_PATH = "/assign";
    private static final String STATUS_PATH = "/status";
    protected ChildrenCache tasksCache;
    protected ChildrenCache workersCache;

    @Getter
    private volatile boolean connected = false;

    @Getter
    private volatile boolean expired = false;

    private volatile MasterStates state = MasterStates.NOT_ELECTED;

    private ZooKeeper zk;

    private final String address;
    private final String serverId;

    public Master(String address, String serverId) {
        this.address = address;
        this.serverId = serverId;
    }

    @SneakyThrows
    public void startZK() {
        zk = new ZooKeeper(address, 15_000, this);
    }

    /**
     * 竞选主节点
     */
    public void runForMaster() {
        byte[] data = serverId.getBytes(StandardCharsets.UTF_8);
        AsyncCallback.StringCallback callback = (rc, path, ctx, name) -> {
            KeeperException.Code code = KeeperException.Code.get(rc);
            switch (code) {
                case CONNECTIONLOSS: {
                    // 网络问题 检查选取操作是否生效
                    deduceRunForResult();
                    break;
                }
                case OK: {
                    // 竞选成功 执行主节点逻辑
                    state = MasterStates.ELECTED;

                    takeLeadership();
                    break;
                }
                case NODEEXISTS: {
                    state = MasterStates.NOT_ELECTED;
                    // 创建失败 注册监视点
                    registerWatcherForMasterPath();
                    break;
                }
                default: {
                    // 其他情况 竞选失败
                    state = MasterStates.NOT_ELECTED;
                    KeeperException exception = KeeperException.create(KeeperException.Code.get(rc), path);
                    log.error("something went wrong when running for master", exception);
                }
            }
        };
        zk.create(MASTER_PATH, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, callback, new Object());
    }

    public void bootstrap() {
        createZkNode(WORKERS_PATH, new byte[0]);
        createZkNode(ASSIGN_PATH, new byte[0]);
        createZkNode(TASKS_PATH, new byte[0]);
        createZkNode(STATUS_PATH, new byte[0]);
    }

    private void createZkNode(String nodePath, byte[] data) {
        AsyncCallback.StringCallback callback = (rc, patch, ctx, name) -> {
            KeeperException.Code code = KeeperException.Code.get(rc);
            switch (code) {
                case CONNECTIONLOSS: {
                    createZkNode(patch, (byte[]) ctx);
                    break;
                }
                case OK: {
                    log.info("parent created");
                    break;
                }
                case NODEEXISTS: {
                    log.info("parent already registered:{}", patch);
                    break;
                }
                default: {
                    log.error("something went wrong", KeeperException.create(code, patch));
                }
            }
        };
        zk.create(nodePath, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, callback, data);
    }

    private void takeLeadership() {
        getWorkers();

        getTasks();
    }

    private void getWorkers() {

        Watcher watcher = e -> {
            if (e.getType() == Event.EventType.NodeChildrenChanged) {
                assert WORKERS_PATH.equals(e.getPath());
                getWorkers();
            }
        };

        AsyncCallback.ChildrenCallback callback = (rc, path, ctx, children) -> {

            KeeperException.Code code = KeeperException.Code.get(rc);
            switch (code) {
                case CONNECTIONLOSS: {
                    getWorkers();
                    break;
                }
                case OK: {
                    log.info("successfully got a list of workers:{} workers", children.size());

                    if (workersCache == null) {
                        // 初始化 worker 缓存列表
                        workersCache = new ChildrenCache(children);
                    } else {
                        // 获取已经失效的 worker 列表, 并更新缓存数据
                        List<String> absentWorkers = workersCache.getAbsentCacheAndRefreshCache(children);
                        absentWorkers.forEach(this::reassignTaskForAbsentWorker);
                    }
                    break;
                }
                default: {
                    KeeperException exception = KeeperException.create(code, path);
                    log.error("get workers failed", exception);
                }
            }
        };

        zk.getChildren(WORKERS_PATH, watcher, callback, CONTEXT);
    }


    private void getTasks() {

        Watcher watcher = e -> {
            if (e.getType() == Event.EventType.NodeChildrenChanged) {
                assert Objects.equals(TASKS_PATH, e.getPath());
                getTasks();
            }
        };

        AsyncCallback.ChildrenCallback callback = (rc, path, ctx, children) -> {
            KeeperException.Code code = KeeperException.Code.get(rc);
            switch (code) {
                case CONNECTIONLOSS: {
                    getTasks();
                    break;
                }
                case OK: {
                    if (tasksCache == null) {
                        tasksCache = new ChildrenCache(Lists.newArrayList());
                    }
                    List<String> newTasks = tasksCache.getNewAndRefreshCache(children);
                    // 新任务,执行分配逻辑
                    newTasks.forEach(this::assignTask);
                    break;
                }
                default: {
                    KeeperException exception = KeeperException.create(code, path);
                    log.error("get tasks failed", exception);
                }
            }
        };

        zk.getChildren(TASKS_PATH, watcher, callback, CONTEXT);
    }


    private void assignTask(String task) {
        AsyncCallback.DataCallback callback = (rc, path, ctx, data, stat) -> {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:
                    assignTask((String) ctx);
                    break;
                case OK: {
                    // 随机分配任务
                    List<String> cachedWorkers = workersCache.getCache();
                    String designatedWorker = cachedWorkers.get((int) (Math.random() * cachedWorkers.size()));

                    String assignmentPath = String.format("%s/%s/%s", ASSIGN_PATH, designatedWorker, ctx);
                    log.info("assignment path:{}", assignmentPath);
                    doAssignTask(assignmentPath, (String) ctx, data);
                    break;
                }
                default: {
                    log.error("Error when trying to get task data", KeeperException.create(KeeperException.Code.get(rc), path));
                }
            }
        };

        zk.getData(TASKS_PATH + "/" + task, false, callback, task);
    }

    private void doAssignTask(String taskPath, String task, byte[] data) {

        AsyncCallback.StringCallback callback = (rc, path, ctx, name) -> {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS: {
                    doAssignTask(path, task, (byte[]) ctx);
                    break;
                }
                case OK: {
                    // 任务指派worker成功, 删除task路径对应的任务
                    log.info("task assigned correctly:{}", name);
                    deleteTask(task);
                    break;
                }
                case NODEEXISTS: {
                    log.error("task already assigned");
                    break;
                }
                default: {
                    KeeperException exception = KeeperException.create(KeeperException.Code.get(rc), path);
                    log.error("error when trying to assign task", exception);
                }
            }
        };

        zk.create(taskPath, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, callback, data);
    }


    private void deleteTask(String task) {
        AsyncCallback.VoidCallback callback = (rc, path, ctx) -> {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS: {
                    deleteTask(path);
                    break;
                }
                case OK: {
                    log.info("Successfully deleted " + path);
                    break;
                }
                case NONODE: {
                    log.info("Task has been deleted already");
                    break;
                }
                default: {
                    KeeperException exception = KeeperException.create(KeeperException.Code.get(rc), path);
                    log.error("Something went wrong here" + exception);
                }
            }
        };

        zk.delete(TASKS_PATH + "/" + task, -1, callback, CONTEXT
        );
    }

    private void reassignTaskForAbsentWorker(String worker) {

        AsyncCallback.ChildrenCallback callback = (rc, path, ctx, children) -> {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS: {
                    reassignTaskForAbsentWorker(path);
                    break;
                }
                case OK: {
                    // 重新分配任务
                    children.forEach(task -> reassignTask(path + "/" + task, task));
                    break;
                }
                default:
                    log.error("getChildren failed", KeeperException.create(KeeperException.Code.get(rc), path));
            }
        };

        zk.getChildren(ASSIGN_PATH + "/" + worker, false, callback, CONTEXT);
    }

    private void reassignTask(String fullPath, String task) {

        AsyncCallback.DataCallback callback = (rc, path, ctx, data, stat) -> {
            KeeperException.Code code = KeeperException.Code.get(rc);
            switch (code) {
                case CONNECTIONLOSS: {
                    reassignTask(path, (String) ctx);
                    break;
                }
                case OK: {
                    // 将任务放回任务列表, 并删除当前已分配的任务节点
                    putBackToTaskPath(new TaskContext(path, (String) ctx, data));
                    break;
                }
                default: {
                    log.error("something went wrong when getting data", KeeperException.create(code));
                }
            }
        };

        zk.getData(fullPath, false, callback, task);
    }

    private void putBackToTaskPath(TaskContext context) {

        AsyncCallback.StringCallback callback = (rc, path, ctx, name) -> {
            KeeperException.Code code = KeeperException.Code.get(rc);
            switch (code) {
                case CONNECTIONLOSS: {
                    putBackToTaskPath((TaskContext) ctx);
                    break;
                }
                case OK: {
                    deleteAssignment(((TaskContext) ctx).path);
                    break;
                }
                case NODEEXISTS: {
                    log.info("Node exists already, but if it hasn't been deleted, then it will eventually, so we keep trying: " + path);
                    // 任务已经放回
                    deleteAssignment(((TaskContext) ctx).path);
                    break;
                }
                default: {
                    log.error("Something want wrong when recreating task", KeeperException.create(code));
                }
            }
        };

        zk.create(TASKS_PATH + "/" + context.task, context.data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, callback, context);
    }

    private void deleteAssignment(String deletePath) {

        AsyncCallback.VoidCallback callback = (rc, path, rtx) -> {
            KeeperException.Code code = KeeperException.Code.get(rc);
            switch (code) {
                case CONNECTIONLOSS: {
                    deleteAssignment(path);
                    break;
                }
                case OK: {
                    log.info("Task correctly deleted: " + path);
                    break;
                }
                default: {
                    log.error("Failed to delete task data" + KeeperException.create(code, path));
                }
            }
        };
        zk.delete(deletePath, -1, callback, CONTEXT);
    }

    @Data
    @AllArgsConstructor
    private static class TaskContext {

        /**
         * 路径
         */
        String path;

        /**
         * 任务
         */
        String task;

        /**
         * 数据
         */
        byte[] data;
    }

    /**
     * 尝试推断竞选结果,如果没有主节点,继续尝试竞选,如果有主节点且当前节点非主节点,在主节点位置设置监视点
     */
    private void deduceRunForResult() {
        AsyncCallback.DataCallback masterCheckCallback = (rc, path, ctx, data, stat) -> {
            KeeperException.Code code = KeeperException.Code.get(rc);
            switch (code) {
                case CONNECTIONLOSS: {
                    // 网络问题 继续尝试
                    deduceRunForResult();
                    break;
                }
                case NONODE: {
                    // 主节点不存在 继续尝试竞选主节点
                    runForMaster();
                    break;
                }
                case OK: {
                    if (Objects.equals(serverId, new String(data))) {
                        // 竞选成功 执行主节点逻辑
                        state = MasterStates.ELECTED;
                        takeLeadership();
                    } else {
                        state = MasterStates.NOT_ELECTED;
                        // 竞选失败 注册监视点
                        registerWatcherForMasterPath();
                    }
                    break;
                }
                default: {
                    KeeperException exception = KeeperException.create(code, path);
                    log.error("Error when reading data", exception);
                }
            }
        };
        zk.getData(MASTER_PATH, false, masterCheckCallback, CONTEXT);
    }


    /**
     * 如果主节点存在,给主节点设置监视点,如果主节点删除,重新竞选; 如果主节点不存在,重新竞选
     */
    private void registerWatcherForMasterPath() {

        Watcher watcher = e -> {
            if (e.getType() == Event.EventType.NodeDeleted) {
                assert Objects.equals(MASTER_PATH, e.getPath());
                // 如果主节点失效 立即尝试重新竞选主节点
                runForMaster();
            }
        };

        AsyncCallback.StatCallback callback = (rc, path, ctx, stat) -> {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS: {
                    // 网络问题 继续尝试
                    registerWatcherForMasterPath();
                    break;
                }
                case OK: {
                    break;
                }
                case NONODE: {
                    state = MasterStates.NOT_ELECTED;
                    // 没有主节点 再次尝试竞选
                    runForMaster();
                    log.info("It sounds like the previous master is gone, so let's elect for master again");
                    break;
                }
                default: {
                    // 其他情况 再次确认转态
                    deduceRunForResult();
                    break;
                }
            }
        };
        zk.exists(MASTER_PATH, watcher, callback, CONTEXT);
    }

    private enum MasterStates {
        /**
         * 竞选成功
         */
        ELECTED,

        /**
         * 非竞选成功
         */
        NOT_ELECTED
    }

    @Override
    public void process(WatchedEvent event) {

        if (event.getType() == Event.EventType.None) {
            Event.KeeperState state = event.getState();
            switch (state) {
                case SyncConnected:
                    connected = true;
                    expired = false;
                    break;
                case Disconnected:
                    connected = false;
                    expired = false;
                    break;
                case Expired:
                    expired = true;
                    connected = false;
                    log.error("Session expiration");
                default: {
                    break;
                }
            }
        }


    }

    @Override
    public void close() {
        if (Objects.nonNull(zk)) {
            try {
                zk.close();
            } catch (InterruptedException exception) {
                log.error("interrupted while closing ZooKeeper session", exception);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("Master{serverId='%s', state=%s}", serverId, state);
    }
}

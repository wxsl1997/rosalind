package com.wxsl.rosalind.zk.leader;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.Closeable;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author wxsl1997
 */
@Slf4j
public class Worker implements Watcher, Closeable {

    private static final Object CONTEXT = new Object();

    public static final String IDLE = "Idle";
    public static final String WORKERS_PATH = "/workers";
    public static final String DONE = "done";
    public static final String STATUS_PATCH = "/status";
    private ZooKeeper zk;

    private final ThreadPoolExecutor executor;

    private final String address;

    private final String serverId;

    @Getter
    private volatile boolean connected = false;

    @Getter
    private volatile boolean expired = false;

    protected ChildrenCache assignedTasksCache = new ChildrenCache(Lists.newArrayList());

    public Worker(String address, String serverId) {
        this.address = address;
        this.serverId = serverId;

        this.executor = new ThreadPoolExecutor(8, 8, 1000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(128));
    }

    @SneakyThrows
    public void startZK() {
        zk = new ZooKeeper(address, 15_000, this);
    }

    public void register() {

        AsyncCallback.StringCallback callback = (rc, path, ctx, name) -> {
            KeeperException.Code code = KeeperException.Code.get(rc);
            switch (code) {
                case CONNECTIONLOSS: {
                    register();
                    break;
                }
                case OK: {
                    log.info("registered successfully:{}", serverId);
                    break;
                }
                case NODEEXISTS: {
                    log.info("already registered:{}", serverId);
                    break;
                }
                default: {
                    log.error("something went wrong", KeeperException.create(code, path));

                }
            }
        };

        String name = "worker-" + serverId;
        zk.create(WORKERS_PATH + "/" + name, IDLE.getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, callback, CONTEXT);
    }

    public void bootstrap() {

        AsyncCallback.StringCallback callback = (rc, path, ctx, name) -> {
            KeeperException.Code code = KeeperException.Code.get(rc);
            switch (code) {
                case CONNECTIONLOSS: {
                    bootstrap();
                    break;
                }
                case OK: {
                    log.info("assign node created");
                    break;
                }
                case NODEEXISTS: {
                    log.info("assign node already registered");
                    break;
                }
                default: {
                    log.error("something went wrong", KeeperException.create(code, path));
                }
            }
        };

        zk.create("/assign/worker-" + serverId, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, callback, null);
    }

    public void processTask() {

        Watcher watcher = e -> {
            if (e.getType() == Event.EventType.NodeChildrenChanged) {
                assert Objects.equals("/assign/worker-" + serverId, e.getPath());
                processTask();
            }
        };

        AsyncCallback.ChildrenCallback callback = (rc, path, ctx, children) -> {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS: {
                    processTask();
                    break;
                }
                case OK:
                    if (children != null) {

                        List<String> newTasks = assignedTasksCache.getNewAndRefreshCache(children);

                        List<Runnable> tasks = newTasks.stream()
                                .map(task -> (Runnable) () -> zk.getData("/assign/worker-" + serverId + "/" + task, false, processTaskCallback, task))
                                .collect(Collectors.toList());

                        // 多线程提交任务
                        tasks.forEach(executor::submit);
                    }
                    break;
                default: {
                    System.out.println("getChildren failed: " + KeeperException.create(KeeperException.Code.get(rc), path));
                }
            }
        };

        zk.getChildren("/assign/worker-" + serverId, watcher, callback, CONTEXT);
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
                    log.error("Session expire");
                default: {
                    break;
                }
            }
        }
    }

    private final AsyncCallback.StringCallback taskStatusCreateCallback = new AsyncCallback.StringCallback() {
        public void processResult(int rc, String path, Object ctx, String name) {
            KeeperException.Code code = KeeperException.Code.get(rc);
            switch (code) {
                case CONNECTIONLOSS: {
                    zk.create(path + "/status", "done".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, taskStatusCreateCallback, CONTEXT);
                    break;
                }
                case OK: {
                    log.info("created status zk node correctly:{}", name);
                    break;
                }
                case NODEEXISTS: {
                    log.info("node exists: {}", path);
                    break;
                }
                default: {
                    log.error("failed to create task data", KeeperException.create(code, path));
                }
            }

        }
    };

    private final AsyncCallback.VoidCallback assignedTaskDeletedCallback = (rc, path, rtx) -> {
        KeeperException.Code code = KeeperException.Code.get(rc);
        switch (code) {
            case CONNECTIONLOSS: {
                break;
            }
            case OK: {
                log.info("task correctly deleted:{}", path);
                break;
            }
            default: {
                log.error("Failed to delete task data" + KeeperException.create(code, path));
            }
        }
    };

    private final AsyncCallback.DataCallback processTaskCallback = new AsyncCallback.DataCallback() {
        public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS: {
                    zk.getData(path, false, processTaskCallback, ctx);
                    break;
                }
                case OK: {
                    log.info("executing your task:{}", new String(data));
                    zk.create(STATUS_PATCH + "/" + ctx, DONE.getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, taskStatusCreateCallback, CONTEXT);
                    zk.delete("/assign/worker-" + serverId + "/" + ctx, -1, assignedTaskDeletedCallback, CONTEXT);
                    break;
                }
                default: {
                    log.error("Failed to get task data: ", KeeperException.create(KeeperException.Code.get(rc), path));
                }
            }
        }
    };
}

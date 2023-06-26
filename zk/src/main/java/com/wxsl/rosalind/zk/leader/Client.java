package com.wxsl.rosalind.zk.leader;

import lombok.Data;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.Closeable;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wxsl1997
 */
@Slf4j
public class Client implements Watcher, Closeable {

    ZooKeeper zk;

    private final String address;

    protected final ConcurrentHashMap<String, Object> ctxMap = new ConcurrentHashMap<>();

    @Getter
    private volatile boolean connected = false;

    @Getter
    private volatile boolean expired = false;


    public Client(String address) {
        this.address = address;
    }

    @SneakyThrows
    public void startZK() {
        zk = new ZooKeeper(address, 15_000, this);
    }

    public void submitTask(String task) {

        AsyncCallback.StringCallback callback = (rc, path, ctx, name) -> {
            KeeperException.Code code = KeeperException.Code.get(rc);
            switch (code) {
                case CONNECTIONLOSS: {
                    submitTask(((TaskContext) ctx).getTask());
                    break;
                }
                case OK: {
                    log.info("my created task name:{}", name);
                    ((TaskContext) ctx).setTaskName(name);
                    watchStatus(name.replace("/tasks/", "/status/"), ctx);
                    break;
                }
                default: {
                    log.error("Something went wrong" + KeeperException.create(code, path));
                }
            }
        };

        TaskContext taskCtx = new TaskContext();
        taskCtx.setTask(task);

        zk.create("/tasks/task-", task.getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL, callback, taskCtx);
    }

    private void watchStatus(String statusPath, Object taskCtx) {
        AsyncCallback.StatCallback callback = (rc, path, ctx, stat) -> {
            KeeperException.Code code = KeeperException.Code.get(rc);
            switch (code) {
                case CONNECTIONLOSS: {
                    watchStatus(path, ctx);
                    break;
                }
                case OK: {
                    if (stat != null) {
                        zk.getData(path, false, getDataCallback, ctx);
                        log.info("status node is there:{}", path);
                    }
                    break;
                }
                case NONODE: {
                    break;
                }
                default: {
                    log.error("something went wrong when checking if the status node exists" + KeeperException.create(code, path));
                    break;
                }
            }
        };

        Watcher watcher = e -> {
            if (e.getType() == Event.EventType.NodeCreated) {
                assert e.getPath().contains("/status/task-");
                assert ctxMap.containsKey(e.getPath());

                zk.getData(e.getPath(), false, getDataCallback, ctxMap.get(e.getPath()));
            }
        };

        ctxMap.put(statusPath, taskCtx);
        zk.exists(statusPath, watcher, callback, taskCtx);
    }

    private final AsyncCallback.DataCallback getDataCallback = new AsyncCallback.DataCallback() {
        public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
            KeeperException.Code code = KeeperException.Code.get(rc);
            switch (code) {
                case CONNECTIONLOSS: {
                    zk.getData(path, false, getDataCallback, ctxMap.get(path));
                    return;
                }
                case OK: {
                    String taskStatus = new String(data);
                    log.info("task:{}, status:{}", path, taskStatus);

                    assert (ctx != null);
                    ((TaskContext) ctx).setSuccessful(taskStatus.contains("done"));

                    zk.delete(path, -1, taskDeleteCallback, null);
                    ctxMap.remove(path);
                    break;
                }
                case NONODE: {
                    log.warn("status node is gone");
                    return;
                }
                default: {
                    log.error("something went wrong here", KeeperException.create(code, path));
                }
            }
        }
    };

    private final AsyncCallback.VoidCallback taskDeleteCallback = new AsyncCallback.VoidCallback() {
        public void processResult(int rc, String path, Object ctx) {
            KeeperException.Code code = KeeperException.Code.get(rc);
            switch (code) {
                case CONNECTIONLOSS: {
                    zk.delete(path, -1, taskDeleteCallback, null);
                    break;
                }
                case OK: {
                    log.info("successfully deleted " + path);
                    break;
                }
                default: {
                    log.error("something went wrong here", KeeperException.create(code, path));
                }
            }
        }
    };


    @Data
    private static class TaskContext {
        private String task;
        private String taskName;
        private boolean successful;
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
                    log.error("Exiting due to session expiration");
                default: {
                    break;
                }
            }
        }
    }
}

package com.wxsl.rosalind.zookeeper.curator;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.zookeeper.CreateMode;

import java.io.Closeable;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * @author wxsl1997
 */
@Slf4j
public class Master implements Closeable, LeaderSelectorListener {

    private static final String MASTER_PATH = "/master";
    private static final String WORKERS_PATH = "/workers";
    private static final String TASKS_PATH = "/tasks";
    private static final String ASSIGN_PATH = "/assign";
    private static final String STATUS_PATH = "/status";
    private final String serverId;
    private final CuratorFramework client;
    LeaderSelector leaderSelector;

    private final CuratorCache workersCache;

    private final CuratorCache tasksCache;

    private final CountDownLatch leaderLatch = new CountDownLatch(1);

    private final CountDownLatch closeLatch = new CountDownLatch(1);


    public Master(String address, String serverId, RetryPolicy retryPolicy) {
        this.serverId = serverId;

        this.client = CuratorFrameworkFactory.builder()
                .connectString(address)
                .retryPolicy(retryPolicy)
                .build();

        this.leaderSelector = new LeaderSelector(this.client, MASTER_PATH, this);

        this.workersCache = CuratorCache.builder(this.client, WORKERS_PATH).build();

        this.tasksCache = CuratorCache.builder(this.client, TASKS_PATH).build();
    }

    public void startZK() {
        client.start();
    }

    @Override
    public void close() {
        // enable close
        closeLatch.countDown();
        // close leader selector
        leaderSelector.close();
        //  close client
        client.close();
    }

    public boolean isLeader() {
        return this.leaderSelector.hasLeadership();
    }

    public boolean isConnected() {
        return client.getZookeeperClient().isConnected();
    }

    @SneakyThrows
    public void awaitLeadership() {
        // blocking, waiting for leadership
        leaderLatch.await();
    }

    @SneakyThrows
    public void bootstrap() {
        client.create().inBackground().forPath(WORKERS_PATH, new byte[0]);
        client.create().inBackground().forPath(ASSIGN_PATH, new byte[0]);
        client.create().inBackground().forPath(TASKS_PATH, new byte[0]);
        client.create().inBackground().forPath(STATUS_PATH, new byte[0]);
    }

    public void runwForMaster() {
        leaderSelector.setId(serverId);
        log.info("starting master selection: {}", serverId);
        leaderSelector.autoRequeue();
        leaderSelector.start();
    }

    @Override
    public void takeLeadership(CuratorFramework curatorFramework) throws InterruptedException {

        log.info("take leader ship, serverId:{}", leaderSelector.getId());
        // register listeners
        client.getCuratorListenable().addListener(this::masterListener);
        client.getUnhandledErrorListenable().addListener((message, e) -> log.error("unhandled error occur", e));


        workersCache.listenable().addListener(CuratorCacheListener.builder()
                .forDeletes(worker -> {
                    String path = worker.getPath().replace(WORKERS_PATH, ASSIGN_PATH);
                    getChildByPath(path, (client1, event) -> reassignTaskForAbsentWorker(event.getPath(), event.getChildren()));
                })
                .build());
        workersCache.start();

        tasksCache.listenable().addListener(CuratorCacheListener.builder()
                .forCreates(task -> {
                    if (task.getPath().endsWith(TASKS_PATH)) {
                        return;
                    }
                    // remove assigned task from task patch
                    BackgroundCallback callback = (client, event) -> deleteByPath(task.getPath());

                    // assign new task
                    assignTask(task.getPath().replace("/tasks/", ""), callback);


                    log.info("create task:{}", task);
                })
                .build());
        tasksCache.start();

        // acquire leader privilege
        leaderLatch.countDown();

        // waiting close
        closeLatch.await();
    }

    private void reassignTaskForAbsentWorker(String worker, List<String> tasks) {
        //
        tasks.forEach(task -> deleteByPath(worker + "/" + task));

        // reassign deleted task
        tasks.forEach(task -> {
            // remove absent  worker's task
            BackgroundCallback callback = (client, event) -> deleteByPath(worker + "/" + task);
            // reassign task
            assignTask(task, callback);
        });
    }

    private void assignTask(String task, BackgroundCallback callback) {
        List<ChildData> workers = currentWorkers();

        if (workers.size() == 0) {
            return;
        }
        // random get one worker
        ChildData reassignWorker = workers.get((int) (Math.random() * workers.size()));

        String path = reassignWorker.getPath().replace(WORKERS_PATH, ASSIGN_PATH) + "/" + task;
        createByPath(path, task.getBytes(StandardCharsets.UTF_8), callback);
    }

    private List<ChildData> currentWorkers() {
        return workersCache.stream().filter(w -> !w.getPath().endsWith(WORKERS_PATH)).collect(Collectors.toList());
    }

    @Override
    public void stateChanged(CuratorFramework client, ConnectionState newState) {
        switch (newState) {
            case CONNECTED:
            case RECONNECTED:
            case READ_ONLY: {
                break;
            }
            case SUSPENDED: {
                log.error("session suspended");
                break;
            }
            case LOST: {
                close();
                break;
            }
        }
    }

    private void masterListener(CuratorFramework client, CuratorEvent event) {
        log.info("master listener, path:{}, event:{}", event.getPath(), event);
    }

    @SneakyThrows
    private void createByPath(String path, byte[] data, BackgroundCallback callback) {
        client.create()
                .withMode(CreateMode.PERSISTENT)
                .inBackground(callback)
                .forPath(path, data);
    }

    @SneakyThrows
    private void getChildByPath(String path, BackgroundCallback callback) {
        client.getChildren().inBackground(callback).forPath(path);
    }

    @SneakyThrows
    private void deleteByPath(String path) {
        client.delete()
                .inBackground((client, event) -> log.info("success deleted path:{}", event.getPath()))
                .forPath(path);
    }
}

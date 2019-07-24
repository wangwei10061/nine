package com.ali.test.nine.mysqlclient;

import com.ali.test.nine.client.mysqlclient.entity.User;
import com.ali.test.nine.client.mysqlclient.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MysqlClientTest {
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(MysqlClientTest.class);

    @Before
    public void setUp() {
        // 删除所有数据
        List<User> users = userService.listUser();
        for (User user : users) {
            userService.delete(user.getId());
        }

        // 添加500条
        Instant startTime = Instant.now();

        ExecutorService executorService = Executors.newFixedThreadPool(500);
        AtomicInteger index = new AtomicInteger(0);

        CountDownLatch latch = new CountDownLatch(500);

        for (int i = 0; i < 500; i++) {
            executorService.execute(() -> {
                User user = new User();
                user.setName("miki" + index.incrementAndGet());
                user.setPassword("miki" + index.incrementAndGet());
                user.setNumber("" + index.incrementAndGet());
                userService.insertUser(user);

                latch.countDown();
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Duration d = Duration.between(startTime, Instant.now());
        logger.info("setUp: " + d.getNano() + "ns");

    }

    @Test
    public void multiInsert() throws InterruptedException {
        List<User> users = userService.listUser();
        for (User user : users) {
            userService.delete(user.getId());
        }

        Instant startTime = Instant.now();

        ExecutorService executorService = Executors.newFixedThreadPool(500);
        AtomicInteger index = new AtomicInteger(0);

        CountDownLatch latch = new CountDownLatch(500);

        for (int i = 0; i < 500; i++) {
            executorService.execute(() -> {
                User user = new User();
                user.setName("miki" + index.incrementAndGet());
                user.setPassword("miki" + index.incrementAndGet());
                user.setNumber("" + index.incrementAndGet());
                userService.insertUser(user);

                latch.countDown();
            });
        }

        latch.await();
        Duration d = Duration.between(startTime, Instant.now());
        logger.info("insert: " + d.getNano() + "ns");

    }

    @Test
    public void multiQuery() throws InterruptedException {
        Instant startTime = Instant.now();

        ExecutorService executorService = Executors.newFixedThreadPool(500);

        CountDownLatch latch = new CountDownLatch(500);

        for (int i = 0; i < 500; i++) {
            executorService.execute(() -> {
                List<User> users = userService.listUser();
                users.stream().forEach(System.out::println);
                latch.countDown();
            });
        }

        latch.await();
        Duration d = Duration.between(startTime, Instant.now());
        logger.info("query: " + d.getNano() + "ns");

    }

    @Test
    public void multiUpdate() throws InterruptedException {
        Instant startTime = Instant.now();

        ExecutorService executorService = Executors.newFixedThreadPool(500);

        CountDownLatch latch = new CountDownLatch(500);

        List<User> users = userService.listUser();
        for (User user : users) {
            executorService.execute(() -> {
                user.setNumber("1212");
                userService.update(user);
                latch.countDown();
            });
        }

        latch.await();
        Duration d = Duration.between(startTime, Instant.now());
        logger.info("update: " + d.getNano() + "ns");
    }

    @Test
    public void multiDelete() throws InterruptedException {
        Instant startTime = Instant.now();

        ExecutorService executorService = Executors.newFixedThreadPool(500);

        CountDownLatch latch = new CountDownLatch(500);

        List<User> users = userService.listUser();
        for (User user : users) {
            executorService.execute(() -> {
                userService.delete(user.getId());
                latch.countDown();
            });
        }

        latch.await();
        Duration d = Duration.between(startTime, Instant.now());
        logger.info("delete: " + d.getNano() + "ns");
    }
}
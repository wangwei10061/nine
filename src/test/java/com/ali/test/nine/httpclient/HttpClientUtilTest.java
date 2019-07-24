package com.ali.test.nine.httpclient;

import com.ali.test.nine.client.httpclient.HttpClientUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpClientUtilTest {
    private Logger logger = LoggerFactory.getLogger(HttpClientUtilTest.class);

    @Autowired
    private HttpClientUtil httpClientUtil;

    @Test
    public void post() throws InterruptedException {

        Instant startTime = Instant.now();

        ExecutorService executorService = Executors.newFixedThreadPool(500);

        CountDownLatch latch = new CountDownLatch(500);

        for (int i = 0; i < 500; i++) {

            executorService.execute(() -> {
                httpClientUtil.post();
                latch.countDown();
            });
        }

        latch.await();
        Duration d = Duration.between(startTime, Instant.now());
        logger.info("post: " + d.getNano() + "ns");

    }

    @Test
    public void get() throws InterruptedException {
        Instant startTime = Instant.now();

        ExecutorService executorService = Executors.newFixedThreadPool(500);

        CountDownLatch latch = new CountDownLatch(500);

        for (int i = 0; i < 500; i++) {

            executorService.execute(() -> {
                httpClientUtil.get();
                latch.countDown();
            });
        }

        latch.await();
        Duration d = Duration.between(startTime, Instant.now());
        logger.info("get: " + d.getNano() + "ns");
    }
}
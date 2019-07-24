package com.ali.test.nine.concurrent.service.impl;

import com.ali.test.nine.concurrent.OnResponseListener;
import com.ali.test.nine.concurrent.service.TestService;

import com.ali.test.nine.concurrent.TestTask;
import com.ali.test.nine.concurrent.TestType;
import com.ali.test.nine.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class TestServiceImpl implements TestService, OnResponseListener {

    /**
     * 本条测试针对tomcat容器
     * 利用线程池发送高并发请求
     * 比较在不同jdk环境下的总响应时间 和 每个请求的平均响应时间
     */

    private ThreadPoolExecutor threadPool;
    private double totalResponseTime;
    private int requestCount;

    @Override
    public void testByType(int threadCount, String testType) {

        TestType realType = convertType(testType);

        //默认1000个线程
        int mThreadCount = threadCount > 0 ? threadCount : 1000;
        threadPool = new ThreadPoolExecutor(mThreadCount, mThreadCount, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5));
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(mThreadCount);

        for (int i = 0; i < threadCount; i++) {
            TestTask task = new TestTask(realType, startLatch, endLatch, this);
            try {
                threadPool.execute(task);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        long startTime = System.currentTimeMillis();
        startLatch.countDown();
        try {
            endLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("总用时: " + (endTime - startTime) + "ms");
        System.out.println("平均 response time: " + StringUtil.twoPoint(totalResponseTime/requestCount) + "ms");
    }

    private TestType convertType(String testType) {
        switch (testType) {
            case "tomcat":
                return TestType.TOMCAT;
            case "mysql":
                return TestType.MYSQL;
            case "dubbo":
                return TestType.DUBBO;
            case "mq":
                return TestType.ROCKET_MQ;
            case "http":
                return TestType.HTTP_CLIENT;
        }

        return TestType.TOMCAT;
    }


    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onSuccess(long responseTime) {
        totalResponseTime += responseTime;
        requestCount++;
    }
}

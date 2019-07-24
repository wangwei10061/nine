package com.ali.test.nine.concurrent;

import com.ali.test.nine.concurrent.service.HelperService;
import com.ali.test.nine.concurrent.service.impl.HelperServiceImpl;

import java.util.concurrent.CountDownLatch;

public class TestTask implements Runnable {

    OnResponseListener onResponseListener;
    CountDownLatch endLatch;
    CountDownLatch startLatch;
    TestType testType;

    private HelperService helperService;

    public TestTask(TestType testType, CountDownLatch startLatch, CountDownLatch endLatch,
                    OnResponseListener onResponseListener) {
        this.testType = testType;
        this.startLatch = startLatch;
        this.endLatch = endLatch;
        this.onResponseListener = onResponseListener;
        this.helperService = new HelperServiceImpl();
    }

    @Override
    public void run() {
        try {
            startLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long startTime = System.currentTimeMillis();

        try {
            // 根据不同测试类别进行测试
            helperService.startTestByType(testType);

            long endTime = System.currentTimeMillis();
            //返回响应的时间
            endLatch.countDown();
            onResponseListener.onSuccess(endTime - startTime);
        } catch (Exception e) {
            onResponseListener.onFailure(e.getMessage());
            e.printStackTrace();
        } finally {
            // TODO:
            // 清理
        }
    }
}

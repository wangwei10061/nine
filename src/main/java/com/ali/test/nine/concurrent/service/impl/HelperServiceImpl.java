package com.ali.test.nine.concurrent.service.impl;

import com.ali.test.nine.concurrent.service.HelperService;
import com.ali.test.nine.concurrent.TestType;
import com.ali.test.nine.client.httpclient.HttpClientUtil;
import com.ali.test.nine.client.mysqlclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HelperServiceImpl implements HelperService {

    @Autowired
    UserService userService;

    @Override
    public void startTestByType(TestType testType) {
        switch (testType) {
            case TOMCAT:
                startTomcatTest();
                break;
            case MYSQL:
                startMySQLTest();
                break;
            case HTTP_CLIENT:
                startHttpClientTest();
                break;
            case DUBBO:
                startDubboTest();
                break;
            case ROCKET_MQ:
                startRocketMQTest();
                break;
        }
    }

    private void startTomcatTest() {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        httpClientUtil.post();
    }

    private void startMySQLTest() {
        userService.listUser();
    }

    private void startHttpClientTest() {
        // TODO
    }

    private void startDubboTest() {
        // TODO
    }

    private void startRocketMQTest() {
        // TODO
    }
}

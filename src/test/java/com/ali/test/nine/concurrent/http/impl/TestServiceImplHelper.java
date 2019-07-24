package com.ali.test.nine.concurrent.http.impl;

import com.ali.test.nine.concurrent.service.TestService;
import com.ali.test.nine.concurrent.TestType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServiceImplHelper {

    @Autowired
    TestService testService;

    @Test
    public void httpTest() {
        testService.testByType(50, "tomcat");
    }
}
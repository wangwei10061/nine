package com.ali.test.nine.httpclient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpClientUtilTest {

    @Autowired
    private HttpClientUtil httpClientUtil;

    @Test
    public void post() {
        httpClientUtil.post();
    }

    @Test
    public void get() {
        httpClientUtil.get();
    }
}
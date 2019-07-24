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

//    @Test
//    public void post() {
//        httpClientUtil.post();
//    }

    @Test
    public void get() {

        long startTime=System.nanoTime();   //获取开始时间
        for(int i =0;i<1000;i++){
            httpClientUtil.get();
        }
        long endTime=System.nanoTime(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ns");
    }
}
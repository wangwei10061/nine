package com.ali.test.nine.netty;

import com.ali.test.nine.client.netty.NettyClient;
import com.ali.test.nine.client.netty.NettyServer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NettyTest {

    @Before
    public void setUp() {
        new Thread(() -> {
            try {
                new NettyServer(9999).run();
            } catch (Exception e) {
                e.printStackTrace();
                assertFalse(true);
            }
        }).start();
    }

    @Test
    public void client() {
        new Thread(() -> {
            NettyClient client = new NettyClient();
            try {
                client.connect("127.0.0.1", 9999);
            } catch (Exception e) {
                e.printStackTrace();
                assertFalse(true);
            }
        }).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
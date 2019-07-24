package com.ali.test.nine.controller;

import com.ali.test.nine.concurrent.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("/test")
    public String testByType(@RequestParam("count") int threadCount, @RequestParam("type") String testType) {
        testService.testByType(threadCount, testType);
        return "";
    }
}

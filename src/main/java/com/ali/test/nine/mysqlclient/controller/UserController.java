package com.ali.test.nine.mysqlclient.controller;

import java.util.List;

import com.ali.test.nine.mysqlclient.entity.User;
import com.ali.test.nine.mysqlclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/user", method = {RequestMethod.GET, RequestMethod.POST})
public class UserController {
    @Autowired
    private UserService userservice;

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(int id) {
        int result = userservice.delete(id);
        if (result >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(User user) {
        int result = userservice.update(user);
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }

    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public User insert(User user) {
        return userservice.insertUser(user);
    }

    @RequestMapping("/listUser")
    @ResponseBody
    public List<User> listUser() {
        return userservice.listUser();
    }

    @RequestMapping("/listUserByname")
    @ResponseBody
    public List<User> listUserByname(String name) {
        return userservice.findByName(name);
    }
}

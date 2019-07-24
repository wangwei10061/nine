package com.ali.test.nine.client.mysqlclient.service;

import java.util.List;

import com.ali.test.nine.client.mysqlclient.entity.User;
import com.ali.test.nine.client.mysqlclient.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> findByName(String name) {
        return userMapper.findUserByName(name);
    }

    public User insertUser(User user) {
        userMapper.insertUser(user);
        return user;
    }
    public List<User> listUser(){
        return	userMapper.listUser();
    }


    public int update(User user){
        return userMapper.update(user);
    }

    public int delete(int id){
        return userMapper.delete(id);
    }
}
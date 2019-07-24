package com.ali.test.nine.client.mysqlclient.mapper;

import java.util.List;

import com.ali.test.nine.client.mysqlclient.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    List<User> findUserByName(String name);

    public List<User> listUser();

    public int insertUser(User user);

    public int delete(int id);

    public int update(User user);

}
package com.example.demo.mapper;

import com.example.demo.model.User;
import com.example.demo.model.UserExample;
import com.example.demo.service.support.ExampleCrudMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserMapper extends ExampleCrudMapper<UserExample, User, Integer> {

//    User getUserById(Integer id);
//
//    List<User> findAll();
//
//    int addUser(User user);
//
//    int updateUser(@Param("id") Integer id, @Param("user") User user);
//
//    int deleteUser(Integer id);

}

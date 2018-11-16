package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.service.support.CrudServiceImpl;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp extends CrudServiceImpl<User, Integer, UserMapper> implements UserService {

    private static final Logger logger = LoggerFactory
            .getLogger(UserServiceImp.class);

    @Override
    @Autowired
    public void setRepository(UserMapper repository) {
        super.setRepository(repository);
    }

    //    @Override
//    public User getUserById(Integer id) {
//        return userMapper.getUserById(id);
//    }
//
//    @Override
//    public List<User> findAll() {
//        return userMapper.findAll();
//    }
//
//    @Override
//    public int addUser(User user) {
//        return userMapper.addUser(user);
//    }
//
//    @Override
//    public int updateUser(Integer id, User user) {
//        return userMapper.updateUser(id, user);
//    }
//
//    @Override
//    public int deleteUser(Integer id) {
//        return userMapper.deleteUser(id);
//    }
}

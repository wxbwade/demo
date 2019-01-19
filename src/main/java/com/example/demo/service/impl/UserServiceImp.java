package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.service.support.CrudServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    @Override
    @Cacheable(value = "user", key = "'user_'+#id")
    public User getUser(int id) {
        System.out.println(id + "进入实现类获取数据！");
        return findOne(id);
    }

    @Override
    @CachePut(value = "user", key = "'user_'+#user.id")
    public User updateUser(User user) {
        System.out.println("更新数据！");
        return update(user);
    }

}

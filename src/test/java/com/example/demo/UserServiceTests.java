package com.example.demo;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
    @MockBean
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Test
    public void testUserService(){
        Integer uid = 1;

        User user = new User();
        user.setId(uid);
        user.setName("Jack");
        user.setAge(25);
        user.setCreatetime(new Date());
        // Mockito指定打桩对象返回值
        BDDMockito.given(this.userMapper.selectByPrimaryKey(uid)).willReturn(user);

        // 直接返回对象时相等，返回新对象时则不想等，所以一般比较对象的值
        User testUser = userService.findOne(uid);
        Assert.assertEquals(user.getId(), testUser.getId());
    }

}

package com.example.demo;

import com.example.demo.controller.UserController;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    private MockMvc mvc;

    @MockBean
    UserService userService;

    @MockBean
    UserMapper userMapper;

    /**
     * 注入上下文容器
     */
    @Autowired
    private WebApplicationContext webapp;

    /**
     * 获取上下文构造MockMvc
     */
    @Before
    public void setup(){
        this.mvc = MockMvcBuilders.webAppContextSetup(this.webapp).build();
    }

    @Test
    public void testController() throws Exception {
        // mvc.perform:执行请求;
        // MockMvcRequestBuilders.get(url):构造get请求;
        // ResultActions.andExcpect添加断言判断
        // ResultActions.andDo添加结果处理器
        // ResultActions.andReturn返回执行结果
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/user/10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.jsonPath("status").value("true"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }
}

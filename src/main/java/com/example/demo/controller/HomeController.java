package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO 描述:
 *
 * @author xiaobin.wang@marketin.cn
 * @create 2018-11-17 1:22
 * @since 2.16.6
 */
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String helloHtml() {
        return "home";
    }

}
package com.example.demo.reactive.context;

import org.springframework.context.ApplicationEvent;

/**
 * Event
 *
 * @author xiaobin.wang@marketin.cn
 * @create 2018-12-20 11:00
 * @since 2.16.6
 */
public class MyEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    public MyEvent(Object source) {
        super(source);
    }
}
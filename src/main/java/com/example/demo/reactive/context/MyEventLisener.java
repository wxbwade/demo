package com.example.demo.reactive.context;

import org.springframework.context.ApplicationListener;

/**
 * Event监听
 *
 * @author xiaobin.wang@marketin.cn
 * @create 2018-12-20 11:01
 * @since 2.16.6
 */
public class MyEventLisener implements ApplicationListener<MyEvent> {

    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        System.out.println(myEvent.getSource() + "+++++++++++++++++++" + myEvent.getTimestamp());
    }
}
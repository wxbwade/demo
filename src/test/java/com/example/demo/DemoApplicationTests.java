package com.example.demo;

import com.example.demo.reactive.context.MyEvent;
import com.example.demo.reactive.context.MyEventLisener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    ApplicationEventMulticaster applicationEventMulticaster;

    @Autowired
    ApplicationEventPublisher publisher;

    @Test
    public void contextLoads() {

    }

    @Test
    public void app() throws Exception{

//        MyEventLisener lisener = new MyEventLisener();
//        applicationEventMulticaster.addApplicationListener(lisener);
//
//        System.out.println("开始睡眠");
//        Thread.sleep(5000);
//        System.out.println("睡眠结束");
//        publisher.publishEvent(new MyEvent("ddddd"));
//        System.out.println("执行结束");

        Mono.create(monoSink -> {
            MyEventLisener lisener = new MyEventLisener();
            applicationEventMulticaster.addApplicationListener(lisener);

            System.out.println("开始睡眠");
            try{
                Thread.sleep(5000);
            }catch (Exception e){
                System.out.println(e);
            }
            System.out.println("睡眠结束");
            publisher.publishEvent(new MyEvent("ddddd"));
            monoSink.success();
            monoSink.onDispose(()->{
                System.out.println(">>>>>>>>>>>>>>>>");
            });
        });
        System.out.println("执行结束");
    }

}

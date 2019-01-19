package com.example.demo.controller;

import com.example.demo.reactive.context.MyEvent;
import com.example.demo.reactive.context.MyEventLisener;
import com.notifier.core.bean.TestMQBean;
import com.notifier.core.MessageQueue;
import com.notifier.core.NotifierMessageSender;
import com.notifier.core.exception.NotifierMessageSendException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

/**
 * TODO 描述:
 *
 * @author xiaobin.wang@marketin.cn
 * @create 2018-12-23 0:19
 * @since 2.16.6
 */
@Controller
public class FluxController {

    private static final Logger logger = LoggerFactory.getLogger(FluxController.class);

    @Autowired
    ApplicationEventMulticaster applicationEventMulticaster;

    @Autowired
    ApplicationEventPublisher publisher;

    @Autowired
    NotifierMessageSender notifierMessageSender;

    @GetMapping("/flux")
    public Mono<String> fluxTest() {
        return Mono.create(monoSink -> {
            // Spring设置广播监听器
            MyEventLisener lisener = new MyEventLisener();
            applicationEventMulticaster.addApplicationListener(lisener);

            logger.info("start");
            TestMQBean bean = new TestMQBean();
            bean.setId(10001L);
            bean.setName("SuperMan");
            try {
                notifierMessageSender.send(MessageQueue.MineMQ, bean);
            } catch (NotifierMessageSendException exception) {
                logger.error(
                        "Send inspectNotify message failed. \nerror ->{}\nmessage:{}",
                        exception, bean.toString());
            }

            System.out.println("开始睡眠");
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("睡眠结束");
            // 发送广播
            publisher.publishEvent(new MyEvent("ddddd"));
            monoSink.success("login");
            monoSink.onDispose(() -> {
                System.out.println(">>>>>>>>>>>>>>>>");
            });
        });
    }
}
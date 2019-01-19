package com.example.demo.reactive;

import com.example.demo.reactive.context.MyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import reactor.core.publisher.Flux;

/**
 * 响应式编程
 *
 * @author xiaobin.wang@marketin.cn
 * @create 2018-12-19 0:41
 * @since 2.16.6
 */
public class ReactiveTest {

    public static void main(String[] args) {
        Flux.just(1,2,4,5).subscribe(System.out::print,System.err::println,()->System.out.println("Completed!" +
                ""));
    }
}
package com.example.demo.config;

import com.notifier.config.RabbitMQConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * RabbitMQ配置
 *
 * @author xiaobin.wang@marketin.cn
 * @create 2018-11-22 20:34
 * @since 2.16.6
 */
@Configuration
@Import({RabbitMQConfiguration.class})
public class MqConfiguration {
}
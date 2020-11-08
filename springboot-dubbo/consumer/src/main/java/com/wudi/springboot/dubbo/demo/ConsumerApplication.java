package com.wudi.springboot.dubbo.demo;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.wudi.springboot.dubbo.demo.service.HelloConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDubboConfiguration
public class ConsumerApplication {

    public static void main(String[] args) {

       // SpringApplication.run(ConsumerApplication.class, args);
        //上下文
        ConfigurableApplicationContext context = SpringApplication.run(ConsumerApplication.class,args);
        HelloConsumer helloConsumer = (HelloConsumer)context.getBean("helloConsumer");
        helloConsumer.sendMessage("你好");
    }

}

package com.wudi.dubbo.consumer.service;

import com.wudi.dubbo.consumer.Annotation.ConsumerAnnotationService;
import com.wudi.dubbo.consumer.configuration.ConsumerConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Dillon Wu
 * @Description:注解方式启动
 * @date 2020/11/7 12:47
 */
public class App_Annotation {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start(); // 启动
        ConsumerAnnotationService consumerAnnotationService = context.getBean(ConsumerAnnotationService.class);
        String hello = consumerAnnotationService.doSayHello("annotation"); // 调用方法
        System.out.println(hello);
        System.out.println("result: " + hello); // 输出结果
    }
}

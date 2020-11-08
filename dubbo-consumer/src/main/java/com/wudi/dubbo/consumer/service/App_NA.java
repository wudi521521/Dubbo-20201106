package com.wudi.dubbo.consumer.service;

import com.wudi.dubbo.provider.service.ProviderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;


/**
 * @author Dillon Wu
 * @Description: 客户端启动(点对点请求方式)
 * @date 2020/11/6 14:38
 */
public class App_NA {
    public static void main(String[] args) throws IOException {
        //加载xml配置文件启动
        ClassPathXmlApplicationContext context =new ClassPathXmlApplicationContext("consumer-na.xml");
        context.start();
        ProviderService providerService = (ProviderService)context.getBean("providerService");
        String string = providerService.SayHello("hello");
        System.out.println(string+"\n");
        System.in.read();
    }
}

package com.wudi.springboot.dubbo.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author Dillon Wu
 * @Description:Spring 客户端 zk 测试请求
 * @date 2020/11/7 18:30
 */
public class ConsumerClient {

    public static void main(String[] args) throws IOException {
        //加载xml配置文件启动
        //加载xml配置文件启动
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer-context.xml");
        ServiceAPI serviceAPI = (ServiceAPI) context.getBean("serviceAPI");
        String sendMessage = serviceAPI.sendMessage("你好");
        System.out.println(sendMessage);
        context.start();
        System.in.read(); // 按任意键退出
    }
}

package com.wudi.springboot.dubbo.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author Dillon Wu
 * @Description: Spring服务端 zk 方式
 * @date 2020/11/7 18:31
 */
public class ProviderClient {
    public static void main(String[] args) throws IOException {
        //加载xml配置文件启动
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-hello-provider.xml");
        context.start();
        System.in.read(); // 按任意键退出
    }
}

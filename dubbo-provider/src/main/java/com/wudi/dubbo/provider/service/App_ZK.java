package com.wudi.dubbo.provider.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author Dillon Wu
 * @Description: xml方式启动(服务端ZK注册中心请求方式)
 * @date 2020/11/6 14:38
 */
public class App_ZK {
    public static void main(String[] args) throws IOException {
        //加载xml配置文件启动
        //加载xml配置文件启动
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF.spring/provider-zk.xml");
        context.start();
        System.in.read(); // 按任意键退出
    }
}

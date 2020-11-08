package com.wudi.dubbo.provider.service;

import com.wudi.dubbo.provider.configuration.DubboConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * @author Dillon Wu
 * @Description:服务端使用注解方式
 * @date 2020/11/7 12:28
 */
public class APP_Annotation {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DubboConfiguration.class);
        context.start();
        System.in.read();
    }
}

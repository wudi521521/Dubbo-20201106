package com.wudi.springboot.dubbo.demo.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wudi.springboot.dubbo.demo.ServiceAPI;
import org.springframework.stereotype.Component;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2020/11/7 17:16
 */
@Component
public class HelloConsumer {

    @Reference(interfaceClass = ServiceAPI.class)
    private ServiceAPI serviceAPI;

    public void sendMessage(String message){
        System.out.println(serviceAPI.sendMessage(message));
    }
}

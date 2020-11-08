package com.wudi.springboot.dubbo.demo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.wudi.springboot.dubbo.demo.ServiceAPI;
import org.springframework.stereotype.Component;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2020/11/7 17:13
 */
@Service()
@Component
public class HelloServiceAPIImpl implements ServiceAPI {
    @Override
    public String sendMessage(String message) {
        System.out.println("===========");
        return "hello-provide-message"+message;
    }
}

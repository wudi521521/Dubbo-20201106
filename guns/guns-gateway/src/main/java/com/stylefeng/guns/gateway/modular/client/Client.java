package com.stylefeng.guns.gateway.modular.client;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.model.UserApi;
import org.springframework.stereotype.Component;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2020/11/9 14:53
 */
@Component
public class Client {

    @Reference(interfaceClass = UserApi.class)
    private UserApi userApi;

    public void run(){
        userApi.login("admin","password");
    }
}

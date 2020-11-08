package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.api.model.UserApi;
import org.springframework.stereotype.Component;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2020/11/8 17:16
 */
@Component
@Service(interfaceClass = UserApi.class)
public class UserImpl implements UserApi {
    @Override
    public boolean login(String username, String password) {
        return true;
    }
}

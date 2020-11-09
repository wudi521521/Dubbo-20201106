package com.stylefeng.guns.user.modular.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.api.model.UserApi;
import org.springframework.stereotype.Component;

/**
 * @author Dillon Wu
 * @Description: 测试用户信息
 * @date 2020/11/8 17:16
 */
@Component
@Service(interfaceClass = UserApi.class)
public class UserImpl implements UserApi {
    @Override
    public boolean login(String username, String password) {
        System.out.println("guns-user:"+username+"____"+password);
        return false;
    }
}

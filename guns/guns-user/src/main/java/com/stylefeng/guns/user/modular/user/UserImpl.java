package com.stylefeng.guns.user.modular.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.api.model.UserApi;
import com.stylefeng.guns.api.model.UserInfoModel;
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
    public int login(String username, String password) {
        System.out.println("guns-user:"+username+"____"+password);
        return 3;
    }

    @Override
    public boolean register() {
        return false;
    }

    @Override
    public boolean checkUsername(String name) {
        return false;
    }

    @Override
    public UserInfoModel getUserInfo(int uuid) {
        return null;
    }

    @Override
    public UserInfoModel updateUserInfo(UserInfoModel userInfoModel) {
        return null;
    }
}

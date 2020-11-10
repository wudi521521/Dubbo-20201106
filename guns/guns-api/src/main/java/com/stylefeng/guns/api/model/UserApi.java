package com.stylefeng.guns.api.model;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2020/11/8 17:15
 */
public interface UserApi {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
     int login(String username,String password);

    /**
     * 注册
     * @return
     */
    boolean register(UserModel userModel);

    /**
     * 判断用户是否存在
     * @param name
     * @return
     */
     boolean checkUsername(String name);

    /**
     * 获取用户信息
     * @param uuid
     * @return
     */
     UserInfoModel getUserInfo(int uuid);

    /**
     * 修改用户信息
     * @return
     */
     UserInfoModel updateUserInfo(UserInfoModel userInfoModel);


}

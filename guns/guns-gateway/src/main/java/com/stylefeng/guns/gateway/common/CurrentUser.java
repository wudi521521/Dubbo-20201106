package com.stylefeng.guns.gateway.common;

import com.stylefeng.guns.api.model.UserInfoModel;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2020/11/9 16:48
 */
public class CurrentUser {

    //线程绑定的存储空间
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static void saveUserId(String userId){
        threadLocal.set(userId);
    }
    public static String getUserId(){
        String result = threadLocal.get();
        return result;
    }
    /*//保存用户信息
    public static void saveUserInfo(UserInfoModel userInfoModel){
        threadLocal.set(userInfoModel);
    };
    //将用户信息取出
    public static UserInfoModel getCurrentUser(){
        return threadLocal.get();
    }*/
}

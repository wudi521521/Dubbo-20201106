package com.stylefeng.guns.api.model;

import lombok.Data;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2020/11/9 16:15
 */
@Data
public class UserInfoModel {

    /**
     * 用户名称
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private  String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 性别
     */
    private int gender;

    /**
     * 生日
     */
    private String birthday;

    private String lifeState;

    private String biography;

    /**
     * 地址
     */
    private String address;

    private String headAddress;

    /**
     * 开始时间
     */
    private long beginTime;

    /**
     * 修改时间
     */
    private long updateTime;

    private int uuid;

}

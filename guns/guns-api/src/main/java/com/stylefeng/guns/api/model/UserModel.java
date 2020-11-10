package com.stylefeng.guns.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2020/11/9 16:08
 */
@Data
public class UserModel implements Serializable {

    private String username;

    private String password;

    private String email;

    private String phone;

    private String address;
}

package com.stylefeng.guns.gateway.modular.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.model.UserApi;
import com.stylefeng.guns.api.model.UserInfoModel;
import com.stylefeng.guns.api.model.UserModel;
import com.stylefeng.guns.gateway.common.CurrentUser;
import com.stylefeng.guns.gateway.modular.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author Dillon Wu
 * @Description:用户请求
 * @date 2020/11/10 14:31
 */
@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {
    //check=false 开启时不校验引用服务
    @Reference(interfaceClass = UserApi.class,check = false)
    private UserApi userApi;

    /**
     * 用户注册
     *
     * @param userModel
     * @return
     */
    @PostMapping("/register")
    public ResponseVO register(UserModel userModel) {
        //@RequestBody UserModel userModel
         //UserModel userModel = new UserModel();
        if (userModel.getUsername() == null || userModel.getUsername().trim().length() == 0) {
            return ResponseVO.serviceFail("用户名不能为空");
        }
        if (userModel.getPassword() == null || userModel.getPassword().trim().length() == 0) {
            return ResponseVO.serviceFail("密码不能为空");
        }
        boolean register = userApi.register(userModel);
        if (register) {
            return ResponseVO.success("注册成功");
        }

        return ResponseVO.serviceFail("注册失败");
    }

    /**
     * 用户注册
     *
     * @param username
     * @return
     */
    @PostMapping("/check")
    public ResponseVO check(String username) {

        if (StringUtils.isBlank(username)) {
            return ResponseVO.serviceFail("用户名不能为空");
        }
        //当返回true的时候，表示用户可用
        boolean notExit = userApi.checkUsername(username);
        if (notExit) {
            return ResponseVO.success("用户不存在");
        }
        return ResponseVO.serviceFail("用户已存在");
    }

    /**
     * 退出登录
     *
     * @param username
     * @return
     */
    @GetMapping("/logout")
    public ResponseVO logout(String username) {
        /**
         * 应用:
         * 1:前端存储JWT[七]:Jwt的刷新
         * 2:服务器端会存储活动用户信息[30m]
         * 3:JWT里的userId为key,查找活跃用户
         * 退出:
         * 1:前端删除掉JWT
         */

        return ResponseVO.success("用户退出成功");
    }

    /**
     * 查询用户信息
     *
     * @param
     * @return
     */
    @GetMapping("/query/info")
    public ResponseVO getUserInfo() {
        //获取当前登录用户
        String userId = CurrentUser.getUserId();
        if (!Objects.isNull(userId)){
            //将用户ID传入后端进行查询
            int uuid = Integer.parseInt(userId);
            UserInfoModel userInfo = userApi.getUserInfo(uuid);
            if (userInfo !=null){
                return ResponseVO.success(userInfo);
            }else {
                return ResponseVO.appFail("用户信息查询失败");
            }
        }


        return ResponseVO.serviceFail("用户未登录");
    }

    /**
     * 修改用户信息
     *
     * @param
     * @return
     */
    @PostMapping("/update/info")
    public ResponseVO updateUserInfo(UserInfoModel userInfoModel) {
        //获取当前登录用户
        String userId = CurrentUser.getUserId();
        if (!Objects.isNull(userId)){
            //将用户ID传入后端进行查询
            int uuid = Integer.parseInt(userId);
            //判断当前登录人员的ID与修改的结果是否一致
            if (uuid !=userInfoModel.getUuid()){
                return ResponseVO.serviceFail("请修改您本人信息");
            }
            UserInfoModel userInfo = userApi.updateUserInfo(userInfoModel);
            if (userInfo !=null){
                return ResponseVO.success(userInfo);
            }else {
                return ResponseVO.appFail("用户信息修改失败");
            }
        }


        return ResponseVO.serviceFail("用户未登录");
    }
}

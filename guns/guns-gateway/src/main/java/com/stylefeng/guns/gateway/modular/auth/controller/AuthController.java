package com.stylefeng.guns.gateway.modular.auth.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.model.UserApi;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.gateway.modular.auth.controller.dto.AuthResponse;
import com.stylefeng.guns.gateway.common.exception.BizExceptionEnum;
import com.stylefeng.guns.gateway.modular.auth.controller.dto.AuthRequest;
import com.stylefeng.guns.gateway.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.gateway.modular.auth.validator.IReqValidator;
import com.stylefeng.guns.gateway.modular.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 请求验证的
 *
 * @author fengshuonan
 * @Date 2017/8/24 14:22
 */
@RestController
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /*@Resource(name = "simpleValidator")
    private IReqValidator reqValidator;*/
    //check=false 开启时不校验引用服务
    @Reference(interfaceClass = UserApi.class,check = false)
    private UserApi userApi;

    @RequestMapping(value = "${jwt.auth-path}")
    public ResponseVO createAuthenticationToken(AuthRequest authRequest) {
        //通过用户名和密码获取校验是否正确或者正确
        boolean validate = true;
        int userId = userApi.login(authRequest.getUserName(),authRequest.getPassword());
        if (userId ==0){
            validate =false;
        }

        if (validate) {
            //randomKey和Token已经生成完毕
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken(String.valueOf(userId), randomKey);
            //返回值
            return ResponseVO.success(new AuthResponse(token, randomKey));
        } else {
            return ResponseVO.serviceFail("用户名或密码错误");
        }
    }
}

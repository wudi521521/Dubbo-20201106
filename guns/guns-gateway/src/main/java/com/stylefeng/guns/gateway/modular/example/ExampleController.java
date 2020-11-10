package com.stylefeng.guns.gateway.modular.example;

import com.stylefeng.guns.gateway.common.CurrentUser;
import com.stylefeng.guns.gateway.common.SimpleObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 常规控制器
 *
 * @author fengshuonan
 * @date 2017-08-23 16:02
 */
@Controller
@RequestMapping("/hello")
public class ExampleController {

    @RequestMapping("")
    public ResponseEntity hello() {
        //System.out.println(simpleObject.getUser());
        System.out.println(CurrentUser.getUserId());
         //userId->key->redis[userInfo]->分钟
         //目前获取方式,userId->条件，去数据库取
        return ResponseEntity.ok("请求成功!");
    }
}

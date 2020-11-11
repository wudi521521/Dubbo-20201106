package com.stylefeng.guns.film.modular.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.api.model.UserApi;
import com.stylefeng.guns.api.model.UserInfoModel;
import com.stylefeng.guns.api.model.UserModel;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.film.common.persistence.dao.MoocUserTMapper;
import com.stylefeng.guns.film.common.persistence.model.MoocUserT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

/**
 * @author Dillon Wu
 * @Description: 测试用户信息
 * @date 2020/11/8 17:16
 */
@Slf4j
@Component
@Service(interfaceClass = UserApi.class,loadbalance = "roundrobin")//指定为轮询模式
public class UserServiceImpl implements UserApi {

    @Autowired
    private MoocUserTMapper moocUserTMapper;

    @Override
    public int login(String username, String password) {
        System.out.println("guns-user:" + username + "____" + password);
        //根据登录账号获取数据信息
        MoocUserT moocUserT = new MoocUserT();
        moocUserT.setUserName(username);
        MoocUserT result = moocUserTMapper.selectOne(moocUserT);
        //获取到的结果,然后与加密以后的密码做匹配
        if (!Objects.isNull(result)) {
            String md5Password = MD5Util.encrypt(password);
            if (result.getUserPwd().equals(md5Password)) {
                return result.getUuid();
            }
        }
        return 0;
    }

    @Override
    public boolean register(UserModel userModel) {
        //获取注册信息实体转换为数据实体[mooc_user_t]
        MoocUserT moocUserT = new MoocUserT();
        moocUserT.setUserName(userModel.getUsername());
        moocUserT.setUserPwd(userModel.getPassword());//注意
        moocUserT.setEmail(userModel.getEmail());
        moocUserT.setAddress(userModel.getAddress());
        moocUserT.setUserPhone(userModel.getPhone());
        //创建时间和修改时间->current_timestamp
        //数据加密【MD5混淆加密+盐值->shiro加密】
        String md5Password = MD5Util.encrypt(userModel.getPassword());
        moocUserT.setUserPwd(md5Password);
        //将注册信息实体转换为数据实体
        Integer insert = moocUserTMapper.insert(moocUserT);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkUsername(String name) {
        EntityWrapper<MoocUserT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("user_name", name);
        Integer result = moocUserTMapper.selectCount(entityWrapper);
        log.info("====checkUsername====={}"+result);
        if (result>0) {
            return false;
        }
        return true;
    }

    @Override
    public UserInfoModel getUserInfo(int uuid) {
        //根据主键查询用户信息[MoocUserT]
        MoocUserT moocUserT = moocUserTMapper.selectById(uuid);
        //将MoocUserT转换UserInfoModel
        UserInfoModel userInfoModel = do2UserInfo(moocUserT);
        //返回UserInfoModel
        return userInfoModel;
    }


    @Override
    public UserInfoModel updateUserInfo(UserInfoModel userInfoModel) {
        //将传入的数据转换为MoocUserT
        MoocUserT moocUserT = new MoocUserT();
        moocUserT.setUuid(userInfoModel.getUuid());
        moocUserT.setNickName(userInfoModel.getNickname());
        moocUserT.setLifeState(Integer.parseInt(userInfoModel.getLifeState()));
        moocUserT.setBirthday(userInfoModel.getBirthday());
        moocUserT.setBiography(userInfoModel.getBiography());
        moocUserT.setBeginTime(time2Date(userInfoModel.getBeginTime()));
        moocUserT.setHeadUrl(userInfoModel.getHeadAddress());
        moocUserT.setEmail(userInfoModel.getEmail());
        moocUserT.setAddress(userInfoModel.getAddress());
        moocUserT.setUserPhone(userInfoModel.getPhone());
        moocUserT.setUserSex(userInfoModel.getGender());
        moocUserT.setUpdateTime(new Date());
        //将数据存入数据
        Integer update = moocUserTMapper.updateById(moocUserT);
        if (update > 0) {
            //按照Id将用户信息查出来
            UserInfoModel userInfo = getUserInfo(moocUserT.getUuid());
            //返回给前端
            return userInfo;
        }
        return userInfoModel;
    }

    /**
     * 实体转换-->MoocUserT ==>UserInfoModel
     *
     * @param moocUserT
     * @return
     */
    private UserInfoModel do2UserInfo(MoocUserT moocUserT) {
        UserInfoModel userInfoModel = new UserInfoModel();

        userInfoModel.setHeadAddress(moocUserT.getHeadUrl());
        userInfoModel.setPhone(moocUserT.getUserPhone());
        userInfoModel.setUpdateTime(moocUserT.getUpdateTime().getTime());
        userInfoModel.setEmail(moocUserT.getEmail());
        userInfoModel.setUsername(moocUserT.getUserName());
        userInfoModel.setNickname(moocUserT.getNickName());
        userInfoModel.setLifeState(moocUserT.getLifeState() + "");
        userInfoModel.setBirthday(moocUserT.getBirthday());
        userInfoModel.setAddress(moocUserT.getAddress());
        userInfoModel.setGender(moocUserT.getUserSex());
        userInfoModel.setBeginTime(moocUserT.getBeginTime().getTime());
        userInfoModel.setBiography(moocUserT.getBiography());

        return userInfoModel;
    }

    /**
     * 时间转换
     *
     * @param time
     * @return
     */
    private Date time2Date(long time) {
        Date date = new Date(time);
        return date;
    }
}

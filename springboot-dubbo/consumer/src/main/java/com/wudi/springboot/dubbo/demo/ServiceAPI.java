package com.wudi.springboot.dubbo.demo;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2020/11/7 17:21
 */
public interface ServiceAPI {

    /**
     * 返回信息
     * @param message
     * @return
     */
    String sendMessage(String message);
}

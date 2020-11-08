package com.wudi.dubbo.provider.service;

/**
 * @author Dillon Wu
 * @Description:xml方式服务提供者实现类
 * @date 2020/11/6 14:26
 */
public class ProviderServiceImpl implements ProviderService {

    public String SayHello(String word) {
        return word;
    }
}

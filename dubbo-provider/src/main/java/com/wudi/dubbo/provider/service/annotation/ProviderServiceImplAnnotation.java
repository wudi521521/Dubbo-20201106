package com.wudi.dubbo.provider.service.annotation;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2020/11/7 12:16
 */
@Service(timeout=500)//@Service用来配置Dubbo的服务提供方
public class ProviderServiceImplAnnotation implements ProviderServiceAnnotation {
    public String SayHelloAnnotation(String word) {
        return word;
    }
}

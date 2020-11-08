package com.wudi.dubbo.consumer.Annotation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wudi.dubbo.provider.service.annotation.ProviderServiceAnnotation;
import org.springframework.stereotype.Component;

/**
 * @author Dillon Wu
 * @Description:注解方式的service
 * @date 2020/11/7 12:37
 */
@Component("annotatedConsumer")
public class ConsumerAnnotationService {
    @Reference
    private ProviderServiceAnnotation providerServiceAnnotation;

    public String doSayHello(String name) {
        return providerServiceAnnotation.SayHelloAnnotation(name);
    }
}

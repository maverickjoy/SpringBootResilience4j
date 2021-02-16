package com.demo.project.SpringBootResilience4j.service.feign.client;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class DownStreamServiceInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("key", "value");
        log.warn("We are using custom interceptor");
    }
}

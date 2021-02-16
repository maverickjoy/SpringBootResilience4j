package com.demo.project.SpringBootResilience4j.service.feign.config;

import com.demo.project.SpringBootResilience4j.service.feign.client.DownStreamServiceInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DownStreamServiceConfig {

    @Bean
    public RequestInterceptor downStreamServiceInterceptor() {
        return new DownStreamServiceInterceptor();
    }

}

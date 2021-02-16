package com.demo.project.SpringBootResilience4j.service.feign.impl;

import com.demo.project.SpringBootResilience4j.model.User;
import com.demo.project.SpringBootResilience4j.service.feign.SampleFeignService;
import com.demo.project.SpringBootResilience4j.service.feign.client.ServiceEndPointFeignClient;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SampleFeignServiceImpl implements SampleFeignService {

    private final String DOWNSTREAM_SERVICE_FEIGN = "downStreamServiceFeign";

    @Autowired
    private ServiceEndPointFeignClient serviceEndPointFeignClient;

    @Override
    @RateLimiter(name = DOWNSTREAM_SERVICE_FEIGN, fallbackMethod = "tooManyBackendFeignPostCalls")
    public User saveUser(User user) {
        log.info("Saving user through feign : {}", user.toString());
        serviceEndPointFeignClient.dummyPost(user);
        return user;
    }

    @Override
    public String getEmailById(int id) {
        log.info("Getting user through feign : {}", id);
        serviceEndPointFeignClient.dummyGet("joy", 3);
        return "email";
    }


    public User tooManyBackendFeignPostCalls(User user, Exception e) {
        log.info("Too many calls at backend feign post calls");
        return null;
    }

    public String tooManyBackendGetCalls(int id, Exception e) {
        log.info("Too many calls at backend get calls");
        return null;
    }
}

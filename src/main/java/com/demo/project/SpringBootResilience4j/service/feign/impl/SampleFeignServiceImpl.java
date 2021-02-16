package com.demo.project.SpringBootResilience4j.service.feign.impl;

import com.demo.project.SpringBootResilience4j.model.User;
import com.demo.project.SpringBootResilience4j.service.feign.SampleFeignService;
import com.demo.project.SpringBootResilience4j.service.feign.client.ServiceEndPointFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SampleFeignServiceImpl implements SampleFeignService {

    @Autowired
    private ServiceEndPointFeignClient serviceEndPointFeignClient;

    @Override
    public User saveUser(User user) {
        log.info("Saving user through feign : {}", user.toString());
        serviceEndPointFeignClient.dummyPost(user);
        return user;
    }

}

package com.demo.project.SpringBootResilience4j.service.feign;

import com.demo.project.SpringBootResilience4j.model.User;

public interface SampleFeignService {
    User saveUser(User user);
}

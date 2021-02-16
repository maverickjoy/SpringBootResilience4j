package com.demo.project.SpringBootResilience4j.service.feign.client;

import com.demo.project.SpringBootResilience4j.model.User;
import com.demo.project.SpringBootResilience4j.service.feign.config.DownStreamServiceConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "${feign.name}", url = "${feign.url}", configuration = DownStreamServiceConfig.class)
public interface ServiceEndPointFeignClient {

    @PostMapping(value = "/api/sample/dummyPost")
    User dummyPost(@RequestBody User user);

    @GetMapping(value = "/api/sample/dummyGet/{name}")
    String dummyGet(@PathVariable("name") String name, @RequestParam int id);
}

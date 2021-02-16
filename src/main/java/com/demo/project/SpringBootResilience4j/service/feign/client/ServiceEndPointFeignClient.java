package com.demo.project.SpringBootResilience4j.service.feign.client;

import com.demo.project.SpringBootResilience4j.model.User;
import com.demo.project.SpringBootResilience4j.service.feign.config.DownStreamServiceConfig;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "${feign.name}", url = "${feign.url}", configuration = DownStreamServiceConfig.class)
public interface ServiceEndPointFeignClient {

    final String DOWNSTREAM_SERVICE = "downStreamService";

    @PostMapping(value = "/api/sample/dummyPost")
    User dummyPost(@RequestBody User user);

    // Since we haven't added the fallback method here what will happen is it will use the fallback method
    // for last nearest Rate limiter written for this method call stack.
    @RateLimiter(name = DOWNSTREAM_SERVICE)
    @GetMapping(value = "/api/sample/dummyGet/{name}")
    String dummyGet(@PathVariable("name") String name, @RequestParam int id);
}

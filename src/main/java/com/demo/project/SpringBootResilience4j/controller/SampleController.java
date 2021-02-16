package com.demo.project.SpringBootResilience4j.controller;

import com.demo.project.SpringBootResilience4j.model.User;
import com.demo.project.SpringBootResilience4j.service.SampleService;
import com.demo.project.SpringBootResilience4j.service.feign.SampleFeignService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/sample")
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @Autowired
    private SampleFeignService sampleFeignService;

    private final String BACKEND_SERVICE = "backendService";

    @GetMapping(value = "/getServiceName")
    public String getName() {
        return sampleService.getServiceName();
    }

    @GetMapping(value = "/getNameById")
    public String getName(@RequestParam int id) {
        return sampleService.getUserNameById(id);
    }

    @RateLimiter(name = BACKEND_SERVICE, fallbackMethod = "tooManyCallsSave")
    @PostMapping(value = "/saveUser")
    public ResponseEntity saveUser(@RequestBody User user) {
        log.info("Save user controller called");
        sampleService.saveUser(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RateLimiter(name = BACKEND_SERVICE, fallbackMethod = "tooManyCallsGet")
    @GetMapping(value = "/getEmailById")
    public ResponseEntity getEmail(@RequestParam int id) {
        log.info("Email by id controller called");
        return ResponseEntity.ok().body(sampleService.getEmailById(id));
    }

    @RateLimiter(name = BACKEND_SERVICE, fallbackMethod = "tooManyCallsGet")
    @GetMapping(value = "/getEmailByIdFeign")
    public ResponseEntity getEmailByIdFeign(@RequestParam int id) {
        return ResponseEntity.ok().body(sampleFeignService.getEmailById(id));
    }

    @RateLimiter(name = BACKEND_SERVICE, fallbackMethod = "tooManyCallsSave")
    @PostMapping(value = "/saveUserFeign")
    public ResponseEntity saveUserFeign(@RequestBody User user) {
        sampleFeignService.saveUser(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // ---------------------- Internal Usages ----------------------------

    @PostMapping(value = "/dummyPost")
    public User  dummyPost(@RequestBody User user) {
        log.info("Dummy Post End Point Called with following user {} input", user.toString());
        return user;
    }

    /*
        PathVariable : http://my-rest-url.org/rest/account/2 => http://my-rest-url.org/rest/account/{account} -> account value becomes 2 | url parameters
        RequestParam :  http://my-rest-url.org/rest/account?name=joy | query parameters
     */
    @GetMapping(value = "/dummyGet/{name}")
    public String dummyGet(@PathVariable("name") String name, @RequestParam int id) {
        log.info("Dummy Get End Point Called with following name {} and id {}", name, id);
        return name;
    }

    public ResponseEntity tooManyCallsSave(User user, Exception e) {
        log.info("Rate limit applied no further calls are accepted for POST method");
        log.warn("Request >> {}", user.toString());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Retry-After", "1"); //retry after one second

        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .headers(responseHeaders) //send retry header
                .body("Too many request - No further calls are accepted");
    }

    public ResponseEntity tooManyCallsGet(Exception e) {
        log.info("Rate limit applied no further calls are accepted for GET method");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Retry-After", "1"); //retry after one second

        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .headers(responseHeaders) //send retry header
                .body("Too many request - No further calls are accepted");
    }

}

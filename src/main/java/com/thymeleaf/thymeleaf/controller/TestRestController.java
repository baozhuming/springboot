package com.thymeleaf.thymeleaf.controller;

import com.thymeleaf.thymeleaf.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {
    @Autowired
    DemoService demoService;

    @RequestMapping(value = "/testRest", produces = "text/plain;charset=UTF-8")
    public @ResponseBody String testRest(){
        return demoService.saySomething();
    }
}

package com.wanda.test.executor.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class IndexController {

    @RequestMapping("/")
    @ResponseBody
    String index() {
        return "test job executor running.";
    }

}
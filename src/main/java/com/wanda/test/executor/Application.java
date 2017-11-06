package com.wanda.test.executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.wanda.test.executor")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}

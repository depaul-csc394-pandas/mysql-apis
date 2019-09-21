package org.pandadevs.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@RequestMapping("api")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
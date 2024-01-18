package com.kani.e_mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(EMailApplication.class, args);
    }
}

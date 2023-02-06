package com.projects.majorProjects;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class userApplication {
    public static void main(String[] args){
        SpringApplication.run(userApplication.class,args);
    }

}

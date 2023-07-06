package com.example.games;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableDiscoveryClient
public class GamesApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamesApplication.class, args);
//        System.out.println("Hello Java Spring!");
    }
}

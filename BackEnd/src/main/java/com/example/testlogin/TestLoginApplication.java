package com.example.testlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TestLoginApplication {

  public static void main(String[] args) {
    SpringApplication.run(TestLoginApplication.class, args);
  }
}

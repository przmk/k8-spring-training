package com.justpz.springbootk8training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringBootK8TrainingApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootK8TrainingApplication.class, args);
  }

}

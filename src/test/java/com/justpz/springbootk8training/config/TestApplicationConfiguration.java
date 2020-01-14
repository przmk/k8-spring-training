package com.justpz.springbootk8training.config;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestApplicationConfiguration {
  @Bean
  Clock getClock(){
    return Clock.fixed(Instant.parse("2019-08-19T16:45:42.00Z"), ZoneId.of("UTC"));
  }
}

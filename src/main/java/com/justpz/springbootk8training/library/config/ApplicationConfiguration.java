package com.justpz.springbootk8training.library.config;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ApplicationConfiguration {

  @Bean
  Clock getClock() {
    return Clock.systemDefaultZone();
  }
}

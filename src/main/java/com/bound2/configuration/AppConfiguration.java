package com.bound2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Semaphore;

@Configuration
public class AppConfiguration {

    @Bean
    public Semaphore webDriverSemaphore(WebDriverProperties properties) {
        return new Semaphore(properties.concurrentDriverCount());
    }
}

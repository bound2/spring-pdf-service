package com.bound2.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "bound2.web-driver")
@ConstructorBinding
public record WebDriverProperties(Integer concurrentDriverCount) {
}

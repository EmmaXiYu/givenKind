package org.givenkind.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration @Profile("dev") @PropertySource("classpath:properties/dev.properties")
public class DevConfig {

}

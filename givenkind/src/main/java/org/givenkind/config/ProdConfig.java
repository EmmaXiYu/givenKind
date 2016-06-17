package org.givenkind.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration @Profile("prod") @PropertySource("classpath:properties/prod.properties")
public class ProdConfig {

}

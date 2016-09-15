package com.markus.app;


import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource(value = "classpath:messages.properties")
public class AppConfig {
	
	@Bean
	public static PropertyPlaceholderConfigurer getPlaceHolderConfigurer() {
		return new PropertyPlaceholderConfigurer();
	}
}

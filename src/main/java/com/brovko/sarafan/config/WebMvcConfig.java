package com.brovko.sarafan.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Bean
	public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerCustomizer() {
		return container -> {
			container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/"));
		};
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedOrigins("http://localhost:8080", "http://sarafan.brovko.tech");
	}
}

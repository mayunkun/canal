package com.cn.canal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CanalApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CanalApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CanalApplication.class);
	}
	
	@Bean
	public ServletWebServerFactory servletContainer() {
		UndertowServletWebServerFactory undertow = new UndertowServletWebServerFactory();
		return undertow;
	}
	
}

package com.example.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// @Bean
	// public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer()
	// 		throws UnknownHostException {
	// 	InetAddress address = InetAddress.getByName("192.168.0.39");

	// 	return (WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>) factory -> {
	// 		factory.setAddress(address);
	// 		factory.setPort(3000);
	// 	};
	// }
}

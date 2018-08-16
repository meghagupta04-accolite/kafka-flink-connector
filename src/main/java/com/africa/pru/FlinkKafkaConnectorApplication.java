package com.africa.pru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages={"com.africa.pru"})
@SpringBootApplication
public class FlinkKafkaConnectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlinkKafkaConnectorApplication.class, args);
	}
}

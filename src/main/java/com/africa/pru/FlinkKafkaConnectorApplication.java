package com.africa.pru;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import com.africa.pru.connector.KafkaReader;

@ComponentScan(basePackages={"com.africa.pru.connector"})
@SpringBootApplication
public class FlinkKafkaConnectorApplication {

	@Autowired
	private static KafkaReader readConnector;
		
	public static void main(String[] args) throws Exception {
		SpringApplication.run(FlinkKafkaConnectorApplication.class, args);
		
		readConnector.kafkaReader();
		
	}
}

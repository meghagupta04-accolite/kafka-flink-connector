package com.africa.pru.connector;

import java.util.Properties;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer082;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaReader {
	
	@Value("${topic}")
	private static String topic;
	@Value("${bootstrap.server}")
	private static String bootstrapServer;
	@Value("${zookeeper.connect}")
	private static String zookeeperConnect;
	@Value("${group.id}")
	private static String groupId;

	
	public static void kafkaReader() throws Exception {
		// create execution environment
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		Properties prop = new Properties();
		prop.setProperty("bootstrap.servers",bootstrapServer);
		prop.setProperty("zookeeper.connect", zookeeperConnect);
		prop.setProperty("group.id",groupId);

		DataStream<String> messageStream = env.addSource(new FlinkKafkaConsumer082<>(topic, new SimpleStringSchema(), prop));
		messageStream.print();
		messageStream.writeAsText("result").setParallelism(1);
		
		env.execute();
	}
}

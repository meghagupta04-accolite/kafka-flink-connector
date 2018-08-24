package com.africa.pru.connector;

import java.io.File;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.TypeExtractor;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.KafkaSink;
import org.apache.flink.streaming.util.serialization.DeserializationSchema;
import org.apache.flink.streaming.util.serialization.SerializationSchema;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaWriter {

	@Value("${bootstrap.server}")
	private static String bootstrapServer;

	@Value("${topic}")
	private static String topic;


	public static void	writeToKafka() throws Exception{
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		File file = new File("ILFlatFile.txt");
		String path = file.getAbsolutePath();

		DataStream<String> sourceData = env.readTextFile(path).setParallelism(1);
		sourceData.addSink(new KafkaSink<>(bootstrapServer, topic, new SimpleStringSchema()));

		env.execute();
	}

	public static class SimpleStringSchema implements DeserializationSchema<String>, SerializationSchema<String, byte[]> {
		private static final long serialVersionUID = 1L;

		public SimpleStringSchema() {
		}

		public String deserialize(byte[] message) {
			return new String(message);
		}

		public boolean isEndOfStream(String nextElement) {
			return false;
		}

		public byte[] serialize(String element) {
			return element.getBytes();
		}

		public TypeInformation<String> getProducedType() {
			return TypeExtractor.getForClass(String.class);
		}
	}
}
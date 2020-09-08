package com.test.amqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.SendTo;

@SpringBootApplication
@EnableBinding(Processor.class)
public class AmqpApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmqpApplication.class, args);
	}
	@Bean
	public TextPlainMessageConverter providesTextPlainMessageConverter() {
		return new TextPlainMessageConverter();
	}

	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public LogMessage enrichLogMessage(LogMessage log) {
		return new LogMessage(String.format("[1]: %s", log.getMessage()));
	}
}

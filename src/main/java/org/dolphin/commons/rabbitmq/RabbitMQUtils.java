package org.dolphin.commons.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQUtils {
	public static Connection getConnection() throws Exception {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("127.0.0.1");
		//amqp
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("yaokai");
		connectionFactory.setPassword("yaokai");
		connectionFactory.setVirtualHost("/yaokai");
		return connectionFactory.newConnection();
	}
}

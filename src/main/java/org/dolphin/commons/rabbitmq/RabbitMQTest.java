package org.dolphin.commons.rabbitmq;

import java.math.BigDecimal;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class RabbitMQTest {
	private static final String QUEUE_NAME="simplequeue";
	public static void main(String[] args) throws Exception {
		Connection connection = RabbitMQUtils.getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String msg = "hello world 123";
		channel.basicPublish("", QUEUE_NAME, null, msg.getBytes("UTF-8"));
		channel.close();
		connection.close();
		BigDecimal a1 = new BigDecimal(98);
		BigDecimal a2 = new BigDecimal(99);
		System.out.println(a1.subtract(a2));
	}

}

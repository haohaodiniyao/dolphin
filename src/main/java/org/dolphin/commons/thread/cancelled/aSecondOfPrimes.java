package org.dolphin.commons.thread.cancelled;

import java.util.concurrent.TimeUnit;

public class aSecondOfPrimes {

	public static void main(String[] args) {
		PrimeGenerator generator = new PrimeGenerator();
		Thread t = new Thread(generator);
		t.start();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			generator.cancel();
		}
		System.out.println(generator.get().size());
		System.out.println("线程状态->"+t.isAlive());
		
		redis.clients.jedis.JedisPoolConfig
	}

}

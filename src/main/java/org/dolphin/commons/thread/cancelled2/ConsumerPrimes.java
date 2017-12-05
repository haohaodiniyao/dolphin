package org.dolphin.commons.thread.cancelled2;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ConsumerPrimes {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<BigInteger> primes = new LinkedBlockingQueue<BigInteger>(5);
		BrokenPrimeProducer producer = new BrokenPrimeProducer(primes);
		producer.start();
		TimeUnit.SECONDS.sleep(5);
		try{
			for(int i=1;i<20;i++){
				System.out.println("consumer->"+primes.take());
				TimeUnit.SECONDS.sleep(1);
			}
		}finally{
			producer.cancel();
		}
		System.out.println("线程状态->"+producer.isAlive());
		TimeUnit.SECONDS.sleep(50);
	}

}

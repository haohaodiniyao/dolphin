package org.dolphin.commons.thread.cancelled2;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

public class BrokenPrimeProducer extends Thread {
	private final BlockingQueue<BigInteger> queue;
	private volatile boolean cancelled = false;

	public BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			BigInteger p = BigInteger.ONE;
			while (!cancelled) {
				p = p.nextProbablePrime();
				System.out.println("producer p="+p);
				queue.put(p);
			}
		} catch (InterruptedException e) {

		}
	}
	public void cancel(){
		cancelled = true;
	}
}

package org.dolphin.commons.thread.cancelled3;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

import javax.annotation.concurrent.GuardedBy;
/**
 * 线程中断
 * @author yaokai
 *
 */
public class BrokenPrimeProducer2 extends Thread {
	@GuardedBy("")
	private final BlockingQueue<BigInteger> queue;
//	private volatile boolean cancelled = false;

	public BrokenPrimeProducer2(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			BigInteger p = BigInteger.ONE;
			while (!Thread.currentThread().isInterrupted()) {
				p = p.nextProbablePrime();
				System.out.println("producer p="+p);
				queue.put(p);
			}
		} catch (InterruptedException e) {

		}
	}
	public void cancel(){
		interrupt();
	}
}

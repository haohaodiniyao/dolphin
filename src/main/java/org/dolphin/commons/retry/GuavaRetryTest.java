package org.dolphin.commons.retry;

import java.util.concurrent.Executors;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
/**
 * guava retry test
 * @author yaokai
 *
 */
public class GuavaRetryTest { 
	final static ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(6));
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			executorService.submit(new MyTaskRetry(i));
		}
	}
}

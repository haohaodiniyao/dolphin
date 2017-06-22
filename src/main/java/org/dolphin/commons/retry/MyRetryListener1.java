package org.dolphin.commons.retry;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;

public class MyRetryListener1 implements RetryListener {

	@Override
	public <V> void onRetry(Attempt<V> attempt) {
		System.out.println("监听1->"+Thread.currentThread());
	}

}

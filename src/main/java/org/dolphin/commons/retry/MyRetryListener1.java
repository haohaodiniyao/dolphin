package org.dolphin.commons.retry;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;

public class MyRetryListener<Boolean> implements RetryListener {

	@Override
	public <Boolean> void onRetry(Attempt<Boolean> attempt) {
		System.out.println("重试码:"+attempt.getAttemptNumber());
	}

}

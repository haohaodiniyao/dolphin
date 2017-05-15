package org.dolphin.commons.retry;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicates;

public class GuavaRetryTest {
	private static Callable<Boolean> maySuccessTask = new Callable<Boolean>(){

		@Override
		public Boolean call() throws Exception {
			 
			return false;
		}
		
	};
	public static void main(String[] args) {
		Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
		.retryIfResult(Predicates.equalTo(false))
		.retryIfException()
		.withWaitStrategy(WaitStrategies.fixedWait(5, TimeUnit.SECONDS))
		.withStopStrategy(StopStrategies.stopAfterAttempt(5))
		.withRetryListener(new MyRetryListener<Boolean>())
		.build();
		try {
			retryer.call(maySuccessTask);
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (RetryException e) {
			e.printStackTrace();
		}

	}

}

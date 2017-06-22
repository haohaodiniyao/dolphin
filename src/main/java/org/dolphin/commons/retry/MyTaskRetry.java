package org.dolphin.commons.retry;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicates;
/**
 * 任务重试模块
 * @author yaokai
 *
 */
public class MyTaskRetry implements Callable<Boolean> {
	
	private int index;
	
	public MyTaskRetry(int index) {
		this.index = index;
	}

	@Override
	public Boolean call() {
		Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
				.retryIfResult(Predicates.equalTo(false))
				.retryIfExceptionOfType(IOException.class)
				.retryIfRuntimeException()
				.retryIfException()
				/* 每隔5秒重试 */
				.withWaitStrategy(WaitStrategies.fixedWait(5, TimeUnit.SECONDS))
				/* 最多重试5次 */
				.withStopStrategy(StopStrategies.stopAfterAttempt(5))
				/* 多个按照注册顺序执行  */
				.withRetryListener(new MyRetryListener1())
				.withRetryListener(new MyRetryListener2())
				.build();
		try {
			retryer.call(new MyTaskProcess(index));
		} catch (ExecutionException e) {
			System.out.println("重试执行异常...");
			e.printStackTrace();
		} catch (RetryException e) {
			System.out.println("重试异常...");
			e.printStackTrace();
		}
		return true;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}

package org.dolphin.commons.disruptor;

import com.lmax.disruptor.ExceptionHandler;

public class LogExceptionHandler1 implements ExceptionHandler<LogEvent>{

	@Override
	public void handleEventException(Throwable paramThrowable, long paramLong, LogEvent paramT) {
		System.out.println("处理异常..."+paramT.getValue());
	}

	@Override
	public void handleOnStartException(Throwable paramThrowable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleOnShutdownException(Throwable paramThrowable) {
		// TODO Auto-generated method stub
		
	}

}

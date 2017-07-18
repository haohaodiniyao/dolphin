package org.dolphin.commons.disruptor;

public class LogTask implements Runnable {
	private LogEventProducer producer;
	private long i;
	private LogData logData;
	
	public LogTask(LogEventProducer producer,long i, LogData logData) {
		this.producer = producer;
		this.i = i;
		this.logData = logData;
	}

	@Override
	public void run() {
		System.out.println("start push "+i);
		long start = System.currentTimeMillis();
		producer.onData(i, logData);
		System.out.println("end push "+i+",耗时->"+(System.currentTimeMillis()-start)+"毫秒");
	}

}

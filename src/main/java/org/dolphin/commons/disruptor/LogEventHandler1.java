package org.dolphin.commons.disruptor;

import java.util.Random;

import com.lmax.disruptor.EventHandler;

/**
 * 
 * @author yaokai
 *
 */
public class LogEventHandler1 implements EventHandler<LogEvent> {
	@Override
	public void onEvent(LogEvent event, long sequence, boolean endOfBatch) throws Exception {
		try{
//			long begin = System.currentTimeMillis();
//			int random = new Random().nextInt(10);
//			if(random>5){
//				Thread.sleep(100);
//			}else{
//				throw new Exception();
//			}
//			long end = System.currentTimeMillis();
//			System.out.println(Thread.currentThread()+",sequence:"+sequence+",endOfBatch:"+endOfBatch+",发送短息"+event.getValue()+",耗时"+(end-begin)/1000);
			System.out.println("处理...");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
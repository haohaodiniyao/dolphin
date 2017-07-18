package org.dolphin.commons.disruptor;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;
/**
 * 
 * @author yaokai
 *
 */
public class LogEventMain {
	private static ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
    public static void main(String[] args) throws InterruptedException {
        //构造Disruptor需要用到的线程池
        ExecutorService executor = Executors.newCachedThreadPool();
//事件工厂
        LogEventFactory factory = new LogEventFactory();
//RingBuffer 大小
        int bufferSize = 1024;
//构造Disruptor
        Disruptor<LogEvent> disruptor = new Disruptor<LogEvent>(factory, bufferSize, executor);
//添加时间处理handler
        EventHandlerGroup<LogEvent> handlerGroup = disruptor.handleEventsWith(new LogEventHandler2());
//        handlerGroup.then(new LogEventHandler3());

//        EventHandler<LogEvent> handler1 = new LogEventHandler1();
//        EventHandler<LogEvent> handler2 = new LogEventHandler2();
//        disruptor.handleEventsWith(handler1, handler2);
//        disruptor.after(handler1, handler2).handleEventsWith(new LogEventHandler3());
//启动Disruptor，启动所有线程
        disruptor.start();
//获取Disruptor的RingBuffer对象，用于发布日志
        RingBuffer<LogEvent> ringBuffer = disruptor.getRingBuffer();

        LogEventProducer producer = new LogEventProducer(ringBuffer);

//        ByteBuffer bb = ByteBuffer.allocate(16);
        for (long i = 1; i <= 20000l; i++) {
        	
//            bb.putLong(l);
        	long start = System.currentTimeMillis();
            LogData logData = new LogData();
            logData.setInterSerial(String.valueOf(i));
            logData.setAddTime(new Date());
            executorService.submit(new LogTask(producer,i,logData));
//            Thread.sleep(1000);
        }
        disruptor.shutdown();
        executor.shutdown();
    }
}

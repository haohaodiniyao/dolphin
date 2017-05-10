package org.dolphin.commons.disruptor;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by licheng5 on 2016/5/24.
 */
public class LogEventMain {
    public static void main(String[] args) throws InterruptedException {
        //构造Disruptor需要用到的线程池
        Executor executor = Executors.newCachedThreadPool();
//事件工厂
        LogEventFactory factory = new LogEventFactory();
//RingBuffer 大小
        int bufferSize = 1024;
//构造Disruptor
        Disruptor<LogEvent> disruptor = new Disruptor<LogEvent>(factory, bufferSize, executor);
//添加时间处理handler
        EventHandlerGroup<LogEvent> handlerGroup = disruptor.handleEventsWith(new LogEventHandler1(), new LogEventHandler2());
        handlerGroup.then(new LogEventHandler3());

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
        for (long l = 0; l < 200l; l++) {
//            bb.putLong(l);
            LogData logData = new LogData();
            logData.setInterSerial(String.valueOf(l));
            logData.setAddTime(new Date());
            producer.onData(l, logData); //produce log data
//            Thread.sleep(1000);
        }
        disruptor.shutdown();
    }
}

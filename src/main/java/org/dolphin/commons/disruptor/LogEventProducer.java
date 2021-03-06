package org.dolphin.commons.disruptor;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;
/**
 * 
 * @author yaokai
 *
 */
public class LogEventProducer {

    private final RingBuffer<LogEvent> ringBuffer;

    public LogEventProducer(RingBuffer<LogEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    /**
     * onData用来发布事件，每调用一次就发布一次事件事件
     * 它的参数会通过事件传递给消费者
     *
     * @param value
     */
    public void onData(long value, LogData logData) {
        //可以把ringBuffer看做一个事件队列，那么next就是得到下面一个事件槽
        long sequence = ringBuffer.next();
        try {
            //用上面的索引取出一个空的事件用于填充
            LogEvent logEvent = ringBuffer.get(sequence);
            logEvent.setValue(value);
            logEvent.setLogData(logData);
        } catch(Exception e){
        	e.printStackTrace();
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
package org.dolphin.commons.disruptor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by licheng5 on 2016/5/24.
 */
public class LogEventMainJava8 {
    /**
     * 使用lambda表达式来注册EventHandler和EventProductor
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        // Executor that will be used to construct new threads for consumers
        Executor executor = Executors.newCachedThreadPool();
        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;
        // Construct the Disruptor
        Disruptor<LogEvent> disruptor = new Disruptor<LogEvent>(LogEvent::new, bufferSize, executor);
        // 可以使用lambda来注册一个EventHandler
        disruptor.handleEventsWith(
                (event, sequence, endOfBatch) -> System.out.println("Event: " + event.getValue())
        );
        // Start the Disruptor, starts all threads running
        disruptor.start();
        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer ringBuffer = disruptor.getRingBuffer();

        LogEventProducer producer = new LogEventProducer(ringBuffer);

        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            byteBuffer.putLong(l);
//            ringBuffer.publishEvent((event, sequence, buffer) -> event.setValue(buffer.getLong(0)), byteBuffer);
            Thread.sleep(1000);
        }
    }
}
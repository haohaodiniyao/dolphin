package org.dolphin.commons.disruptor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by licheng5 on 2016/5/25.
 */
public class LogEventWithMethodRef {
    public static void handleEvent(LogEvent event, long sequenct, boolean endOfBatch) {
        System.out.println(event.getValue());
    }

    public static void translate(LogEvent event, long sequenct, ByteBuffer byteBuffer) {
        event.setValue(byteBuffer.getLong(0));
    }

    public static void main(String[] args) throws InterruptedException {
        // Executor that will be used to construct new threads for consumers
        Executor executor = Executors.newCachedThreadPool();
        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;
// Construct the Disruptor
        Disruptor<LogEvent> disruptor = new Disruptor<LogEvent>(LogEvent::new, bufferSize, executor);
        // Connect the handler
        disruptor.handleEventsWith(LogEventWithMethodRef::handleEvent);
        // Start the Disruptor, starts all threads running
        disruptor.start();
        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LogEvent> ringBuffer = disruptor.getRingBuffer();

        LogEventProducer producer = new LogEventProducer(ringBuffer);

        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            byteBuffer.putLong(l);
            ringBuffer.publishEvent(LogEventWithMethodRef::translate, byteBuffer);
            Thread.sleep(1000);
        }
    }
}

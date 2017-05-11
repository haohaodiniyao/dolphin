package org.dolphin.commons.disruptor;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;
/**
 * 
 * @author yaokai
 *
 */
public class LogEventProducerWithTranslator {

    //一个translator可以看做一个事件初始化器，publicEvent方法会调用它填充Event
    private static final EventTranslatorOneArg<LogEvent, ByteBuffer> TRANSLATOR =
            new EventTranslatorOneArg<LogEvent, ByteBuffer>() {
                @Override
                public void translateTo(LogEvent event, long sequence, ByteBuffer byteBuffer) {
                    event.setValue(byteBuffer.getLong(0));
                }
            };

    private final RingBuffer<LogEvent> ringBuffer;

    public LogEventProducerWithTranslator(RingBuffer<LogEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer byteBuffer) {
        ringBuffer.publishEvent(TRANSLATOR, byteBuffer);
    }

}

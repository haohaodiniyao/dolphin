package org.dolphin.commons.disruptor;
import com.lmax.disruptor.EventFactory;
/**
 * 
 * @author yaokai
 *
 */
public class LogEventFactory implements EventFactory<LogEvent> {
    @Override
    public LogEvent newInstance() {
        return new LogEvent();
    }
}
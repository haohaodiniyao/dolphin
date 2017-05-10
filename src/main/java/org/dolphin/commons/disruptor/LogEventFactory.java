package org.dolphin.commons.disruptor;
import com.lmax.disruptor.EventFactory;

/**
 * Created by licheng5 on 2016/5/24.
 */
public class LogEventFactory implements EventFactory<LogEvent> {
    @Override
    public LogEvent newInstance() {
        return new LogEvent();
    }
}
package org.dolphin.commons.disruptor;
import com.lmax.disruptor.EventHandler;

/**
 * Created by licheng5 on 2016/6/13.
 */
public class LogEventHandler2 implements EventHandler<LogEvent> {
    @Override
    public void onEvent(LogEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Handle with handler2, sequence:" + sequence + ",endOfBatch:" + endOfBatch + ",event:" + event);
    }
}
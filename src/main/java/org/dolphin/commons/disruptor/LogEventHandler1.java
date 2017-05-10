package org.dolphin.commons.disruptor;
import com.lmax.disruptor.EventHandler;

/**
 * Created by licheng5 on 2016/5/24.
 */
public class LogEventHandler1 implements EventHandler<LogEvent> {
    @Override
    public void onEvent(LogEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Handle with handler1, sequence:" + sequence + ",endOfBatch:" + endOfBatch + ",event:" + event); //do what you want
    }
}
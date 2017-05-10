package org.dolphin.commons.disruptor;
/**
 * Created by licheng5 on 2016/5/24.
 */
public class LogEvent {

    private long value;

    private LogData logData;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public LogData getLogData() {
        return logData;
    }

    public void setLogData(LogData logData) {
        this.logData = logData;
    }

    @Override
    public String toString() {
        return "LogEvent{" +
                "value=" + value +
                ", logData=" + logData +
                '}';
    }
}

package org.dolphin.commons.disruptor;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.lmax.disruptor.EventHandler;
/**
 * 
 * @author yaokai
 *
 */
public class LogEventHandler2 implements EventHandler<LogEvent> {
	ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
    @Override
    public void onEvent(LogEvent event, long sequence, boolean endOfBatch) throws Exception {
		final ListenableFuture<String> listenableFuture = executorService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				long begin = System.currentTimeMillis();
				int random = new Random().nextInt(8);
				Thread.sleep(random * 1000);
				long end = System.currentTimeMillis();
				System.out.println(Thread.currentThread()+",sequence:"+sequence+",endOfBatch:"+endOfBatch+",发送短信"+event.getValue()+",耗时"+(end-begin)/1000);
				return "";
			}
		});
		Futures.addCallback(listenableFuture, new FutureCallback<String>() {
			@Override
			public void onSuccess(String result) {

			}

			@Override
			public void onFailure(Throwable t) {

			}
		});
    }
}
package org.dolphin.commons.thread.executorService;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorCompletionServiceTest {

	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(5);
		CompletionService<String> cs = new ExecutorCompletionService<String>(es);
		for(int i=1;i<5;i++){
			cs.submit(new MyCallable(String.valueOf(i)));	
		}
		try {
			Future<String> f = cs.take();
			while(f!=null){
				try {
					f.cancel(false);
					System.out.println(f.get());
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				f = cs.take();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

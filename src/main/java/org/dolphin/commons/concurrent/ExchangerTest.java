package org.dolphin.commons.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 线程间数据交换Exchanger
 * @author yaokai
 *
 */
public class ExchangerTest {
	private static final Exchanger<String> exgr = new Exchanger<String>();
	private static ExecutorService es = Executors.newCachedThreadPool();
	public static void main(String[] args) {
		es.submit(new Runnable(){

			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				String a = "hello1";
				try {
					String b = exgr.exchange(a);
					System.out.println(a+"->"+b);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		es.submit(new Runnable(){

			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				String a = "hello2";
				try {
					String b = "";
					try {
						b = exgr.exchange(a,5,TimeUnit.SECONDS);
					} catch (TimeoutException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(a+"->"+b);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		});	
		
		es.submit(new Runnable(){

			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				String a = "hello3";
				try {
					String b = exgr.exchange(a);
					System.out.println(a+"->"+b);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		});		
	}

}

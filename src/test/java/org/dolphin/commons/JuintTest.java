package org.dolphin.commons;

import org.dolphin.commons.hystrix.CommandHelloWorld;
import org.junit.Test;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

public class JuintTest {

	@Test
	public void test() throws Exception {
//		System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm"));
//		System.out.println(DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.format(new Date()));
//		System.out.println(DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date()));
//		System.out.println(DateFormatUtils.format(DateUtils.addYears(new Date(), -1), "yyyy-MM-dd HH:mm"));
//		System.out.println(String.format("%1$s%%%2$s", "8888","9999"));
//		System.out.println(new CommandHelloWorld("World").execute());
//		Future<String> fs = new CommandHelloWorld("World").queue();
//		try {
//			System.out.println(fs.get(5,TimeUnit.SECONDS));
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (TimeoutException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		Observable<String> fs = new CommandHelloWorld("World").observe();
//		fs.subscribe(new Action1<String>(){
//
//			@Override
//			public void call(String result) {
//				System.out.println(result);
//			}
//			
//		});
//		fs.subscribe(new Observer<String>(){
//
//			@Override
//			public void onCompleted() {
//				System.out.println("execute onCompleted");
//			}
//
//			@Override
//			public void onError(Throwable e) {
//				System.out.println("on Error "+e.getMessage());
//				e.printStackTrace();
//			}
//
//			@Override
//			public void onNext(String v) {
//				System.out.println("onNext:"+v);
//			}
//			
//		});
//		System.in.read();
//		Integer a = 100;
//		int b = 100;
//		System.out.println(a == b);
		String a = "";
		if(a.length()>=5){
			System.out.println(a.substring(0,5));	
		}else{
			System.out.println(a);
		}
	}

}

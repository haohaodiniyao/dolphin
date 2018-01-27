package org.dolphin.commons;

import org.junit.Test;

public class JuintTest {

	private final String getStr(String b){
		return "a"+"b"+"c";
	}
	
	@Test
	public void test() throws Exception {
		final String str;
		str = "100";
		String str1 = str + "c";
		System.out.println(getStr("")=="abc");
//		String str = "2017-12-10T16:40:00+09:00";
//		System.out.println(str.substring(0, 10));
//		System.out.println(str.substring(11, 16));
		
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
//		String a = new String("账号：201000104560277 开户行：浙江温州鹿城农村商业银行股份有限公司营业部 地址：温州市鹿城区新城宏源路89号4楼 电话：0577-88667855 联行号：402333011011".getBytes(),"UTF-8");
//	
//		System.out.println(new String(a.getBytes("gb2312"),"iso-8859-1").length());
		
	}

}

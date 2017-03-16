package org.dolphin.commons.proxy;

public class MyProxyImp implements MyProxy {

	@Override
	public String sayHello(String msg) {
		System.out.println("msg:"+msg);
		return msg;
	}

}

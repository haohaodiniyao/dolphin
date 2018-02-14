package org.dolphin.commons.proxy.cglib;

import org.dolphin.commons.proxy.HelloService;
import org.dolphin.commons.proxy.HelloServiceImpl;

public class CglibProxyTest {

	public static void main(String[] args) {
		HelloService helloService = new HelloServiceImpl();
		CglibProxy cglibProxy = new CglibProxy();
		HelloService helloServiceProxy = (HelloService)cglibProxy.getInstance(helloService);
		helloServiceProxy.sayHello("zhangsan");
	}

}

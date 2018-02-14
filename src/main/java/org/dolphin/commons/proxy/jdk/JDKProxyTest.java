package org.dolphin.commons.proxy.jdk;

import org.dolphin.commons.proxy.HelloService;
import org.dolphin.commons.proxy.HelloServiceImpl;

public class JDKProxyTest {

	public static void main(String[] args) {
		HelloService helloService = new HelloServiceImpl();
		JDKProxy jdkProxy = new JDKProxy();
		HelloService helloServiceProxy = (HelloService)jdkProxy.bind(helloService);
		helloServiceProxy.sayHello("zhangsan");
	}

}

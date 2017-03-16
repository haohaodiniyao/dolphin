package org.dolphin.commons.proxy;

import java.lang.reflect.Proxy;

public class JdkProxyTest {

	public static void main(String[] args) {
		MyProxyImp jdkProxyImp = new MyProxyImp();
		JdkProxy jdkProxy = new JdkProxy(jdkProxyImp);
		MyProxy myProxy = (MyProxy)Proxy.newProxyInstance(jdkProxyImp.getClass().getClassLoader(), jdkProxyImp.getClass().getInterfaces(), jdkProxy);
		myProxy.sayHello("hello world");
	}

}

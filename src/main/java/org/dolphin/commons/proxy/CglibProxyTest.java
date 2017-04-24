package org.dolphin.commons.proxy;

public class CglibProxyTest {

	public static void main(String[] args) {
		CglibProxy cglibProxy = new CglibProxy(new MyProxyImp());
		MyProxy myProxy = (MyProxy)cglibProxy.getProxy();
		myProxy.sayHello("hello world");
	}

}

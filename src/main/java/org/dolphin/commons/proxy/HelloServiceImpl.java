package org.dolphin.commons.proxy;

public class HelloServiceImpl implements HelloService {

	@Override
	public void sayHello(String name) {
		System.out.println("hello "+name);
	}

}

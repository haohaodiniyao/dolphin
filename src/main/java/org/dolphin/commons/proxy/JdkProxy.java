package org.dolphin.commons.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * jdk proxy
 * @author yaokai
 *
 */
public class JdkProxy implements InvocationHandler{
	private Object target;
	
	public JdkProxy(Object target) {
		super();
		this.target = target;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("call before");
		Object ret = method.invoke(target, args);
		System.out.println("call after");
		return ret;
	}

}

package org.dolphin.commons.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * jdk proxy
 * @author yaokai
 *
 */
public class JDKProxy implements InvocationHandler{
	/**
	 * 真实服务对象
	 */
	private Object target;
	
	public Object bind(Object target){
		this.target = target;
		//jdk代理需要提供接口
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	@Override
	/**
	 * 通过代理对象调用方法首先进入这个方法
	 * proxy-代理对象
	 * method-被调用方法
	 * args-方法参数
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("JDK动态代理");
		Object result = null;
		System.out.println("方法执行前...");
		result = method.invoke(target, args);
		System.out.println("方法执行后...");
		return result;
	}

}

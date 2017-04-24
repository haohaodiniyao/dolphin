package org.dolphin.commons.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
/**
 * Cglib代理
 * @author yaokai
 *
 */
public class CglibProxy implements MethodInterceptor {
	private Object target;
	
	public CglibProxy(Object target) {
		this.target = target;
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		Object res = null;
		Throwable exception = null;
		try {
			System.out.println("my before...");
			res = method.invoke(this.target, args);
			System.out.println("my after...");
		} catch (InvocationTargetException e) {
			exception = e.getTargetException();
			throw exception;
		}
		return res;
	}

	public Object getProxy(){
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
	}
	
	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

}

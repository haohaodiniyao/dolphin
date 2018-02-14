package org.dolphin.commons.proxy.cglib;

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
	/**
	 * 创建代理对象
	 * @param target
	 * @return
	 */
	public Object getInstance(Object target){
		this.target = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());
		//回调方法
		enhancer.setCallback(this);
		//创建代理对象
		return enhancer.create();
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("CGLIB动态代理");
		Object result = null;
		Throwable exception = null;
		try {
			System.out.println("方法执行前...");
			result = proxy.invokeSuper(obj, args);
			System.out.println("方法执行后...");
		} catch (InvocationTargetException e) {
			exception = e.getTargetException();
			throw exception;
		}
		return result;
	}

	public Object getProxy(){
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
	}
}

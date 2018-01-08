package org.dolphin.commons.jdk.jdk_1_5_0.generic;
/**
 * 泛型接口
 * @author yaokai
 *
 * @param <T>
 */
public interface GenericInterface<T> {
	T getMsg(T t);
	void getMsg2(T t);
}

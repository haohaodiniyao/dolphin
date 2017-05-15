package org.dolphin.commons.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
/**
 * hystrix http://www.jianshu.com/p/b9af028efebb
 * @author yaokai
 *
 */
public class CommandHelloWorld extends HystrixCommand<String> {
	private final String name;
	public CommandHelloWorld(String name){
		super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
		this.name = name;
	}
	@Override
	protected String run() throws Exception {
		return "Hello "+name+"!";
	}

}

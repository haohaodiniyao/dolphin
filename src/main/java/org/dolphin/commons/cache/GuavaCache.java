package org.dolphin.commons.cache;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class GuavaCache {
	private static final LoadingCache<String,Object> cache = CacheBuilder.newBuilder()
			//并发级别，同时写缓存的线程数
			.concurrencyLevel(8)
//			.refreshAfterWrite(5, TimeUnit.SECONDS)
			//写缓存后30秒过期
			.expireAfterWrite(30, TimeUnit.SECONDS)
			//访问缓存后30秒过期
			.expireAfterAccess(30, TimeUnit.SECONDS)
			//设置缓存初始容量为10
			.initialCapacity(10)
			 //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
	        .maximumSize(300)
	        //缓存命中率统计
	        .recordStats()
	        //缓存移除监听
	        .removalListener(new RemovalListener<String,Object>(){
				@Override
				public void onRemoval(RemovalNotification<String, Object> removalNotification) {
					System.out.println("缓存key="+removalNotification.getKey()+"被移除,原因"+removalNotification.getCause());
				}
	        	
	        })
	        .build(new CacheLoader<String,Object>(){

		@Override
		public Object load(String key) throws Exception {
			System.out.println("key:"+key+",没有命中缓存...");
			return key;
		}

//		@Override
//		public Map<String, Object> loadAll(Iterable<? extends String> keys) throws Exception {
//			// TODO Auto-generated method stub
//			return super.loadAll(keys);
//		}
//
//		@Override
//		public ListenableFuture<Object> reload(String key, Object oldValue) throws Exception {
//			// TODO Auto-generated method stub
//			return super.reload(key, oldValue);
//		}
		
	});
	public static void main(String[] args) throws Exception {
		cache.put("100", "100");
		Thread.sleep(10*1000);
	}

}

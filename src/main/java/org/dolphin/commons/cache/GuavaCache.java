package org.dolphin.commons.cache;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;

public class GuavaCache {

	public static void main(String[] args) throws Exception {
		LoadingCache<String,Object> cache = CacheBuilder.newBuilder().recordStats().refreshAfterWrite(3, TimeUnit.HOURS).expireAfterAccess(3, TimeUnit.HOURS)
		.maximumSize(150).build(new CacheLoader<String,Object>(){

			@Override
			public Object load(String key) throws Exception {
				if("5".equals(key)){
					throw new Exception();
				}
				System.out.println("key:"+key+",没有命中缓存...");
				return key;
			}

//			@Override
//			public Map<String, Object> loadAll(Iterable<? extends String> keys) throws Exception {
//				// TODO Auto-generated method stub
//				return super.loadAll(keys);
//			}
//
//			@Override
//			public ListenableFuture<Object> reload(String key, Object oldValue) throws Exception {
//				// TODO Auto-generated method stub
//				return super.reload(key, oldValue);
//			}
			
		});
		for(int i=1;i<201;i++){
			cache.put(i+"", i);
		}
		List<String> list = Lists.newArrayList();
		for(int i=1;i<201;i++){
			list.add(i+"");
		}
		Map<String,Object> map = cache.getAll(list);
		System.out.println(map);
		System.out.println(cache.stats());
	}

}

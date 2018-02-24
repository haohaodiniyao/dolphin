package org.dolphin.commons.redis.redisson;

import redis.clients.jedis.Jedis;
/**
 * 
 * @author yaokai
 *
 */
public class JedisTest {
   
	public static void main(String[] args) {
		
		Jedis jedis = new Jedis("127.0.0.1",6379);
	
//		jedis.setex("foo", 5, "bar");
//		System.out.println(jedis.get("foo"));
//		System.out.println("休息5s...");
//		try {
//			Thread.sleep(6*1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println(jedis.get("foo"));
		
//		jedis.set("index", "0");
		jedis.incr("index123");
		System.out.println(jedis.get("index123"));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//Exception in thread "main" redis.clients.jedis.exceptions.JedisDataException: ERR This instance has cluster support disabled
		
//		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
//		jedisClusterNodes.add(new HostAndPort("127.0.0.1",6379));
//		JedisCluster jc = new JedisCluster(jedisClusterNodes);
//		jc.set("hello", "world");
//		System.out.println(jc.get("hello"));
	}

}

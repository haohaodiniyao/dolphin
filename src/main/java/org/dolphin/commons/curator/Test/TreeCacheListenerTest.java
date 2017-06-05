package org.dolphin.commons.curator.Test;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

public class TreeCacheListenerTest {
	private static final String PATH = "/example/cache";

	public static void main(String[] args) {
		CuratorFramework client = null;
		TreeCache treeCache = null;
		try {
			client = CuratorFrameworkFactory.newClient("127.0.0.1:2181",
					new ExponentialBackoffRetry(1000, 3));
			client.start();
			client.create().creatingParentContainersIfNeeded().forPath(PATH);
		    treeCache = new TreeCache(client, PATH);
		    treeCache.start();
			treeCache.getListenable().addListener(new TreeCacheListener() {

				@Override
				public void childEvent(CuratorFramework arg0, TreeCacheEvent arg1) throws Exception {
					System.out.println(arg1);
				}

			});
			Thread.sleep(1000*1000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			CloseableUtils.closeQuietly(treeCache);
			CloseableUtils.closeQuietly(client);
		}
	}

}

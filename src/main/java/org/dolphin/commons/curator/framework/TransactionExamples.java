package org.dolphin.commons.curator.framework;
import java.util.Collection;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;

public class TransactionExamples
{
    public static Collection<CuratorTransactionResult>      transaction(CuratorFramework client) throws Exception
    {
        // this example shows how to use ZooKeeper's transactions

//        CuratorOp createOp = client.transactionOp().create().forPath("/a/path", "some data".getBytes());
//        CuratorOp setDataOp = client.transactionOp().setData().forPath("/another/path", "other data".getBytes());
//        CuratorOp deleteOp = client.transactionOp().delete().forPath("/yet/another/path");
//
//        Collection<CuratorTransactionResult>    results = client.transaction().forOperations(createOp, setDataOp, deleteOp);
//
//        for ( CuratorTransactionResult result : results )
//        {
//            System.out.println(result.getForPath() + " - " + result.getType());
//        }
//
//        return results;
    	return null;
    }
}

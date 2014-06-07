package com.funguide.billing.dao;

import java.io.IOException;  
import java.text.SimpleDateFormat;
import java.util.ArrayList;  
import java.util.List;  
import org.apache.hadoop.conf.Configuration;  
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;  
import org.apache.hadoop.hbase.ZooKeeperConnectionException;  
import org.apache.hadoop.hbase.client.Delete;  
import org.apache.hadoop.hbase.client.HBaseAdmin;  
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.HTablePool;  
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
  
@SuppressWarnings("deprecation")
public class BaseDao {  
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static Configuration configuration;  
	public static HTablePool pool;
    static {  
        configuration = HBaseConfiguration.create();  
        pool = new HTablePool(configuration, 2);
    }  
    
    /** 
     * 创建表 
     */  
    @SuppressWarnings("resource")
	public static void createTable(String tableName) {  
        try {  
            HBaseAdmin hBaseAdmin = new HBaseAdmin(configuration);  
            if (hBaseAdmin.tableExists(tableName)) {// 如果存在要创建的表，那么先删除，再创建  
                hBaseAdmin.disableTable(tableName);  
                hBaseAdmin.deleteTable(tableName);  
            }  
            HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
            tableDescriptor.addFamily(new HColumnDescriptor("details"));  
            hBaseAdmin.createTable(tableDescriptor);  
        } catch (MasterNotRunningException e) {  
            e.printStackTrace();  
        } catch (ZooKeeperConnectionException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
    
    /** 
     * 删除一张表 
     */  
    @SuppressWarnings("resource")
	public static void dropTable(String tableName) {  
        try {  
            HBaseAdmin admin = new HBaseAdmin(configuration);  
            admin.disableTable(tableName);  
            admin.deleteTable(tableName);  
        } catch (MasterNotRunningException e) {  
            e.printStackTrace();  
        } catch (ZooKeeperConnectionException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
    }  
    
    /** 
     * 根据 rowkey删除一条记录 
     */  
     public static void deleteRow(String tableName, String rowkey)  {  
        try {  
        	HTableInterface table = pool.getTable(tableName);
        	List<Delete> list = new ArrayList<Delete>();  
            Delete d1 = new Delete(rowkey.getBytes());  
            list.add(d1);  
            table.delete(list);  
            table.flushCommits();
            pool.putTable(table);
        } catch (IOException e) {  
            e.printStackTrace();  
		}
	}

	/**
	 * 根据 startRowkey和stopRowkey批量删除记录
	 */
	public static void batchDeleteRow(String tableName, String startRow, String stopRow) {
		try {
	    	HTableInterface table = pool.getTable(tableName);
			List<Delete> list = new ArrayList<Delete>();
			Scan scan = new Scan();
            scan.setStartRow(startRow.getBytes());
            scan.setStopRow(stopRow.getBytes());
            ResultScanner rs = table.getScanner(scan);  
            for (Result r : rs) { 
            	Delete d1 = new Delete(r.getRow());
    			list.add(d1);
            }
            rs.close();
            table.delete(list);
			table.flushCommits();
            pool.putTable(table);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
  
}  
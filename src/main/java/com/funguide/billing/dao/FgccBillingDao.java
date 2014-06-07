package com.funguide.billing.dao;

import java.io.IOException;  
import java.util.ArrayList;  
import java.util.List;  
import org.apache.hadoop.hbase.KeyValue;  
import org.apache.hadoop.hbase.client.Get;  
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;  
import org.apache.hadoop.hbase.client.Result;  
import org.apache.hadoop.hbase.client.ResultScanner;  
import org.apache.hadoop.hbase.client.Scan;  
import com.funguide.billing.entity.FgccBilling;
  
public class FgccBillingDao extends BaseDao{  
  
    /** 
     * 插入数据 
     */  
    @SuppressWarnings("deprecation")
	public static void insertData(String tableName, FgccBilling pb) {
    	HTableInterface table = pool.getTable(tableName);
        Put put = new Put(pb.getId().getBytes());
        if(pb.getServiceOrderNum() != null){
        	put.add("details".getBytes(), "service_order_num".getBytes(), pb.getServiceOrderNum().getBytes());
        }if(pb.getServiceSerialNum() != null){
        	put.add("details".getBytes(), "service_serial_num".getBytes(), pb.getServiceSerialNum().getBytes());
        }if(pb.getPayOrderNum() != null){
        	put.add("details".getBytes(), "pay_order_num".getBytes(), pb.getPayOrderNum().getBytes());
        }if(pb.getPaySerialNum() != null){
        	put.add("details".getBytes(), "pay_serial_num".getBytes(), pb.getPaySerialNum().getBytes());
        }
        try {  
            table.put(put);  
            table.flushCommits();
            pool.putTable(table);
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }
  
    /** 
     * 查询所有数据 
     */  
    @SuppressWarnings("deprecation")
	public static List<FgccBilling> QueryAll(String tableName) {  
    	HTableInterface table = pool.getTable(tableName);
        List<FgccBilling> pbs = new ArrayList<FgccBilling>();
        FgccBilling fb = null;
        try {  
            ResultScanner rs = table.getScanner(new Scan());  
            for (Result r : rs) { 
            	fb = new FgccBilling();
            	fb.setId(new String(r.getRow()));
                for (KeyValue keyValue : r.raw()) {
                	String qualifier = new String(keyValue.getQualifier());
                	String value = new String(keyValue.getValue());
                	if(qualifier.equals("service_order_num")){
                		fb.setServiceOrderNum(value);
                	}else if(qualifier.equals("service_serial_num")){
                		fb.setServiceSerialNum(value);
                	}else if(qualifier.equals("pay_order_num")){
                		fb.setPayOrderNum(value);
                	}else if(qualifier.equals("pay_serial_num")){
                		fb.setPaySerialNum(value);
                	}
                }  
                pbs.add(fb);
            }
            rs.close();
            pool.putTable(table);
        } catch (IOException e) {  
            e.printStackTrace();  
        }
		return pbs;  
    }  
  
    /** 
     * 单条件查询,根据rowkey查询唯一一条记录 
     */  
    @SuppressWarnings("deprecation")
	public static FgccBilling QueryByRowkey(String tableName, String rowkey) {  
    	HTableInterface table = pool.getTable(tableName);
    	FgccBilling fb = null;
        try {  
            Get scan = new Get(rowkey.getBytes());
            Result r = table.get(scan); 
            if(r.getRow() !=null){
            	fb = new FgccBilling();
            	fb.setId(new String(r.getRow()));
                for (KeyValue keyValue : r.raw()) {  
                	String qualifier = new String(keyValue.getQualifier());
                	String value = new String(keyValue.getValue());
                	if(qualifier.equals("service_order_num")){
                		fb.setServiceOrderNum(value);
                	}else if(qualifier.equals("service_serial_num")){
                		fb.setServiceSerialNum(value);
                	}else if(qualifier.equals("pay_order_num")){
                		fb.setPayOrderNum(value);
                	}else if(qualifier.equals("pay_serial_num")){
                		fb.setPaySerialNum(value);
                	}
                }  
            }
            pool.putTable(table);
        } catch (IOException e) {  
            e.printStackTrace();  
        }
		return fb;  
    }  
  
    /** 
     * 单条件查询,根据rowkey查询唯一一条记录 
     */  
    @SuppressWarnings("deprecation")
	public static List<FgccBilling> QueryByNav(String tableName, String start, String end) {  
    	HTableInterface table = pool.getTable(tableName);
        List<FgccBilling> fbs = new ArrayList<FgccBilling>();
        FgccBilling fb = null;
        try {  
            Scan scan = new Scan();
            scan.setStartRow(start.getBytes());
            scan.setStopRow(end.getBytes());
            ResultScanner rs = table.getScanner(scan);  
            for (Result r : rs) {  
            	fb = new FgccBilling();
            	fb.setId(new String(r.getRow()));
                for (KeyValue keyValue : r.raw()) {  
                	String qualifier = new String(keyValue.getQualifier());
                	String value = new String(keyValue.getValue());
                	if(qualifier.equals("service_order_num")){
                		fb.setServiceOrderNum(value);
                	}else if(qualifier.equals("service_serial_num")){
                		fb.setServiceSerialNum(value);
                	}else if(qualifier.equals("pay_order_num")){
                		fb.setPayOrderNum(value);
                	}else if(qualifier.equals("pay_serial_num")){
                		fb.setPaySerialNum(value);
                	}
                } 
                fbs.add(fb);
            }
            rs.close();
            pool.putTable(table);
        } catch (IOException e) {  
            e.printStackTrace();  
        }
		return fbs;  
    }  
}  
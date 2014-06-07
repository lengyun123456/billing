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
import com.funguide.billing.entity.ContrastBilling;


public class ContrastBillingDao extends BaseDao{  
  
    /** 
     * 插入数据 
     */  
    @SuppressWarnings("deprecation")
	public static void insertData(String tableName, ContrastBilling cb) {
    	HTableInterface table = pool.getTable(tableName);
        Put put = new Put(cb.getId().getBytes());
        if(cb.getServiceDealTime() != null){
        	put.add("details".getBytes(), "service_deal_time".getBytes(), cb.getServiceDealTime().getBytes());
        }if(cb.getPayDealTime() != null){
        	put.add("details".getBytes(), "pay_deal_time".getBytes(), cb.getPayDealTime().getBytes());
        }if(cb.getServiceOrderNum() != null){
        	put.add("details".getBytes(), "service_order_num".getBytes(), cb.getServiceOrderNum().getBytes());
        }if(cb.getPayOrderNum() != null){
        	put.add("details".getBytes(), "pay_order_num".getBytes(), cb.getPayOrderNum().getBytes());
        }if(cb.getSeriveDealAmount() != null){
        	put.add("details".getBytes(), "service_deal_amount".getBytes(), cb.getPayDealAmount().getBytes());
        }if(cb.getPayDealAmount() != null){
        	put.add("details".getBytes(), "pay_deal_amount".getBytes(), cb.getPayDealAmount().getBytes());
        }if(cb.getComtrastResult() != null){
        	put.add("details".getBytes(), "contrast_result".getBytes(), cb.getComtrastResult().getBytes());
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
	public static List<ContrastBilling> QueryAll(String tableName) {  
    	HTableInterface table = pool.getTable(tableName);
        List<ContrastBilling> cbs = new ArrayList<ContrastBilling>();
        ContrastBilling cb = null;
        try {  
            ResultScanner rs = table.getScanner(new Scan());  
            for (Result r : rs) { 
            	cb = new ContrastBilling();
            	cb.setId(new String(r.getRow()));
                for (KeyValue keyValue : r.raw()) {
                	String qualifier = new String(keyValue.getQualifier());
                	String value = new String(keyValue.getValue());
                	if(qualifier.equals("service_deal_time")){
                		cb.setServiceDealTime(value);
                	}else if(qualifier.equals("pay_deal_time")){
                		cb.setPayDealTime(value);
                	}else if(qualifier.equals("service_order_num")){
                		cb.setServiceOrderNum(value);
                	}else if(qualifier.equals("pay_order_num")){
                		cb.setPayOrderNum(value);
                	}else if(qualifier.equals("service_deal_amount")){
                		cb.setSeriveDealAmount(value);
                	}else if(qualifier.equals("pay_deal_amount")){
                		cb.setPayDealAmount(value);
                	}else if(qualifier.equals("contrast_result")){
                		cb.setComtrastResult(value);
                	}
                }  
                cbs.add(cb);
            }
            rs.close();
            pool.putTable(table);
        } catch (IOException e) {  
            e.printStackTrace();  
        }
		return cbs;  
    }  
  
    /** 
     * 单条件查询,根据rowkey查询唯一一条记录 
     */  
    @SuppressWarnings("deprecation")
	public static ContrastBilling QueryByRowkey(String tableName, String rowkey) {  
    	HTableInterface table = pool.getTable(tableName);
    	ContrastBilling cb = null;
        try {  
            Get scan = new Get(rowkey.getBytes());
            Result r = table.get(scan); 
            if(r.getRow() !=null){
            	cb = new ContrastBilling();
            	cb.setId(new String(r.getRow()));
                for (KeyValue keyValue : r.raw()) {  
                	String qualifier = new String(keyValue.getQualifier());
                	String value = new String(keyValue.getValue());
                	if(qualifier.equals("service_deal_time")){
                		cb.setServiceDealTime(value);
                	}else if(qualifier.equals("pay_deal_time")){
                		cb.setPayDealTime(value);
                	}else if(qualifier.equals("service_order_num")){
                		cb.setServiceOrderNum(value);
                	}else if(qualifier.equals("pay_order_num")){
                		cb.setPayOrderNum(value);
                	}else if(qualifier.equals("service_deal_amount")){
                		cb.setSeriveDealAmount(value);
                	}else if(qualifier.equals("pay_deal_amount")){
                		cb.setPayDealAmount(value);
                	}else if(qualifier.equals("contrast_result")){
                		cb.setComtrastResult(value);
                	}
                }  
            }
            pool.putTable(table);
        } catch (IOException e) {  
            e.printStackTrace();  
        }
		return cb;  
    }  
  
    /** 
     * 单条件查询,根据rowkey查询唯一一条记录 X
     */  
    @SuppressWarnings("deprecation")
	public static List<ContrastBilling> QueryByNav(String tableName, String start, String end) {  
    	HTableInterface table = pool.getTable(tableName);
        List<ContrastBilling> cbs = new ArrayList<ContrastBilling>();
        ContrastBilling cb = null;
        try {  
            Scan scan = new Scan();
            scan.setStartRow(start.getBytes());
            scan.setStopRow(end.getBytes());
            ResultScanner rs = table.getScanner(scan);  
            for (Result r : rs) {  
            	cb = new ContrastBilling();
            	cb.setId(new String(r.getRow()));
                for (KeyValue keyValue : r.raw()) {  
                	String qualifier = new String(keyValue.getQualifier());
                	String value = new String(keyValue.getValue());
                	if(qualifier.equals("service_deal_time")){
                		cb.setServiceDealTime(value);
                	}else if(qualifier.equals("pay_deal_time")){
                		cb.setPayDealTime(value);
                	}else if(qualifier.equals("service_order_num")){
                		cb.setServiceOrderNum(value);
                	}else if(qualifier.equals("pay_order_num")){
                		cb.setPayOrderNum(value);
                	}else if(qualifier.equals("service_deal_amount")){
                		cb.setSeriveDealAmount(value);
                	}else if(qualifier.equals("pay_deal_amount")){
                		cb.setPayDealAmount(value);
                	}else if(qualifier.equals("contrast_result")){
                		cb.setComtrastResult(value);
                	}
                }  
                cbs.add(cb);
            }
            rs.close();
            pool.putTable(table);
        } catch (IOException e) {  
            e.printStackTrace();  
        }
		return cbs;  
    }  
}  
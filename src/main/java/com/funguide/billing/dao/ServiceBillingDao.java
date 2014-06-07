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
import com.funguide.billing.entity.ServiceBilling;

public class ServiceBillingDao extends BaseDao{  
  
    /** 
     * 插入数据 
     */  
    @SuppressWarnings("deprecation")
	public static void insertData(String tableName, ServiceBilling sb) {
    	HTableInterface table = pool.getTable(tableName);
        Put put = new Put(sb.getId().getBytes());
        if(sb.getBusinessNum() != null){
        	put.add("details".getBytes(), "business_num".getBytes(), sb.getBusinessNum().getBytes());
        }if(sb.getProductNum() != null){
        	put.add("details".getBytes(), "product_num".getBytes(), sb.getProductNum().getBytes());
        }if(sb.getServiceOrderNum() != null){
        	put.add("details".getBytes(), "service_order_num".getBytes(), sb.getServiceOrderNum().getBytes());
        }if(sb.getServiceSerialNum() != null){
        	put.add("details".getBytes(), "service_serial_num".getBytes(), sb.getServiceSerialNum().getBytes());
        }if(sb.getDealType() != null){
        	put.add("details".getBytes(), "deal_type".getBytes(), sb.getDealType().getBytes());
        }if(sb.getDealTime() != null){
        	put.add("details".getBytes(), "deal_time".getBytes(), sb.getDealTime().getBytes());
        }if(sb.getDealAmount() != null){
        	put.add("details".getBytes(), "deal_amount".getBytes(), sb.getDealAmount().getBytes());
        }if(sb.getDealPoints() != null){
        	put.add("details".getBytes(), "deal_points".getBytes(), sb.getDealPoints().getBytes());
        }if(sb.getPointsWeights() != null){
        	put.add("details".getBytes(), "points_weights".getBytes(), sb.getPointsWeights().getBytes());
        }if(sb.getUserMobileNum() != null){
        	put.add("details".getBytes(), "user_mobile_num".getBytes(), sb.getUserMobileNum().getBytes());
        }if(sb.getUserId() != null){
        	put.add("details".getBytes(), "user_id".getBytes(), sb.getUserId().getBytes());
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
	public static List<ServiceBilling> QueryAll(String tableName) {  
    	HTableInterface table = pool.getTable(tableName);
        List<ServiceBilling> sbs = new ArrayList<ServiceBilling>();
        ServiceBilling sb = null;
        try {  
            ResultScanner rs = table.getScanner(new Scan());  
            for (Result r : rs) { 
            	sb = new ServiceBilling();
            	sb.setId(new String(r.getRow()));
                for (KeyValue keyValue : r.raw()) {
                	String qualifier = new String(keyValue.getQualifier());
                	String value = new String(keyValue.getValue());
                	if(qualifier.equals("business_num")){
                		sb.setBusinessNum(value);
                	}else if(qualifier.equals("product_num")){
                		sb.setProductNum(value);
                	}else if(qualifier.equals("service_order_num")){
                		sb.setServiceOrderNum(value);
                	}else if(qualifier.equals("service_serial_num")){
                		sb.setServiceSerialNum(value);
                	}else if(qualifier.equals("deal_type")){
                		sb.setDealType(value);
                	}else if(qualifier.equals("deal_time")){
                		sb.setDealTime(value);
                	}else if(qualifier.equals("deal_amount")){
                		sb.setDealAmount(value);
                	}else if(qualifier.equals("deal_points")){
                		sb.setDealPoints(value);
                	}else if(qualifier.equals("points_weights")){
                		sb.setPointsWeights(value);
                	}else if(qualifier.equals("user_mobile_num")){
                		sb.setUserMobileNum(value);
                	}else if(qualifier.equals("user_id")){
                		sb.setUserId(value);
                	}
                }  
                sbs.add(sb);
            }
            rs.close();
            pool.putTable(table);
        } catch (IOException e) {  
            e.printStackTrace();  
        }
		return sbs;  
    }  
  
    /** 
     * 单条件查询,根据rowkey查询唯一一条记录 
     */  
    @SuppressWarnings("deprecation")
	public static ServiceBilling QueryByRowkey(String tableName, String rowkey) {  
    	HTableInterface table = pool.getTable(tableName);
        ServiceBilling sb = null;
        try {  
            Get scan = new Get(rowkey.getBytes());
            Result r = table.get(scan); 
            if(r.getRow() !=null){
            	sb = new ServiceBilling();
            	sb.setId(new String(r.getRow()));
                for (KeyValue keyValue : r.raw()) {  
                	String qualifier = new String(keyValue.getQualifier());
                	String value = new String(keyValue.getValue());
                	if(qualifier.equals("business_num")){
                		sb.setBusinessNum(value);
                	}else if(qualifier.equals("product_num")){
                		sb.setProductNum(value);
                	}else if(qualifier.equals("service_order_num")){
                		sb.setServiceOrderNum(value);
                	}else if(qualifier.equals("service_serial_num")){
                		sb.setServiceSerialNum(value);
                	}else if(qualifier.equals("deal_type")){
                		sb.setDealType(value);
                	}else if(qualifier.equals("deal_time")){
                		sb.setDealTime(value);
                	}else if(qualifier.equals("deal_amount")){
                		sb.setDealAmount(value);
                	}else if(qualifier.equals("deal_points")){
                		sb.setDealPoints(value);
                	}else if(qualifier.equals("points_weights")){
                		sb.setPointsWeights(value);
                	}else if(qualifier.equals("user_mobile_num")){
                		sb.setUserMobileNum(value);
                	}else if(qualifier.equals("user_id")){
                		sb.setUserId(value);
                	}
                }  
            }
            pool.putTable(table);
        } catch (IOException e) {  
            e.printStackTrace();  
        }
		return sb;  
    }  
  
    /** 
     * 单条件查询,根据rowkey查询唯一一条记录 
     */  
    @SuppressWarnings("deprecation")
	public static List<ServiceBilling> QueryByNav(String tableName, String start, String end) {  
    	HTableInterface table = pool.getTable(tableName);
        List<ServiceBilling> sbs = new ArrayList<ServiceBilling>();
        ServiceBilling sb = null;
        try {  
            Scan scan = new Scan();
            scan.setStartRow(start.getBytes());
            scan.setStopRow(end.getBytes());
            ResultScanner rs = table.getScanner(scan);  
            for (Result r : rs) {  
            	sb = new ServiceBilling();
            	sb.setId(new String(r.getRow()));
                for (KeyValue keyValue : r.raw()) {  
                	String qualifier = new String(keyValue.getQualifier());
                	String value = new String(keyValue.getValue());
                	if(qualifier.equals("business_num")){
                		sb.setBusinessNum(value);
                	}else if(qualifier.equals("product_num")){
                		sb.setProductNum(value);
                	}else if(qualifier.equals("service_order_num")){
                		sb.setServiceOrderNum(value);
                	}else if(qualifier.equals("service_serial_num")){
                		sb.setServiceSerialNum(value);
                	}else if(qualifier.equals("deal_type")){
                		sb.setDealType(value);
                	}else if(qualifier.equals("deal_time")){
                		sb.setDealTime(value);
                	}else if(qualifier.equals("deal_amount")){
                		sb.setDealAmount(value);
                	}else if(qualifier.equals("deal_points")){
                		sb.setDealPoints(value);
                	}else if(qualifier.equals("points_weights")){
                		sb.setPointsWeights(value);
                	}else if(qualifier.equals("user_mobile_num")){
                		sb.setUserMobileNum(value);
                	}else if(qualifier.equals("user_id")){
                		sb.setUserId(value);
                	}
                } 
                sbs.add(sb);
            }
            rs.close();
            pool.putTable(table);
        } catch (IOException e) {  
            e.printStackTrace();  
        }
		return sbs;
    }
}  
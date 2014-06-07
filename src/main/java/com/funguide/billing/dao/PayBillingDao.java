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
import com.funguide.billing.entity.PayBilling;
  
public class PayBillingDao extends BaseDao{  
  
	/** 
     * 插入数据 
     */  
    @SuppressWarnings("deprecation")
	public static void insertData(String tableName, PayBilling pb) {
    	HTableInterface table = pool.getTable(tableName);
        Put put = new Put(pb.getId().getBytes());
        if(pb.getPayChannel() != null){
        	put.add("details".getBytes(), "pay_channel".getBytes(), pb.getPayChannel().getBytes());
        }if(pb.getAccessBusinessNum() != null){
        	put.add("details".getBytes(), "access_business_num".getBytes(), pb.getAccessBusinessNum().getBytes());
        }if(pb.getServiceOrderNum() != null){
        	put.add("details".getBytes(), "service_order_num".getBytes(), pb.getServiceOrderNum().getBytes());
        }if(pb.getPayOrderNum() != null){
        	put.add("details".getBytes(), "pay_order_num".getBytes(), pb.getPayOrderNum().getBytes());
        }if(pb.getPaySerialNum() != null){
        	put.add("details".getBytes(), "pay_serial_num".getBytes(), pb.getPaySerialNum().getBytes());
        }if(pb.getDealType() != null){
        	put.add("details".getBytes(), "deal_type".getBytes(), pb.getDealType().getBytes());
        }if(pb.getDealTime() != null){
        	put.add("details".getBytes(), "deal_time".getBytes(), pb.getDealTime().getBytes());
        }if(pb.getDealAmount() != null){
        	put.add("details".getBytes(), "deal_amount".getBytes(), pb.getDealAmount().getBytes());
        }if(pb.getDealPoints() != null){
        	put.add("details".getBytes(), "deal_points".getBytes(), pb.getDealPoints().getBytes());
        }if(pb.getPointsWeights() != null){
        	put.add("details".getBytes(), "points_weights".getBytes(), pb.getPointsWeights().getBytes());
        }if(pb.getUserMobileNum() != null){
        	put.add("details".getBytes(), "user_mobile_num".getBytes(), pb.getUserMobileNum().getBytes());
        }if(pb.getUserId() != null){
        	put.add("details".getBytes(), "user_id".getBytes(), pb.getUserId().getBytes());
        }if(pb.getCommission() != null){
        	put.add("details".getBytes(), "commission".getBytes(), pb.getCommission().getBytes());
        }if(pb.getRefundFee() != null){
        	put.add("details".getBytes(), "eefund_fee".getBytes(), pb.getRefundFee().getBytes());
        }if(pb.getActualSettleAmount() != null){
        	put.add("details".getBytes(), "actual_settle_amount".getBytes(), pb.getActualSettleAmount().getBytes());
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
	public static List<PayBilling> QueryAll(String tableName) {  
    	HTableInterface table = pool.getTable(tableName);
        List<PayBilling> pbs = new ArrayList<PayBilling>();
        PayBilling pb = null;
        try {  
            ResultScanner rs = table.getScanner(new Scan());  
            for (Result r : rs) { 
            	pb = new PayBilling();
            	pb.setId(new String(r.getRow()));
                for (KeyValue keyValue : r.raw()) {
                	String qualifier = new String(keyValue.getQualifier());
                	String value = new String(keyValue.getValue());
                	if(qualifier.equals("pay_channel")){
                		pb.setPayChannel(value);
                	}else if(qualifier.equals("access_business_num")){
                		pb.setAccessBusinessNum(value);
                	}else if(qualifier.equals("service_order_num")){
                		pb.setServiceOrderNum(value);
                	}else if(qualifier.equals("pay_serial_num")){
                		pb.setPaySerialNum(value);
                	}else if(qualifier.equals("deal_type")){
                		pb.setDealType(value);
                	}else if(qualifier.equals("deal_time")){
                		pb.setDealTime(value);
                	}else if(qualifier.equals("deal_amount")){
                		pb.setDealAmount(value);
                	}else if(qualifier.equals("deal_points")){
                		pb.setDealPoints(value);
                	}else if(qualifier.equals("points_weights")){
                		pb.setPointsWeights(value);
                	}else if(qualifier.equals("user_mobile_num")){
                		pb.setUserMobileNum(value);
                	}else if(qualifier.equals("user_id")){
                		pb.setUserId(value);
                	}else if(qualifier.equals("commission")){
                		pb.setCommission(value);
                	}else if(qualifier.equals("refund_fee")){
                		pb.setRefundFee(value);
                	}else if(qualifier.equals("actual_settle_amount")){
                		pb.setActualSettleAmount(value);
                	}
                }  
                pbs.add(pb);
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
	public static PayBilling QueryByRowkey(String tableName, String rowkey) {  
    	HTableInterface table = pool.getTable(tableName);
    	PayBilling pb = null;
        try {  
            Get scan = new Get(rowkey.getBytes());
            Result r = table.get(scan); 
            if(r.getRow() !=null){
            	pb = new PayBilling();
            	pb.setId(new String(r.getRow()));
                for (KeyValue keyValue : r.raw()) {  
                	String qualifier = new String(keyValue.getQualifier());
                	String value = new String(keyValue.getValue());
                	if(qualifier.equals("pay_channel")){
                		pb.setPayChannel(value);
                	}else if(qualifier.equals("access_business_num")){
                		pb.setAccessBusinessNum(value);
                	}else if(qualifier.equals("service_order_num")){
                		pb.setServiceOrderNum(value);
                	}else if(qualifier.equals("pay_serial_num")){
                		pb.setPaySerialNum(value);
                	}else if(qualifier.equals("deal_type")){
                		pb.setDealType(value);
                	}else if(qualifier.equals("deal_time")){
                		pb.setDealTime(value);
                	}else if(qualifier.equals("deal_amount")){
                		pb.setDealAmount(value);
                	}else if(qualifier.equals("deal_points")){
                		pb.setDealPoints(value);
                	}else if(qualifier.equals("points_weights")){
                		pb.setPointsWeights(value);
                	}else if(qualifier.equals("user_mobile_num")){
                		pb.setUserMobileNum(value);
                	}else if(qualifier.equals("user_id")){
                		pb.setUserId(value);
                	}else if(qualifier.equals("commission")){
                		pb.setCommission(value);
                	}else if(qualifier.equals("refund_fee")){
                		pb.setRefundFee(value);
                	}else if(qualifier.equals("actual_settle_amount")){
                		pb.setActualSettleAmount(value);
                	}
                }  
            }
            pool.putTable(table);
        } catch (IOException e) {  
            e.printStackTrace();  
        }
		return pb;  
    }  
  
    /** 
     * 单条件查询,根据rowkey查询唯一一条记录 
     */  
    @SuppressWarnings("deprecation")
	public static List<PayBilling> QueryByNav(String tableName, String start, String end) {  
    	HTableInterface table = pool.getTable(tableName);
        List<PayBilling> pbs = new ArrayList<PayBilling>();
        PayBilling pb = null;
        try {  
            Scan scan = new Scan();
            scan.setStartRow(start.getBytes());
            scan.setStopRow(end.getBytes());
            ResultScanner rs = table.getScanner(scan);  
            for (Result r : rs) {  
            	pb = new PayBilling();
            	pb.setId(new String(r.getRow()));
                for (KeyValue keyValue : r.raw()) {  
                	String qualifier = new String(keyValue.getQualifier());
                	String value = new String(keyValue.getValue());
                	if(qualifier.equals("pay_channel")){
                		pb.setPayChannel(value);
                	}else if(qualifier.equals("access_business_num")){
                		pb.setAccessBusinessNum(value);
                	}else if(qualifier.equals("service_order_num")){
                		pb.setServiceOrderNum(value);
                	}else if(qualifier.equals("pay_serial_num")){
                		pb.setPaySerialNum(value);
                	}else if(qualifier.equals("deal_type")){
                		pb.setDealType(value);
                	}else if(qualifier.equals("deal_time")){
                		pb.setDealTime(value);
                	}else if(qualifier.equals("deal_amount")){
                		pb.setDealAmount(value);
                	}else if(qualifier.equals("deal_points")){
                		pb.setDealPoints(value);
                	}else if(qualifier.equals("points_weights")){
                		pb.setPointsWeights(value);
                	}else if(qualifier.equals("user_mobile_num")){
                		pb.setUserMobileNum(value);
                	}else if(qualifier.equals("user_id")){
                		pb.setUserId(value);
                	}else if(qualifier.equals("commission")){
                		pb.setCommission(value);
                	}else if(qualifier.equals("refund_fee")){
                		pb.setRefundFee(value);
                	}else if(qualifier.equals("actual_settle_amount")){
                		pb.setActualSettleAmount(value);
                	}
                } 
                pbs.add(pb);
            }
            rs.close();
            pool.putTable(table);
        } catch (IOException e) {  
            e.printStackTrace();  
        }
		return pbs;  
    }
}  
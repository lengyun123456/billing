--hbase shell

create 'SERVICE_BILLING','details'

create 'FGCC_BILLING','details'

create 'PAY_BILLING','details'

create 'CONTRAST_BILLING','details'

--创建BILLING库。
CREATE DATABASE BILLING;

--使用BILLING库
USE BILLING;

--Hive中SERVICE表关联Hbase中SERVICE_BILLING表。
CREATE EXTERNAL TABLE SERVICE(key string, business_num string, product_num string, service_order_num string, service_serial_num string, deal_type tinyint, deal_time string, deal_amount bigint, deal_points bigint, points_weights int, user_mobile_num string, user_id string)
STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler'
WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,details:business_num,details:product_num,details:service_order_num,details:service_serial_num,details:deal_type,details:deal_time,details:deal_amount,details:deal_points,details:points_weights,details:user_mobile_num,details:user_id")   
TBLPROPERTIES("hbase.table.name" = "SERVICE_BILLING");

--Hive中FGCC表关联Hbase中FGCC_BILLING表。
CREATE EXTERNAL TABLE FGCC(key string, service_order_num string, service_serial_num string, pay_order_num string, pay_serial_num string)
STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler'
WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,details:service_order_num,details:service_serial_num,details:pay_order_num,details:pay_serial_num")   
TBLPROPERTIES("hbase.table.name" = "FGCC_BILLING");

--Hive中PAY表关联Hbase中PAY_BILLING表。
CREATE EXTERNAL TABLE PAY(key string, pay_channel string, access_business_num string, service_order_num string, pay_order_num string, pay_serial_num string, deal_type tinyint, deal_time string, deal_amount bigint, deal_points bigint, points_weights string, user_mobile_num string, user_id string, commission bigint, eefund_fee bigint, actual_settle_amount bigint)
STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler'
WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,details:pay_channel,details:access_business_num,details:service_order_num,details:pay_order_num,details:pay_serial_num,details:deal_type,details:deal_time,details:deal_amount,details:deal_points,details:points_weights,details:user_mobile_num,details:user_id,details:commission,details:eefund_fee,details:actual_settle_amount")   
TBLPROPERTIES("hbase.table.name" = "PAY_BILLING");

--Hive中CONTRAST表关联Hbase中CONTRAST_BILLING表。
CREATE EXTERNAL TABLE contrast(key string, service_deal_time string, pay_deal_time string, service_order_num string, pay_order_num string, service_deal_amount bigint, pay_deal_amount bigint, contrast_result bigint)
STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler'
WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,details:service_deal_time,details:pay_deal_time,details:service_order_num,details:pay_order_num,details:service_deal_amount,details:pay_deal_amount,details:contrast_result")   
TBLPROPERTIES("hbase.table.name" = "CONTRAST_BILLING");

--平帐处理，每日定时执行。
INSERT OVERWRITE TABLE BILLING.CONTRAST 
SELECT ser.service_order_num, ser.deal_time, pa.deal_time, ser.service_order_num, pa.pay_order_num, ser.deal_amount, pa.deal_amount, ser.deal_amount - pa.deal_amount 
FROM BILLING.PAY pa, BILLING.FGCC fg, BILLING.SERVICE ser 
WHERE pa.pay_order_num = fg.pay_order_num AND ser.service_order_num = fg.service_order_num AND ser.deal_amount - pa.deal_amount != 0;
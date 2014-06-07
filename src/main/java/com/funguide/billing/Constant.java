package com.funguide.billing;

public class Constant {
	public static final String ROOT_FILE_PATH = "/home/root/data/file";
	public static final String SERVICE_BILLING_PATH = "/home/root/data/billing_file/service_billing";
	public static final String FGCC_BILLING_PATH = "/home/root/data/billing_file/fgcc_billing";
	public static final String PAY_BILLING_SOURCE_PATH = "/home/root/data/billing_file/pay_billing/source";
	public static final String PAY_BILLING_TARGET_PATH = "/home/root/data/billing_file/pay_billing/target";
	public static final Integer HBASE_TABLE_POOL_NUM = 2;
	public static final String OLD_FILE_FLAG = ".old";
	public static final String SEND_MAIL_USER = "zhengmingcheng@funguide.com.cn";
	public static final String SEND_MAIL_PASSWORD = "zaq%121";
	public static final String MAIL_SMTP_HOST = "smtp.exmail.qq.com";
	public static final String MAIL_SUBJECT = "业务账单文件处理提醒";
	public static final String MAIL_SUCCESS_CONTENT = "您上传的业务账单文件已经成功处理！";
	public static final String MAIL_FAILURE_CONTENT = "您上传的业务账单文件有误，请重新上传！";
	
	/**
	 * 数据库表名称
	 * @author zhmch
	 *
	 */
	public static final class TableName {
		public static final String SERVICE_BILLING = "SERVICE_BILLING";
		public static final String FGCC_BILLING = "FGCC_BILLING";
		public static final String PAY_BILLING = "PAY_BILLING";
		public static final String CONTRAST_BILLING = "CONTRAST_BILLING";
	}
	
	/**
	 * 账单类型
	 * @author zhmch
	 *
	 */
	public static final class BillingType {
		public static final int SERVICE = 14;
		public static final int FGCC = 21;
		public static final int PAY = 42;
	}
	
	/**
	 * 字符编码类型
	 * @author zhmch
	 *
	 */
	public static final class EncodingType {
		public static final String UTF8 = "UTF-8";
		public static final String GBK = "GBK";
	}
	
	/**
	 * 交易类型
	 * @author zhmch
	 *
	 */
	public static final class DealType {
		public static final String PAYOFF= "消费";
		public static final String REFUND = "退货";
	}
}

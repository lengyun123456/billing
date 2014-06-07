package com.funguide.billing.entity;

public class FgccBilling {
	//roekey
	private String id;
	//业务订单号
	private String serviceOrderNum;
	//业务流水号
	private String serviceSerialNum;
	//支付渠道订单号
	private String payOrderNum;
	//支付渠道流水号
	private String paySerialNum;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getServiceOrderNum() {
		return serviceOrderNum;
	}
	public void setServiceOrderNum(String serviceOrderNum) {
		this.serviceOrderNum = serviceOrderNum;
	}
	public String getServiceSerialNum() {
		return serviceSerialNum;
	}
	public void setServiceSerialNum(String serviceSerialNum) {
		this.serviceSerialNum = serviceSerialNum;
	}
	public String getPayOrderNum() {
		return payOrderNum;
	}
	public void setPayOrderNum(String payOrderNum) {
		this.payOrderNum = payOrderNum;
	}
	public String getPaySerialNum() {
		return paySerialNum;
	}
	public void setPaySerialNum(String paySerialNum) {
		this.paySerialNum = paySerialNum;
	}
}
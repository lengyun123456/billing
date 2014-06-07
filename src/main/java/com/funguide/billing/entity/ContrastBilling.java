package com.funguide.billing.entity;

public class ContrastBilling {
	//roekey
	private String id;
	//业务订单交易时间
	private String serviceDealTime;
	//支付订单交易时间
	private String payDealTime;
	//业务订单号
	private String serviceOrderNum;
	//支付订单号
	private String payOrderNum;
	//业务交易金额
	private String seriveDealAmount;
	//支付交易金额
	private String payDealAmount;
	//平帐结果
	private String comtrastResult;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getServiceDealTime() {
		return serviceDealTime;
	}
	public void setServiceDealTime(String serviceDealTime) {
		this.serviceDealTime = serviceDealTime;
	}
	public String getPayDealTime() {
		return payDealTime;
	}
	public void setPayDealTime(String payDealTime) {
		this.payDealTime = payDealTime;
	}
	public String getServiceOrderNum() {
		return serviceOrderNum;
	}
	public void setServiceOrderNum(String serviceOrderNum) {
		this.serviceOrderNum = serviceOrderNum;
	}
	public String getPayOrderNum() {
		return payOrderNum;
	}
	public void setPayOrderNum(String payOrderNum) {
		this.payOrderNum = payOrderNum;
	}
	public String getSeriveDealAmount() {
		return seriveDealAmount;
	}
	public void setSeriveDealAmount(String seriveDealAmount) {
		this.seriveDealAmount = seriveDealAmount;
	}
	public String getPayDealAmount() {
		return payDealAmount;
	}
	public void setPayDealAmount(String payDealAmount) {
		this.payDealAmount = payDealAmount;
	}
	public String getComtrastResult() {
		return comtrastResult;
	}
	public void setComtrastResult(String comtrastResult) {
		this.comtrastResult = comtrastResult;
	}
}
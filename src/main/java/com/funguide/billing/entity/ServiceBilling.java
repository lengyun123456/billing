package com.funguide.billing.entity;

public class ServiceBilling {
	//roekey
	private String id;
	//商户号
	private String businessNum;
	//产品号
	private String productNum;
	//业务订单号
	private String serviceOrderNum;
	//业务流水号
	private String serviceSerialNum;
	//交易类型
	private String dealType;
	//交易时间
	private String dealTime;
	//交易金额
	private String dealAmount;
	//交易积分
	private String dealPoints;
	//积分价值
	private String pointsWeights;
	//交易用户手机号
	private String userMobileNum;
	//交易用户ID
	private String userId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBusinessNum() {
		return businessNum;
	}
	public void setBusinessNum(String businessNum) {
		this.businessNum = businessNum;
	}
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
		this.productNum = productNum;
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
	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	public String getDealTime() {
		return dealTime;
	}
	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public String getDealPoints() {
		return dealPoints;
	}
	public void setDealPoints(String dealPoints) {
		this.dealPoints = dealPoints;
	}
	public String getPointsWeights() {
		return pointsWeights;
	}
	public void setPointsWeights(String pointsWeights) {
		this.pointsWeights = pointsWeights;
	}
	public String getUserMobileNum() {
		return userMobileNum;
	}
	public void setUserMobileNum(String userMobileNum) {
		this.userMobileNum = userMobileNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
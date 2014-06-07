package com.funguide.billing.entity;

public class PayBilling {
	//roekey
	private String id;
	//支付渠道
	private String payChannel;
	//接入商户号
	private String accessBusinessNum;
	//业务订单号
	private String serviceOrderNum;
	//支付订单号
	private String payOrderNum;
	//支付流水号
	private String paySerialNum;
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
	//佣金金额
	private String commission;
	//退款手续费
	private String refundFee;
	//实拔金额
	private String actualSettleAmount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	public String getAccessBusinessNum() {
		return accessBusinessNum;
	}
	public void setAccessBusinessNum(String accessBusinessNum) {
		this.accessBusinessNum = accessBusinessNum;
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
	public String getPaySerialNum() {
		return paySerialNum;
	}
	public void setPaySerialNum(String paySerialNum) {
		this.paySerialNum = paySerialNum;
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
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getRefundFee() {
		return refundFee;
	}
	public void setRefundFee(String refundFee) {
		this.refundFee = refundFee;
	}
	public String getActualSettleAmount() {
		return actualSettleAmount;
	}
	public void setActualSettleAmount(String actualSettleAmount) {
		this.actualSettleAmount = actualSettleAmount;
	}
}
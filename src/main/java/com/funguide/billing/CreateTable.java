package com.funguide.billing;

import com.funguide.billing.dao.BaseDao;


public class CreateTable extends BaseDao{ 

	public static void main(String[] args) {
		createTable("SERVICE_BILLING");
		createTable("FGCC_BILLING"); 
		createTable("PAY_BILLING");  
		createTable("CONTRAST_BILLING");
	}
}

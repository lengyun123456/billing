package com.funguide.billing.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class HiveDao {
	public static void contrast() throws Exception {
		Class.forName("org.apache.hadoop.hive.jdbc.HiveDriver");
		String billingContrastSql = " INSERT OVERWRITE TABLE BILLING.CONTRAST "
				+ " SELECT ser.service_order_num, ser.deal_time, pa.deal_time, "
				+ " ser.service_order_num, pa.pay_order_num, ser.deal_amount, "
				+ " pa.deal_amount, ser.deal_amount - pa.deal_amount "
				+ " FROM BILLING.SERVICE ser, BILLING.FGCC fg, BILLING.PAY pa "
				+ " WHERE ser.service_order_num = fg.service_order_num "
				+ " AND fg.pay_order_num = pa.pay_order_num "
				+ " AND ser.deal_amount - pa.deal_amount != 0";
		Connection con = DriverManager.getConnection("jdbc:hive://localhost:10000/BILLING", "", "");
		Statement stmt = con.createStatement();
		stmt.execute(billingContrastSql);
	}
}
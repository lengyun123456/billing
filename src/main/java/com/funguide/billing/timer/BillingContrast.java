package com.funguide.billing.timer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import com.funguide.billing.Constant;
import com.funguide.billing.dao.HiveDao;
import com.funguide.billing.dao.PayBillingDao;
import com.funguide.billing.dao.FgccBillingDao;
import com.funguide.billing.dao.ServiceBillingDao;
import com.funguide.billing.entity.PayBilling;
import com.funguide.billing.entity.FgccBilling;
import com.funguide.billing.entity.ServiceBilling;
import com.funguide.billing.utils.MailUtils;

public class BillingContrast {
	
	public static void exe() {
		String serviceBillingPath = Constant.SERVICE_BILLING_PATH;
		String fgccBillingPath = Constant.FGCC_BILLING_PATH;
		String payBillingPath = Constant.PAY_BILLING_TARGET_PATH;
		File file = null;
		boolean isNeedContrast = false;
		//业务账单入库
		try {
			file = new File(serviceBillingPath);
			if(file.exists() && file.isDirectory()){
				File[] files = file.listFiles();
				for(File f : files){
					String mailTo = null;
					if(f.getName().lastIndexOf(Constant.OLD_FILE_FLAG) == -1){
						isNeedContrast = true;
						if (f.isFile() && f.exists()) {
							InputStreamReader read = new InputStreamReader(
									new FileInputStream(f),
									Constant.EncodingType.UTF8);
							BufferedReader bufferedReader = new BufferedReader(
									read);
							String lineTxt = null;
							ServiceBilling sb = null;
							while ((lineTxt = bufferedReader.readLine()) != null) {
								if (!lineTxt.startsWith("#")) {
									String[] records = lineTxt.split(",");
									int recordslen = records.length;
									sb = new ServiceBilling();
									sb.setId(records[0] + records[1]
											+ records[2] + records[3]
											+ records[4]);
									sb.setBusinessNum(records[0]);
									sb.setProductNum(records[1]);
									sb.setServiceOrderNum(records[2]);
									sb.setServiceSerialNum(records[3]);
									sb.setDealType(records[4]);
									sb.setDealTime(records[5]);
									sb.setDealAmount(records[6]);
									sb.setDealPoints(records[7]);
									sb.setPointsWeights(records[8]);
									
									
									if (recordslen > 9) {
										sb.setUserMobileNum(records[9]);
										if (recordslen > 10) {
											sb.setUserId(records[10]);
										} else {
											sb.setUserId(null);
										}
									} else {
										sb.setUserMobileNum(null);
									}

									ServiceBillingDao.insertData(
											Constant.TableName.SERVICE_BILLING,
											sb);
								} else if (lineTxt.contains("mailto")) {
									String[] mailStr = lineTxt.split(":");
									mailTo = "".equals(mailStr[1].trim()) ? null
											: mailStr[1].trim();
								}
							}
							read.close();
						}
						File oldFile = new File(serviceBillingPath + "/" + f.getName() + Constant.OLD_FILE_FLAG);
						f.renameTo(oldFile);
						if(mailTo != null){
							String[] mailInfo = {mailTo, Constant.MAIL_SUBJECT, Constant.MAIL_SUCCESS_CONTENT};
							MailUtils.SendEmail(mailInfo);
						}
						File newTargetFile = new File(Constant.SERVICE_BILLING_PATH + "/" + f.getName() + Constant.OLD_FILE_FLAG);
						f.renameTo(newTargetFile);
					}
				}
			}
			//FGCC账单入库
			file = new File(fgccBillingPath);
			if(file.exists() && file.isDirectory()){
				File[] files = file.listFiles();
				for(File f : files){
					if(f.getName().lastIndexOf(Constant.OLD_FILE_FLAG) == -1){
						isNeedContrast = true;
						if (f.isFile() && f.exists()) {
							InputStreamReader read = new InputStreamReader(
									new FileInputStream(f),
									Constant.EncodingType.UTF8);
							BufferedReader bufferedReader = new BufferedReader(
									read);
							String lineTxt = null;
							FgccBilling fb = null;
							while ((lineTxt = bufferedReader.readLine()) != null) {
								if (!lineTxt.startsWith("#")) {
									String[] records = lineTxt.split(",");
									fb = new FgccBilling();
									fb.setId(records[0]);
									fb.setServiceOrderNum(records[0]);
									fb.setServiceSerialNum(records[2]);
									fb.setPayOrderNum(records[9]);
									fb.setPaySerialNum(records[11]);
									FgccBillingDao.insertData(Constant.TableName.FGCC_BILLING,fb);
								}
							}
							read.close();
						}
						File newTargetFile = new File(Constant.FGCC_BILLING_PATH + "/" + f.getName() + Constant.OLD_FILE_FLAG);
						f.renameTo(newTargetFile);
					}
				}
			}
			//银行账单入库
			file = new File(payBillingPath);
			if(file.exists() && file.isDirectory()){
				File[] files = file.listFiles();
				for(File f : files){
					if (f.getName().lastIndexOf(Constant.OLD_FILE_FLAG) == -1) {
						isNeedContrast = true;
						if (f.isFile() && f.exists()) {
							InputStreamReader read = new InputStreamReader(
									new FileInputStream(f),
									Constant.EncodingType.UTF8);
							BufferedReader bufferedReader = new BufferedReader(
									read);
							String lineTxt = null;
							PayBilling pb = null;
							while ((lineTxt = bufferedReader.readLine()) != null) {
								if (!lineTxt.startsWith("#")) {
									String[] records = lineTxt.split(",");
									pb = new PayBilling();
									pb.setId(records[4] + records[0] + records[5]);
									pb.setPayChannel(records[0]);
									pb.setAccessBusinessNum(records[1]);
									pb.setServiceOrderNum(null);
									pb.setPayOrderNum(records[3]);
									pb.setPaySerialNum(records[4]);
									pb.setDealType(records[5]);
									pb.setDealTime(records[6]);
									pb.setDealAmount(records[7]);
									pb.setDealPoints(records[8]);
									pb.setPointsWeights(records[9]);
									pb.setUserMobileNum(null);
									pb.setUserId(null);
									pb.setCommission(records[12]);
									pb.setRefundFee(records[13]);
									pb.setActualSettleAmount(records[14]);
									PayBillingDao.insertData(Constant.TableName.PAY_BILLING, pb);
								}
							}
							read.close();
						}
						File newTargetFile = new File(Constant.PAY_BILLING_TARGET_PATH + "/" + f.getName() + Constant.OLD_FILE_FLAG);
						f.renameTo(newTargetFile);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(isNeedContrast){
			try {
				HiveDao.contrast();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		BillingContrast.exe();
	}
}

package com.funguide.billing.timer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import org.apache.commons.lang3.StringUtils;
import com.funguide.billing.Constant;

public class BillingFormat {

	public static void exe() {
		String filePath = Constant.PAY_BILLING_SOURCE_PATH;
		try {
			File file = new File(filePath);
			if (file.exists() && file.isDirectory()) {
				File[] files = file.listFiles();
				for (File f : files) {
					if (f.getName().lastIndexOf(Constant.OLD_FILE_FLAG) == -1) {
						File oldSuurceFile = new File(
								Constant.PAY_BILLING_SOURCE_PATH + "/"
										+ f.getName() + Constant.OLD_FILE_FLAG);
						File newTargetFile = new File(
								Constant.PAY_BILLING_TARGET_PATH
										+ "/"
										+ StringUtils.substring(f.getName(), 0,
												f.getName().length() - 3)
										+ "csv");
						try {
							if (f.isFile() && f.exists()) {
								InputStreamReader inRe = new InputStreamReader(
										new FileInputStream(f), "GBK");
								BufferedReader br = new BufferedReader(inRe);
								FileOutputStream fos = new FileOutputStream(
										newTargetFile);
								OutputStreamWriter bw = new OutputStreamWriter(
										fos, "UTF-8");
								String line = null;
								StringBuilder sb = null;
								// boolean start = true;
								// boolean end = false;
								while ((line = br.readLine()) != null) {
									if (checkLine(line)) {
										sb = new StringBuilder();
										String records0 = StringUtils
												.substring(line, 1, 9);
										String records1 = StringUtils
												.substring(line, 10, 18)
												+ StringUtils.substring(line,
														19, 21)
												+ StringUtils.substring(line,
														22, 24)
												+ StringUtils.substring(line,
														25, 27);
										String records2 = StringUtils
												.substring(line, 28, 34);
										String records3 = StringUtils
												.trim(StringUtils.substring(
														line, 34, 39));
										// String records4 =
										// StringUtils.trim(StringUtils.substring(line,
										// 39, 48));
										String records5 = StringUtils
												.trim(StringUtils.substring(
														line, 48, 64));
										// String records6 =
										// StringUtils.trim(StringUtils.substring(line,
										// 65, 81));
										// String records7 =
										// StringUtils.trim(StringUtils.substring(line,
										// 81, 91));
										String records8 = StringUtils
												.trim(StringUtils.substring(
														line, 91, 96));
										if (Constant.DealType.PAYOFF
												.equals(records8)) {
											records8 = "1";
										} else {
											records8 = "2";
										}
										// String records9 =
										// StringUtils.trim(StringUtils.substring(line,
										// 96, 103));
										// String records10 =
										// StringUtils.trim(StringUtils.substring(line,
										// 103, 124));
										// String records11 =
										// StringUtils.trim(StringUtils.substring(line,
										// 124, 137));
										// String records12 =
										// StringUtils.trim(StringUtils.substring(line,
										// 137, 145));
										String records13 = StringUtils
												.trim(StringUtils.substring(
														line, 145, 160));
										String records14 = StringUtils
												.trim(StringUtils.substring(
														line, 160, 175));
										String records15 = StringUtils
												.trim(StringUtils.substring(
														line, 175, 190));
										String records16 = StringUtils
												.trim(StringUtils.substring(
														line, 190, 205));
										// String records17 =
										// StringUtils.trim(StringUtils.substring(line,
										// 205, 218));
										// String records18 =
										// StringUtils.trim(StringUtils.substring(line,
										// 218, 229));
										String records19 = StringUtils
												.trim(StringUtils.substring(
														line, 229, 248));
										String records20 = StringUtils
												.trim(StringUtils.substring(
														line, 248, 269));

										sb.append(records3)
												.append(",")
												.append(records0)
												.append(",,")
												.append(records5)
												.append(",")
												.append(records1)
												.append(records2)
												.append(records5)
												.append(",")
												.append(records8)
												.append(",")
												.append(records1)
												.append(",")
												.append(StringUtils.replace(
														Double.parseDouble(records13)
																* 100 + "",
														".0", ""))
												.append(",")
												.append(StringUtils.replace(
														Double.parseDouble(records16)
																* 100 + "",
														".0", ""))
												.append(",")
												.append(StringUtils.replace(
														Double.parseDouble(records19)
																* 100 + "",
														".0", ""))
												.append(",,,")
												.append(StringUtils.replace(
														Double.parseDouble(records14)
																* 100 + "",
														".0", ""))
												.append(",")
												.append(StringUtils.replace(
														Double.parseDouble(records15)
																* 100 + "",
														".0", ""))
												.append(",")
												.append(StringUtils.replace(
														Double.parseDouble(records20)
																* 100 + "",
														".0", ""));

										// 0(fixed-subs s 1 8) ;终端号
										// 1(fixed-subs s 10 17) ;交易时间
										// 2(fixed-subs s 28 6) ;POS 批次号
										// 3(fixed-subs s 34 5) ;渠道
										// 4(fixed-subs s 39 9) ;授权号
										// 5(fixed-subs s 48 16) ;订单号
										// 6(fixed-subs s 65 16) ;卡号
										// 7(fixed-subs s 81 10) ;卡标志
										// 8(fixed-subs s 91 5) ;交易类型
										// 9(fixed-subs s 96 7) ;业务类型
										// 10(fixed-subs s 103 21) ;是否 DCC 交易
										// 11(fixed-subs s 124 13) ;商品号
										// 12(fixed-subs s 137 8) ;期数
										// 13(fixed-subs s 145 15) ;交易金额
										// 14(fixed-subs s 160 15) ;佣金金额
										// 15(fixed-subs s 175 15) ;退货手续费
										// 16(fixed-subs s 190 15) ;积分（分）
										// 17(fixed-subs s 205 13) ;积分／元
										// 18(fixed-subs s 218 11) ;招行承担积分比例％
										// 19(fixed-subs s 229 19) ;积分价值
										// 20(fixed-subs s 248 15) ;实拨金额
										bw.write(sb.toString());
										bw.write("\n");
									}
								}
								bw.flush();
								br.close();
								bw.close();
								inRe.close();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						f.renameTo(oldSuurceFile);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean checkLine(String line) {
		if (!line.contains("********")) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		BillingFormat.exe();
	}
}

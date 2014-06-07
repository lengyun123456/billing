package com.funguide.billing.utils;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.funguide.billing.Constant;

public class MailUtils {
	public static boolean SendEmail(String[] mailInfo) {
		Properties props = new Properties();
		props.put("mail.smtp.host", Constant.MAIL_SMTP_HOST);
		props.put("mail.smtp.auth", "true");
		Session sendMailSession = Session.getInstance(props, null);
//		sendMailSession.setDebug(true);
		try {
			Transport transport = sendMailSession.getTransport("smtp");
			// 连接你的QQ，注意用户名和密码必须填写正确，否则权限得不到
			transport.connect(Constant.MAIL_SMTP_HOST, Constant.SEND_MAIL_USER, Constant.SEND_MAIL_PASSWORD);
			Message newMessage = new MimeMessage(sendMailSession);

			// mail主题
			newMessage.setSubject(mailInfo[1]);

			// 发件人地址
			String strFrom = Constant.SEND_MAIL_USER;
			strFrom = new String(strFrom.getBytes(), "UTF-8");
			newMessage.setFrom(new InternetAddress(strFrom));
			// Address addressFrom[] = { new
			// InternetAddress(Constant.SEND_MAIL_USER),new
			// InternetAddress(Constant.SEND_MAIL_USER) };
			// 改变发件人地址
			// newMessage.addFrom(addressFrom);
			// 设置收件人地址
			Address addressTo[] = { new InternetAddress(mailInfo[0]) };
			newMessage.setRecipients(Message.RecipientType.TO, addressTo);

			// 设置mail正文
			newMessage.setSentDate(new java.util.Date());
			newMessage.setText(mailInfo[2]);
			// 保存发送信息
			newMessage.saveChanges();
			// 发送邮件
			transport.sendMessage(newMessage, newMessage.getRecipients(Message.RecipientType.TO));

			transport.close();
			// Transport.send(newMessage);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}
}

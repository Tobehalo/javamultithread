package messages;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileOutputStream;

public class TextMessage {
	public static MimeMessage generate() throws Exception {
		String from = "lychen@sei.ecnu.edu.cn "; // �����˵�ַ
		String to = "chenliangyu1980@126.com"; // �ռ��˵�ַ
		
		String subject = "test";
		String body = "����,��������һ��chenliangyu�Ĳ����ʼ�";

		// ����Sessionʵ������
		Session session = Session.getDefaultInstance(new Properties());
		// ����MimeMessageʵ������
		MimeMessage message = new MimeMessage(session);
		// ���÷�����
		message.setFrom(new InternetAddress(from));
		// �����ռ���
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		// ���÷�������
		message.setSentDate(new Date());
		// �����ʼ�����
		message.setSubject(subject);
		// ���ô��ı����ݵ��ʼ�����
		message.setText(body);
		// ���沢�������յ��ʼ�����
		message.saveChanges();

		// ��MimeMessage�����е�����д�뵽�ļ���
		//msg.writeTo(new FileOutputStream("e:/test.eml"));
		return message;
	}
}

package messages;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileOutputStream;

public class HtmlMessage {
	public static MimeMessage generate() throws Exception 
	{
		String from = "lychen@sei.ecnu.edu.cn "; // �����˵�ַ
		String to = "chenliangyu1980@126.com"; // �ռ��˵�ַ
		
		String subject = "HTML�ʼ�";
		String body = "<a href=http://www.ecnu.edu.cn>" 
		  + "<h4>��ӭ��ҷ������ǵ���վ</h4></a></br>" 
		  + "<img src=\"https://news.ecnu.edu.cn/_upload/article/images/2e/e2/6b554d034c9192101208c732195e/16a6ec66-6729-4469-a5f4-0435e0f2e66a.jpg\">";

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
		// ����HTML��ʽ���ʼ�����
		message.setContent(body, "text/html;charset=gb2312");
		// ���沢�������յ��ʼ�����
		message.saveChanges();

		// ��MimeMessage�����е�����д�뵽�ļ���
		//msg.writeTo(new FileOutputStream("e:/HtmlMessage.eml"));
		return message;
	}
}

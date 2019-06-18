package messages;

import java.io.FileOutputStream;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class AttachmentMessage 
{
	public static MimeMessage generate() throws Exception
	{
		String from = "lychen@sei.ecnu.edu.cn "; // �����˵�ַ
		String to = "chenliangyu1980@126.com"; // �ռ��˵�ַ
		
        String subject = "�฽���ʼ�";        //�ʼ�����
        String body = "<a href=http://www.ecnu.edu.cn>" +
        			  "��ӭ��ҷ������ǵ���վ</a></br>"; 
      
        // ����Sessionʵ������
        Session session = Session.getDefaultInstance(new Properties());
     	// ����MimeMessageʵ������
     	MimeMessage message = new MimeMessage(session);            
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO,
        		InternetAddress.parse(to));
        message.setSubject(subject);
        
        //���������ʼ����ĺ͸����ĸ���MimeBodyPart����
        MimeBodyPart contentPart = createContent(body);
        MimeBodyPart attachPart1 = createAttachment("c:/temp/ecnu4.jpg");
        MimeBodyPart attachPart2 = createAttachment("c:/temp/ecnu5.jpg");
        
        //������������ʼ����ĺ͸�����MimeMultipart����
        MimeMultipart allMultipart = new MimeMultipart("mixed");
        allMultipart.addBodyPart(contentPart);
        allMultipart.addBodyPart(attachPart1);
        allMultipart.addBodyPart(attachPart2);
        
        //���������ʼ�����Ϊ������ϳ���MimeMultipart����
        message.setContent(allMultipart);
        message.saveChanges();
        
        //message.writeTo(new FileOutputStream("e:/ComplexMessage.eml"));
        return message;
	}
	
	public static MimeBodyPart createContent(String body) throws Exception
	{
        MimeBodyPart htmlBodyPart = new MimeBodyPart();          
        htmlBodyPart.setContent(body,"text/html;charset=gb2312");
        return htmlBodyPart;
	}
	
	public static MimeBodyPart createAttachment(String filename) throws Exception
	{
		//�������渽����MimeBodyPart���󣬲����븽�����ݺ���Ӧ��Ϣ
		MimeBodyPart attachPart = new MimeBodyPart();
        FileDataSource fds = new FileDataSource(filename);
        attachPart.setDataHandler(new DataHandler(fds));
        attachPart.setFileName(fds.getName());
		return attachPart;
	}
}

package tools;

import javax.mail.*;
import java.util.*;
import messages.*;


public class MailClientSend {
  private Session session;
  private Transport transport;
  private String username = "lychen@sei.ecnu.edu.cn";
  private String password = "1234567899";
  private String smtpServer = "webmail.ecnu.edu.cn";
  
  public void init()throws Exception
  {
	//��������
    Properties  props = new Properties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.class", "com.sun.mail.smtp.SMTPTransport");
    props.put("mail.smtp.host", smtpServer); //���÷����ʼ�������
    props.put("mail.smtp.port", "25");
    props.put("mail.smtp.auth", "true"); //SMTP��������Ҫ�����֤    

    // ����Session����
    session = Session.getInstance(props,new Authenticator(){   //�����˻� 
        public PasswordAuthentication getPasswordAuthentication() { 
          return new PasswordAuthentication(username, password); 
        }            
 });
    session.setDebug(true); //���������־
    
    // ����Transport����
    transport = session.getTransport();           
  }
  
  public void sendMessage()throws Exception{
    //����һ���ʼ�
    //Message msg = TextMessage.generate();
	//Message msg = HtmlMessage.generate();
	Message msg = AttachmentMessage.generate();
    //�����ʼ�    
    transport.connect();
    transport.sendMessage(msg, msg.getAllRecipients());
    //��ӡ���
    System.out.println("�ʼ��Ѿ��ɹ�����");
  } 
  
  public void close()throws Exception
  {
	transport.close();
  }
  
  public static void main(String[] args)throws Exception {
	  
    MailClientSend client=new MailClientSend();
    //��ʼ��
    client.init();
    //�����ʼ�
    client.sendMessage();
    //�ر�����
    client.close();
  }
}






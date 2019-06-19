package tools;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;

public class MailClientRecv {
  private Session session;
  private Store store;
  private String username = "chenliangyu1980@126.com";
  private String password = "1234567899";
  private String popServer = "pop.126.com";

  public void init()throws Exception
  {
    //��������
    Properties  props = new Properties();
    props.put("mail.store.protocol", "pop3");
    props.put("mail.imap.class", "com.sun.mail.imap.IMAPStore");
    props.put("mail.pop3.class", "com.sun.mail.pop3.POP3Store");    

    // ����Session����
    session = Session.getInstance(props,null);
    session.setDebug(false); //���������־

    // ����Store����
    store = session.getStore("pop3");
    
    //���ӵ����ʼ�������
    store.connect(popServer,username,password);
  }  
  
  public void receiveMessage()throws Exception
  {
	String folderName = "inbox";
    Folder folder=store.getFolder(folderName);
    if(folder==null)
    {
    	throw new Exception(folderName+"�ʼ��в�����");
    }
    //������
    folder.open(Folder.READ_ONLY);
    System.out.println("�����ռ�����"+folder.getMessageCount()+"���ʼ�.");
    System.out.println("�����ռ�����"+folder.getUnreadMessageCount()+"��δ�����ʼ�.");

    //���ʼ�
    Message[] messages=folder.getMessages();
    //for(int i=1;i<=messages.length;i++)
    for(int i=1;i<=3;i++)  
    {
      System.out.println("------��"+i+"���ʼ�-------");
      //��ӡ�ʼ���Ϣ
      Message message = messages[i];
      //folder.getMessage(i).writeTo(System.out);
      System.out.println((message.getFrom())[0]);
      System.out.println(message.getSubject());
      System.out.println();
    }
    folder.close(false);  //�ر��ʼ���
  }
  
  public void close()throws Exception
  {
	store.close();
  }
  
  public static void main(String[] args)throws Exception {
    MailClientRecv client=new MailClientRecv();
    //��ʼ��
    client.init();
    //�����ʼ�
    client.receiveMessage();
    //�ر�����
    client.close();
  }
}






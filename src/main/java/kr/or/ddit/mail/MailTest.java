package kr.or.ddit.mail;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class MailTest {
	
	public static void main(String[] args) {
		MailTest.naverMailSend();
	}
	
	public static void naverMailSend() {
        String host = "smtp.naver.com"; 
        String user = ""; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
        String password = ""; // 패스워드

        // SMTP 서버 정보를 설정한다.
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");
        
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("git_hub@naver.com"));

            // 메일 제목
            message.setSubject("KTKO SMTP TEST1111");

            // 메일 내용
            message.setText("KTKO Success!!");

            // send the message
            Transport.send(message);
            System.out.println("Success Message Send");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

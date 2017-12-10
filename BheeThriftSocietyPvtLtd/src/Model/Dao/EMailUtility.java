package Model.Dao;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import Model.Bean.EmailUtilityBean;

public class EMailUtility {
	
        	
		public static void sendEmail( String toAddress,
				String subject, String message) throws AddressException,
				MessagingException {

			final EmailUtilityBean bean=new EmailUtilityDao().getEmailUtilityByMaxId();
			
			// sets SMTP server properties
			Properties properties = new Properties();
			properties.put("mail.smtp.host", bean.getHost_name());
			properties.put("mail.smtp.port", bean.getPort_no());
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");

			// creates a new session with an authenticator
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(bean.getEmail_id(), bean.getPwd());
				}
			};

			Session session = Session.getInstance(properties, auth);

			// creates a new e-mail message
			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(bean.getEmail_id()));
			InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(message);
			// sends the e-mail
			Transport.send(msg);

		}
		
		public static void sendEmailWithAttachment( String toAddress,
				String subject, String message) throws AddressException,
				MessagingException {

			final EmailUtilityBean bean=new EmailUtilityDao().getEmailUtilityByMaxId();
			
			// sets SMTP server properties
			Properties properties = new Properties();
			properties.put("mail.smtp.host", bean.getHost_name());
			properties.put("mail.smtp.port", bean.getPort_no());
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");

			// creates a new session with an authenticator
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(bean.getEmail_id(), bean.getPwd());
				}
			};

			Session session = Session.getInstance(properties, auth);

			// creates a new e-mail message
			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(bean.getEmail_id()));
			InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(message);
			
			    String filename = "D://Test.pdf";//change accordingly  
			    DataSource source = new FileDataSource(filename);  
			    msg.setDataHandler(new DataHandler(source));  
			    msg.setFileName(filename);  
			
			// sends the e-mail
			Transport.send(msg);

		}
	}




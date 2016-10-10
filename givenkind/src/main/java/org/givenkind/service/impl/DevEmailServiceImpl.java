package org.givenkind.service.impl;

import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.givenkind.dto.PasswordResetAuthorizationDTO;
import org.givenkind.model.CompletedTransactions;
import org.givenkind.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevEmailServiceImpl implements EmailService {
	
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;	
	
	@Resource
	Environment env;
	
	private static final String PORT = "smtp.port";
	private static final String EMAIL = "smtp.email";
	private static final String PWD = "smtp.pwd";
	private static final String SERVER = "smtp.server";
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public Boolean ngoRegistrationEmail(String email) {
		log.info("ngoRegistrationEmail: email="+email);
		return true;
	}

	@Override
	public Boolean adminRegistrationEmail(String profileURL) {
		log.info("adminRegistrationEmail: profileURL="+profileURL);
		return true;
	}

	@Override
	public Boolean donorRegistrationEmail(String email) {
		log.info("donorRegistrationEmail: email="+email);
		return true;
	}

	@Override
	public Boolean donorItemEmail(CompletedTransactions completedTransactions, String email) {
		log.info("donorItemEmail: donatedItem="+completedTransactions.toString()+" email="+email);
		return true;
	}

	@Override
	public Boolean forgotPasswordEmail(PasswordResetAuthorizationDTO pr,String httpURL) {
		try {
			log.info("password reset link is: "+pr.getPasswordResetLink());
			String pwdlink =httpURL+pr.getPasswordResetLink();
			String forgotPWDmsg ="Hi,<br> We received a request to reset password for your giveNkind account. Click the below link to reset your password.<br>"+pwdlink+ "<br><br> Regards, <br>giveNkind Admin";
			generateAndSendEmail(pr.getUserEmail(),forgotPWDmsg);
			return true;
		} catch (Exception ex) {
			log.error("could not send password reset email", ex);
			return false;
		}
	}
	public void generateAndSendEmail(String emailid,String emailBody) throws AddressException, MessagingException {
		 
		// Step1
		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", env.getRequiredProperty(PORT));
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Mail Server Properties have been setup successfully..");
 
		// Step2
		System.out.println("\n\n 2nd ===> get Mail Session..");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailid));
		//generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(emailid));
		generateMailMessage.setSubject("Greetings from giveNkind..");		
		generateMailMessage.setContent(emailBody, "text/html");
		System.out.println("Mail Session has been created successfully..");
 
		// Step3
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");
 
		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect(env.getRequiredProperty(SERVER),env.getRequiredProperty(EMAIL), env.getRequiredProperty(PWD));
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}
}

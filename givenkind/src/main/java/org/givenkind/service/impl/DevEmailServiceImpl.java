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
import org.givenkind.model.ActiveTransactionItems;
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
	public Boolean donorRequestedEmail(String npemail) {
		
		try {	
			
			String donorRequestedMsg ="Great news!  A donor is looking to give you an item from your giveNkind wish list! Here's the next steps!<br><br>" +
					"1. Please login to your giveNkind dashboard at www.givenkind.org and select 'Active Transactions'.  Here you can accept or deny the offered item.<br>" +
					"2. If you accept the item, you can directly contact the donor through the email provided to arrange a drop off or pick up.<br>" +
					"3. Receive the donated item and provide the donor a tax receipt.<br>" +
					"4. Mark the transaction as completed on your giveNkind dashboard.<br><br>" +
					"As a courtesy to the donor, please don't forget to respond in a timely manner.<br><br>" +
					"We are excited to offer the opportunity to connect nonprofits and donors.Please let us know if you have any questions by emailing contact@givenkind.org or calling 847-802-8977.<br><br><br>" +
					"Thank you for using givenkind and for making a difference in your community!<br><br><br>" +
					"- The giveNkind Team";
			
			generateAndSendEmail(npemail,donorRequestedMsg);
			return true;
		} catch (Exception ex) {
			log.error("could not send Donor Requested email", ex);
			return false;
		}
	}

	@Override
	public Boolean npRequestedEmail(String donorEmail) {
		
		try {	
			
			String npRequestedMsg ="Great news!  A nonprofit needs an item you have on your giveNkind donor list! Here's the next steps!<br><br>" +
					"1. Please login to your giveNkind dashboard at www.givenkind.org and select 'Active Transactions'.  Here you can accept or deny the offered item.<br>" +
					"2. If you accept the request for your item, you can directly contact the nonprofit through the email provided to arrange a drop off or pick up.  Please let them know if you would like a tax receipt for your donation at this time.<br>" +
					"3. Give your donated item!<br>" +
					"4. Mark the transaction as completed on your giveNkind dashboard.<br><br>" +
					"As a courtesy to the nonprofit, please don't forget to respond in a timely manner.<br><br>" +
					"We are excited to offer the opportunity to connect nonprofits and donors.Please let us know if you have any questions by emailing contact@givenkind.org or calling 847-802-8977.<br><br><br>" +
					"Thank you for using givenkind and for making a difference in your community!<br><br><br>" +
					"- The giveNkind Team";
			
			generateAndSendEmail(donorEmail,npRequestedMsg);
			return true;
		} catch (Exception ex) {
			log.error("could not send NP Requested email", ex);
			return false;
		}
	}
	@Override
	public Boolean forgotPasswordEmail(PasswordResetAuthorizationDTO pr,String httpURL) {
		try {
			log.info("password reset link is: "+pr.getPasswordResetLink());
			String pwdlink =httpURL+pr.getPasswordResetLink();
			String forgotPWDmsg ="Hi,<br> We received a request to reset password for your giveNkind account. Click the below link to reset your password.<br>"+pwdlink+ "<br><br> We are excited to offer the opportunity to connect nonprofits and donors.  Please let us know if you have any questions by emailing contact@givenkind.org or calling 847-802-8977.<br><br>Thank you for using givenkind and for making a difference in your community!<br>-- The giveNkind Team";
			generateAndSendEmail(pr.getUserEmail(),forgotPWDmsg);
			return true;
		} catch (Exception ex) {
			log.error("could not send forgot password email", ex);
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

	@Override
	public Boolean donorItemEmail(CompletedTransactions completedTransactions,
			String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean completedTransactionEmail(String npEmail, String donorEmail) {

		try {	
			
			String donorMsg ="Thank you for using giveNkind to complete your donation!<br>" +
					"Your donation will help nonprofit organizations fulfill their mission, extend their reach in your community, and serve those in need.<br><br>" +
					"At giveNkind, we are just getting started!  We would love your feedback on how we can improve our Beta site.  Please send your suggestions for improvement, share your experience with the site, or let us know what you liked about giveNkind.  You can email us at contact@givenkind.org or call 847-802-8977.<br><br>" +
					"Thank you for using givenkind and for making a difference in your community and we hope to see you back on givenkind.org soon!<br><br><br>" +
					"- The giveNkind Team";
			
			String npMsg ="Thank you for using giveNkind to list your wish list need.  We hope the donation you received will help you carry out your organization's mission, extend your reach in the community, and serve those in need.<br><br>" +
					"At giveNkind, we are just getting started!  We would love your feedback on how we can improve our Beta site.  Please send your suggestions for improvement, share your experience with the site, or let us know what you liked about giveNkind.  You can email us at contact@givenkind.org or call 847-802-8977.<br><br>" +
					"Thank you for using givenkind and for making a difference in your community and we hope to see you back on givenkind.org soon!<br><br><br>" +
					"- The giveNkind Team";
			
			generateAndSendEmail(npEmail,npMsg);
			generateAndSendEmail(donorEmail,donorMsg);
			return true;
		} catch (Exception ex) {
			log.error("could not send Donor Requested email", ex);
			return false;
		}
	}

	
}

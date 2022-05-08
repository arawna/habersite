package nku.haber.core.utilities.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailManager {
	
	@Autowired
	private JavaMailSender emailSender;
	
	public void sendMail(String email,String subject,String text) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setText(text);
        message.setTo(email);
        message.setFrom("deneme@gmail.com");
        emailSender.send(message);
	}

}

package hk.ecommerce.services.impl;

import hk.ecommerce.services.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendAccountActivationEmail(String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("justasmtp@gmail.com");
        message.setFrom("hamza@noreply.com");
        message.setSubject("Account activation link");
        message.setText(content);
        javaMailSender.send(message);
    }

    @Override
    public void sendConfirmationEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        //setTo to the users real email just doing this for a test
        message.setTo("justasmtp@gmail.com");
        message.setFrom("hamza@noreply.com");
        message.setSubject("Activation confirmation");
        message.setText("Your account has been activated you can log in now");
        javaMailSender.send(message);
    }

}

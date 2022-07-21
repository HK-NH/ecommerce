package hk.ecommerce.services;

public interface EmailService {

    void sendAccountActivationEmail(String content);
    void sendConfirmationEmail();
}

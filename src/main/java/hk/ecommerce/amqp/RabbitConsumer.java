package hk.ecommerce.amqp;

import hk.ecommerce.services.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RabbitListener(queues = "registration", id="listener")
public class RabbitConsumer {

//    @Autowired
//    private EmailService emailService;
//
//    private static Logger logger = LogManager.getLogger(RabbitConsumer.class.toString());
//
//    @RabbitHandler
//    public void receiver(String message) {
//        emailService.sendAccountActivationEmail(message);
//    }
}

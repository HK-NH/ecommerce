package hk.ecommerce.amqp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Service;


@Service
public class RabbitSender {


    private final AmqpTemplate rabbitTemplate;
    private final Queue queue;

    private static Logger logger = LogManager.getLogger(RabbitSender.class.getName());


    public RabbitSender(AmqpTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    public void send(String message) {
        rabbitTemplate.convertAndSend(queue.getName(), "http://localhost:8080/api/auth/validateRegistration/"+message);
    }
}

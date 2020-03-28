package pl.rybialek.rabbitmqapp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer {

    private RabbitTemplate rabbitTemplate;
    private static final String TOPIC = "topic";

    @Autowired
    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/add-msg")
    public String addMsg(@RequestParam String message) {
        rabbitTemplate.convertAndSend(TOPIC, message);
        return "Sent: " + message;
    }

    @GetMapping("/receive-msg")
    public Object receiveMsg() {
        return rabbitTemplate.receiveAndConvert(TOPIC);
    }

    @RabbitListener(queues = TOPIC)
    public void listenOnMsg(String message) {
        System.out.println(message);
    }
}

package projecttest.javaspringtest.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import projecttest.javaspringtest.Kafka.Dto.OrderDto;
import projecttest.javaspringtest.Kafka.Dto.UserDto;
import projecttest.javaspringtest.Kafka.KafkaProducerService;

@RestController
@RequiredArgsConstructor
public class KafkaTestController {

    private final KafkaProducerService producer;

    @Value("${app.kafka.topic}")
    private String topic;

    @GetMapping("/kafka/send")
    public String send() {
        producer.send(topic, "ㅂㅈㄷㅂㅈㄷㅂㅈㄷ");
        return "sent: " + "ㅂㅈㄷㅂㅈㄷㅂㅈㄷ";
    }

    @GetMapping("/kafka/order")
    public String sendOrder() {
        producer.sendOrder(new OrderDto("OrderDto",1000));
        return "order sent";
    }

    @GetMapping("/kafka/user")
    public String sendUser() {
        producer.sendUser(new UserDto("UserDto","name"));
        return "user sent";
    }
}

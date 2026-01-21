package projecttest.javaspringtest.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
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
}

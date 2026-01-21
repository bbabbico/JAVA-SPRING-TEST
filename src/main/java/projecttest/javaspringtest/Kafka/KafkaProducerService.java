package projecttest.javaspringtest.Kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import projecttest.javaspringtest.Kafka.Dto.OrderDto;
import projecttest.javaspringtest.Kafka.Dto.UserDto;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    public void sendOrder(OrderDto dto) {
        kafkaTemplate.send("order-topic", dto.getOrderId(), dto);
    }

    public void sendUser(UserDto dto) {
        kafkaTemplate.send("user-topic", dto.getUserId(), dto);
    }
}

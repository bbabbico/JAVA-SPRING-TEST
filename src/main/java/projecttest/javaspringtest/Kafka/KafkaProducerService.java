package projecttest.javaspringtest.Kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import projecttest.javaspringtest.Database.Mybatis.MybatisMember;
import projecttest.javaspringtest.Kafka.Dto.UserDto;

import static projecttest.javaspringtest.Kafka.KafkaTopicConfig.MYBATIS_TOPIC;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    public void sendMybatisMember(MybatisMember member) {
        kafkaTemplate.send(MYBATIS_TOPIC, member);
    }

    public void sendUser(UserDto dto) {
        kafkaTemplate.send("user-topic", dto.getUserId(), dto);
    }
}

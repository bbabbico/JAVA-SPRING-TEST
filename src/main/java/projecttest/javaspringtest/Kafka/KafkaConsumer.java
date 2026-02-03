package projecttest.javaspringtest.Kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import projecttest.javaspringtest.Spring.Database.Mybatis.MybatisMember;
import projecttest.javaspringtest.Kafka.Dto.UserDto;

import static projecttest.javaspringtest.Kafka.KafkaTopicConfig.MYBATIS_TOPIC;
import static projecttest.javaspringtest.Kafka.KafkaTopicConfig.USER_TOPIC;

@Slf4j
@Component
public class KafkaConsumer {

//    @KafkaListener(topics = "${app.kafka.topic}", groupId = "group-1")
//    public void listen(String message) {
//        log.debug("받음: {}", message);
//    }

    @KafkaListener(
            topics = MYBATIS_TOPIC,
            groupId = "group-1",
            containerFactory = "MybatisMemberInsertKafkaListenerContainerFactory"
    )
    public void listenOrder(MybatisMember member) {
        log.debug("Kafka consumer listen Mybatis Member insert :{}", member.toString());
    }

    @KafkaListener(
            topics = USER_TOPIC,
            groupId = "group-1",
            containerFactory = "userKafkaListenerContainerFactory"
    )
    public void listenUser(UserDto dto) {
        log.debug("USER: userId={}, name={}", dto.getUserId(), dto.getName());
    }

}


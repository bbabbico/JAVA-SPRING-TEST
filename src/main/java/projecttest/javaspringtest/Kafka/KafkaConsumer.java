package projecttest.javaspringtest.Kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import projecttest.javaspringtest.Kafka.Dto.OrderDto;
import projecttest.javaspringtest.Kafka.Dto.UserDto;

import static projecttest.javaspringtest.Kafka.KafkaTopicConfig.ORDER_TOPIC;
import static projecttest.javaspringtest.Kafka.KafkaTopicConfig.USER_TOPIC;

@Slf4j
@Component
public class KafkaConsumer {

//    @KafkaListener(topics = "${app.kafka.topic}", groupId = "group-1")
//    public void listen(String message) {
//        log.debug("받음: {}", message);
//    }

    @KafkaListener(
            topics = ORDER_TOPIC,
            groupId = "group-1",
            containerFactory = "orderKafkaListenerContainerFactory"
    )
    public void listenOrder(OrderDto dto) {
        log.debug("ORDER: orderId={}, price={}", dto.getOrderId(), dto.getPrice());
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


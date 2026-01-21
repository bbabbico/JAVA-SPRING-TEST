package projecttest.javaspringtest.Kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "${app.kafka.topic}", groupId = "group-1")
    public void listen(String message) {
        log.info("받음: {}", message);
    }
}


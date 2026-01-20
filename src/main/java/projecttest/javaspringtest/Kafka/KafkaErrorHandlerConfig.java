package projecttest.javaspringtest.Kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;

@Slf4j
@Configuration
public class KafkaErrorHandlerConfig {

    @Bean(name = "customErrorHandler")
    public KafkaListenerErrorHandler customErrorHandler() {
        return (message, exception) -> {
            log.error("Listener error. message={}", message, exception);
            return null; // 필요하면 fallback 값 리턴
        };
    }
}

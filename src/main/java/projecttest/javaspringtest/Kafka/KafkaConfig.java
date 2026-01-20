package projecttest.javaspringtest.Kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultErrorHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
    Map<String, Object> config = new HashMap<>();
    config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // 브로커 주소

    // Producer가 Key와 Value 값의 데이터를 Kafka 브로커로 전송하기 전에 데이터를 byte array로 변환하는 데 사용하는 직렬화 메커니즘을 설정함.
    // Kafka는 네트워크를 통해 데이터를 전송하기 때문에, 객체를 byte array로 변환하는 직렬화 과정이 필요 따라서, StringSerializer를 사용해 직렬화함.
    config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

    return new DefaultKafkaProducerFactory<>(config);
    }

    /**
     * Kafka 메시지를 전송하기 위한 템플릿 Bean
     * 실제 애플리케이션에서 메시지 전송시 사용됨
     *
     * @return KafkaTemplate<String, String>
     */
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
    }
    /**
     * Consumer 설정을 위한 Factory Bean
     * 메시지 소비자의 역직렬화 설정 및 그룹 ID, 오프셋 설정을 담당
     *
     * @return ConsumerFactory<String, String>
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
//            ConsumerFactory<String, String> consumerFactory) {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//
//        // Consumer 팩토리 설정
//        factory.setConsumerFactory(consumerFactory);
//
//        // 동시성 설정 - 3개의 스레드로 병렬 처리
//        factory.setConcurrency(3);
//
//        // 자동 시작 설정
//        factory.setAutoStartup(true);
//
//        // 배치 처리 모드 설정
//        factory.setBatchListener(true);
//
//        // 승인 모드 설정 - 배치 단위로 커밋
//        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.BATCH);
//
//        // 에러 핸들러 설정
//        factory.setCommonErrorHandler(new DefaultErrorHandler(
//                (consumerRecord, exception) -> {
//                    log.error("Error in processing: {}", exception.getMessage());
//                    log.error("Failed record: {}", consumerRecord);
//
//                    // 추가적인 에러 처리 로직
//                    // 예: 데드 레터 큐로 전송, 알림 발송 등
//                }
//        ));
//
//        return factory;
//    }

}

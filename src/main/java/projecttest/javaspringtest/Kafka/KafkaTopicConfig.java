package projecttest.javaspringtest.Kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic exampleTopic() {
        return TopicBuilder.name("example-topic")
                .partitions(3)                     // 파티션 수 설정
                .replicas(2)                       // 복제 팩터 설정
                .config(                           // 추가 설정
                        TopicConfig.RETENTION_MS_CONFIG,
                        String.valueOf(7 * 24 * 60 * 60 * 1000L)  // 7일
                )
                .build();
    }

    @Bean
    public NewTopic compactTopic() {
        return TopicBuilder.name("compact-topic")
                .partitions(1)
                .replicas(1)
                .compact()                         // 압축 정책 설정
                .build();
    }
}

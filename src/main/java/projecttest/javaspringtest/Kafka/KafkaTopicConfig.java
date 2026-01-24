package projecttest.javaspringtest.Kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    public static final String MYBATIS_TOPIC = "mybatis-member-insert-topic";
    public static final String USER_TOPIC  = "user-topic";

    @Bean
    public NewTopic exampleTopic() {
        return TopicBuilder.name("example-topic")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic orderTopic() {
        return TopicBuilder.name(MYBATIS_TOPIC).partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic userTopic() {
        return TopicBuilder.name(USER_TOPIC).partitions(1).replicas(1).build();
    }
}

package projecttest.javaspringtest.Kafka;

import java.util.Map;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;

import projecttest.javaspringtest.Spring.Database.Mybatis.MybatisMember;
import projecttest.javaspringtest.Kafka.Dto.UserDto;

@Configuration
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;

    public KafkaConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    // Producer: Object -> JSON
    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> props = kafkaProperties.buildProducerProperties();
        return new DefaultKafkaProducerFactory<>(
                props,
                new StringSerializer(),
                new JacksonJsonSerializer<>().noTypeInfo()
        );
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> pf) {
        return new KafkaTemplate<>(pf);
    }

    // Consumer (MybatisMember)
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MybatisMember> MybatisMemberInsertKafkaListenerContainerFactory() {
        Map<String, Object> props = kafkaProperties.buildConsumerProperties();

        var valueDeserializer = new JacksonJsonDeserializer<>(MybatisMember.class)
                .ignoreTypeHeaders()
                .trustedPackages("com.example.demo.kafka");

        ConsumerFactory<String, MybatisMember> cf =
                new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), valueDeserializer);

        var factory = new ConcurrentKafkaListenerContainerFactory<String, MybatisMember>();
        factory.setConsumerFactory(cf);
        return factory;
    }

    // Consumer (UserDto)
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserDto> userKafkaListenerContainerFactory() {
        Map<String, Object> props = kafkaProperties.buildConsumerProperties();

        var valueDeserializer = new JacksonJsonDeserializer<>(UserDto.class)
                .ignoreTypeHeaders()
                .trustedPackages("com.example.demo.kafka");

        ConsumerFactory<String, UserDto> cf =
                new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), valueDeserializer);

        var factory = new ConcurrentKafkaListenerContainerFactory<String, UserDto>();
        factory.setConsumerFactory(cf);
        return factory;
    }
}

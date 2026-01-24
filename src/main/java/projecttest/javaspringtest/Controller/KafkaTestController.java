package projecttest.javaspringtest.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import projecttest.javaspringtest.Database.Mybatis.MybatisMember;
import projecttest.javaspringtest.Database.Mybatis.MybatisMemberService;
import projecttest.javaspringtest.Kafka.Dto.UserDto;
import projecttest.javaspringtest.Kafka.KafkaProducerService;

@RestController
@RequiredArgsConstructor
public class KafkaTestController {

    private final KafkaProducerService producer;
    private final MybatisMemberService mybatisMemberService;

    @Value("${app.kafka.topic}")
    private String topic;

    @GetMapping("/kafka/send")
    public String send() {
        producer.send(topic, "ㅂㅈㄷㅂㅈㄷㅂㅈㄷ");
        return "sent: " + "ㅂㅈㄷㅂㅈㄷㅂㅈㄷ";
    }

    @GetMapping("/kafka/user")
    public String sendUser() {
        producer.sendUser(new UserDto("UserDto","name"));
        return "user sent";
    }

    @GetMapping("/kafka/memberinsert")
    public String sendMemberInsert() {
        mybatisMemberService.insert(new MybatisMember("qwe","qweqwe","eee"));
        return "member inserted";
    }
}

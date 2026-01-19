package projecttest.javaspringtest.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import projecttest.javaspringtest.Kafka.KafkaConsumerService;
import projecttest.javaspringtest.Kafka.KafkaProducerService;

@Controller
@RequiredArgsConstructor
public class MainController {
    final private KafkaProducerService kafkaProducerService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/kafka")
    public String kafka(){
        kafkaProducerService.sendMessage("Kafka 메시지 전송");
        return "kafka";
    }

    


}

package projecttest.javaspringtest.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import projecttest.javaspringtest.Native.TestJNI;

@Controller
@Slf4j
public class NativeController {
    @GetMapping("/native")
    public String test(){
        TestJNI testJNI = new TestJNI();
        System.out.println(System.getProperty("java.library.path"));
        log.info("C 언어 호출 값 : {}",testJNI.add(2,9));
        return "native";
    }
}

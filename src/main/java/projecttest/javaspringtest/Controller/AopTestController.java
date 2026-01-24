package projecttest.javaspringtest.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import projecttest.javaspringtest.AOP.LogAopTestService;

@Controller
@RequiredArgsConstructor
public class AopTestController {

    private final LogAopTestService logAopTestService;

    @GetMapping("/AOP")
    public String AopTest() {
        logAopTestService.LogAopTest();
        return "aop";
    }
}

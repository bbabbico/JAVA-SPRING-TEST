package projecttest.javaspringtest.AOP;

import org.springframework.stereotype.Service;

@Service
public class LogAopTestService {

    @LogExecutionTime
    public void LogAopTest() {
        // 로그 테스트 1초
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
    }
}

package projecttest.javaspringtest.Spring.Event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
public class MemberEventListener {

    @EventListener
    public void EventListener(EventDto eventDto) {
        log.info("회원가입 됨. loginid={}", eventDto.loginid());
    }

    /**
     * 기본값은 AFTER_COMMIT (커밋 성공 후 실행)
     * AFTER_COMMIT (기본값): 트랜잭션 성공 후 실행
     * AFTER_ROLLBACK: 트랜잭션 실패(롤백) 시 실행
     * BEFORE_COMMIT: 커밋 직전 실행
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleAfterCommit(EventDto eventDto) {
        System.out.println("DB 커밋 완료 후 실행됨.");
    }
}

package projecttest.javaspringtest.Spring.Event;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import projecttest.javaspringtest.Spring.Database.JPA.JPAMember;
import projecttest.javaspringtest.Spring.Database.JPA.JPAMemberRepository;

@Service
@RequiredArgsConstructor
public class EventService {
    private final JPAMemberRepository jpaMemberRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public JPAMember createMember(JPAMember member) {
        // 회원 생성
        JPAMember savedMember = jpaMemberRepository.save(member);


        // 이벤트 발행
        eventPublisher.publishEvent(
                new EventDto(
                        savedMember.getId(),
                        savedMember.getLoginid()
                )
        );
        return savedMember;
    }

}

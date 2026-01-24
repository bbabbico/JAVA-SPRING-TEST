package projecttest.javaspringtest.Database.Mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MybatisMemberService {
    private final MybatisMemberRepository mybatisMemberRepository;

    public void insert(MybatisMember member){
        mybatisMemberRepository.insert(member);
        log.info("insert success");
    }
}

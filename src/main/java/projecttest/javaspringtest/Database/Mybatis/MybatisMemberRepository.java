package projecttest.javaspringtest.Database.Mybatis;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MybatisMemberRepository {
    int insert(MybatisMember member);             // member.id 자동 세팅
    Optional<MybatisMember> findById(Long id);
    Optional<MybatisMember> findByLoginid(String loginid);

    List<MybatisMember> findAll();

    int update(MybatisMember member);             // 전체 컬럼 업데이트 예시
    int deleteById(Long id);
}

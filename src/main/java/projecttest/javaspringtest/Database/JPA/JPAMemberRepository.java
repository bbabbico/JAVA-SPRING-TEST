package projecttest.javaspringtest.Database.JPA;

import org.springframework.data.repository.CrudRepository;
import projecttest.javaspringtest.Database.Mybatis.MybatisMember;

public interface JPAMemberRepository extends CrudRepository<JPAMember,Long> {
}

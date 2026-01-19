package projecttest.javaspringtest.Database.Mybatis;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MybatisMember {
    private Long id;
    private String loginid;
    private String password;
    private String role;
}

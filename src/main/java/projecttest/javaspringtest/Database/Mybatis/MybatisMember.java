package projecttest.javaspringtest.Database.Mybatis;

import lombok.AllArgsConstructor;
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

    public MybatisMember(String loginid, String password, String role) {
        this.loginid = loginid;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "MybatisMember{" +
                "id=" + id +
                ", loginid='" + loginid + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

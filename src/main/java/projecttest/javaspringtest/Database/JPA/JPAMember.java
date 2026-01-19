package projecttest.javaspringtest.Database.JPA;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class JPAMember {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100 , nullable = false)
    private String loginid;

    @Column(length = 100 , nullable = false)
    private String password;

    @Column(length = 100 , nullable = false)
    private String role;


}

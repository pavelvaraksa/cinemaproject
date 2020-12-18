package by.itacademy.domain.hibernate;

import by.itacademy.domain.SystemRoles;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "m_role")
public class UserRoleApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private SystemRoles roleName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserApp user;

    public UserRoleApp() {
    }

    public UserRoleApp(SystemRoles roleName, UserApp user) {
        this.roleName = roleName;
        this.user = user;
    }
}

package by.itacademy.domain.hibernate;

import by.itacademy.domain.SystemRoles;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.EnumType;
import javax.persistence.JoinColumn;

@Data
@Entity
@Table(name = "m_role")
public class UserRoleHibernate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private SystemRoles roleName;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserHibernate user;

    public UserRoleHibernate() {
    }

    public UserRoleHibernate(SystemRoles roleName, UserHibernate user) {
        this.roleName = roleName;
        this.user = user;
    }
}

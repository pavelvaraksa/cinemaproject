package by.itacademy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "m_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private SystemRoles roleName;

    @OneToOne
    @JoinColumn(name = "user_id")
    //@JsonBackReference
    @JsonManagedReference
    @JsonIgnore
    private User user;

    public UserRole() {
    }

    public UserRole(SystemRoles roleName, User user) {
        this.roleName = roleName;
        this.user = user;
    }
}
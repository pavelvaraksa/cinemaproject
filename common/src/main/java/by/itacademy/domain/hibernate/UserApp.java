package by.itacademy.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@Data
@Entity
@Table(name = "m_user")
@EqualsAndHashCode(exclude = {"roles"})
public class UserApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<UserRoleApp> roles = Collections.emptySet();
}

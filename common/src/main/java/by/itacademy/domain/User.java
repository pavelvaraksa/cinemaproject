package by.itacademy.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "m_user")
@EqualsAndHashCode(exclude = {"tickets", "role"})
public class User {

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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonBackReference
    //@JsonManagedReference
    private UserRole role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<Ticket> tickets = Collections.emptySet();
}

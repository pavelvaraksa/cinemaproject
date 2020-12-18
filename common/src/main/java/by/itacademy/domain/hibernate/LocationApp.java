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
@Table(name = "m_location")
@EqualsAndHashCode(exclude = {"cinemas"})
public class LocationApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String location;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<CinemaApp> cinemas = Collections.emptySet();
}

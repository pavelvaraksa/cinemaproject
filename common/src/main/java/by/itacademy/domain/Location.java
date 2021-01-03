package by.itacademy.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "m_location")
@EqualsAndHashCode(exclude = {"movies"})
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String location;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @ManyToMany(mappedBy = "locations", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("locations")
    private Set<Movie> movies = Collections.emptySet();

    @Override
    public String toString() {
        return location;
    }
}

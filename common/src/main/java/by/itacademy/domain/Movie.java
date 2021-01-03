package by.itacademy.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "m_movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String genre;

    @Column
    private int year;

    @Column
    private int duration;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "m_cinema",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    @JsonIgnoreProperties("movies")
    private Set<Location> locations = Collections.emptySet();

    @Override
    public String toString() {
        return title;
    }
}

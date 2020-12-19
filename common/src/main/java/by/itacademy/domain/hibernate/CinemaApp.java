package by.itacademy.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@Data
@Entity
@Table(name = "m_cinema")
@EqualsAndHashCode(exclude = {"events"})
public class CinemaApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "movie_id")
    private Long movieId;

    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<EventApp> events = Collections.emptySet();

    @ManyToOne
    @JoinColumn(name = "location_id", insertable = false, updatable = false)
    @JsonBackReference
    private LocationApp location;

    @ManyToOne
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    @JsonBackReference
    private MovieApp movie;

}


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
@Table(name = "m_event")
@EqualsAndHashCode(exclude = {"tickets"})
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String date;

    @Column
    private String time;

    @Column(name = "tickets_count")
    private int ticketsCount;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "cinema_id")
    private Long cinemaId;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<Ticket> tickets = Collections.emptySet();

    @ManyToOne
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    @JsonBackReference
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "cinema_id", insertable = false, updatable = false)
    @JsonBackReference
    private Cinema cinema;

    @Override
    public String toString() {
        return "with movie_id " + movieId + " and cinema_id " + cinemaId;
    }
}

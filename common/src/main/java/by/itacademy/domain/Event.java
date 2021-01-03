package by.itacademy.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "m_event")
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

    @Column(name = "ticket_id")
    private Long ticketId;

    @ManyToOne
    @JoinColumn(name = "ticket_id", insertable = false, updatable = false)
    @JsonBackReference
    private Ticket ticket;

    @Override
    public String toString() {
        return "with movie_id " + movieId + " and cinema_id " + cinemaId;
    }
}

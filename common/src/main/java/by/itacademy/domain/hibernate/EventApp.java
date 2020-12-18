package by.itacademy.domain.hibernate;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "m_event")
public class EventApp {

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
}

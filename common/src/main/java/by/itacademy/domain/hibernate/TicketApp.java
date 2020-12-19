package by.itacademy.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "m_ticket")
public class TicketApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "place_number")
    private int placeNumber;

    @Column
    private double price;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "event_id")
    private Long eventId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonBackReference
    private UserApp user;

    @ManyToOne
    @JoinColumn(name = "event_id", insertable = false, updatable = false)
    @JsonBackReference
    private EventApp event;
}

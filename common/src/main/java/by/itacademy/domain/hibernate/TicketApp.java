package by.itacademy.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

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
}

package by.itacademy.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;

import java.sql.Timestamp;


@Getter
@Setter
@Entity
@Table(name = "m_ticket")
@EqualsAndHashCode(exclude = {"user", "event"})
public class Ticket {

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

    @JsonIgnore
    @Column(name = "user_id")
    private Long userId;

    @JsonIgnore
    @Column(name = "event_id")
    private Long eventId;

    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonBackReference
    private User user;
}

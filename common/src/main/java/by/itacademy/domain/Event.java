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
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;

import java.sql.Timestamp;

import java.util.Collections;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "m_event")
@EqualsAndHashCode(exclude = {"cinemas"})
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

    @JsonIgnore
    @Column(name = "cinema_id")
    private Long cinemaId;

    @JsonIgnore
    @Column(name = "ticket_id")
    private Long ticketId;

    @OneToOne
    @JoinColumn(name = "ticket_id", insertable = false, updatable = false)
    @JsonBackReference
    private Ticket ticket;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<Cinema> cinemas = Collections.emptySet();
}

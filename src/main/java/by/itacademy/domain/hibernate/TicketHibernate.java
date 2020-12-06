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
@Table(name = "m_ticket")
public class TicketHibernate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int placeNumber;

    @Column
    private int price;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column
    private Long userId;

    @Column
    private Long eventId;
}

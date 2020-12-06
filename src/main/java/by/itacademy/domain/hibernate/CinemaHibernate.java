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
@Table(name = "m_cinema")
public class CinemaHibernate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int ticketsCount;

    @Column
    private String phoneNumber;

    @Column
    private String paymentMethod;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column
    private Long locationId;

    @Column
    private Long movieId;
}

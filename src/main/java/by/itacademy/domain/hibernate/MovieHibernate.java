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
@Table(name = "m_movie")
public class MovieHibernate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String genre;

    @Column
    private int year;

    @Column
    private int duration;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;
}

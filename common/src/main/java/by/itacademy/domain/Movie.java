package by.itacademy.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GenerationType;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "m_movie")
public class Movie {

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

    @JsonIgnore
    @Column(name = "cinema_id")
    private Long cinemaId;

    @ManyToOne
    @JoinColumn(name = "cinema_id", insertable = false, updatable = false)
    @JsonBackReference
    private Cinema cinema;
}

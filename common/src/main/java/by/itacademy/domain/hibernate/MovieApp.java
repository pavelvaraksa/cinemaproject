package by.itacademy.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@Data
@Entity
@Table(name = "m_movie")
public class MovieApp {

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

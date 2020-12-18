package by.itacademy.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "m_cinema")
public class CinemaApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "movie_id")
    private Long movieId;

    @ManyToOne
    @JoinColumn(name = "location_id", insertable = false, updatable = false)
    @JsonBackReference
    private LocationApp location;
}

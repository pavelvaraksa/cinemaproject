package by.itacademy.controller.hibernate;

import by.itacademy.controller.request.CinemaCreateRequest;
import by.itacademy.dao.hibernate.CinemaHibernateRepository;
import by.itacademy.domain.hibernate.CinemaHibernate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/rest/cinemas/hibernate")
@RequiredArgsConstructor
public class CinemaHibernateController {

    private final CinemaHibernateRepository cinemaHibernateRepository;

    @GetMapping
    public ResponseEntity<List<CinemaHibernate>> findAllCinemas() {
        return new ResponseEntity<>(cinemaHibernateRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CinemaHibernate findCinemaById(@PathVariable Long id) {
        return cinemaHibernateRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CinemaHibernate saveCinema(@RequestBody CinemaCreateRequest cinemaCreateRequest) {
        CinemaHibernate cinema = new CinemaHibernate();
        cinema.setTicketsCount(cinemaCreateRequest.getTicketsCount());
        cinema.setPhoneNumber(cinemaCreateRequest.getPhoneNumber());
        cinema.setPaymentMethod(cinemaCreateRequest.getPaymentMethod());
        cinema.setCreated(new Timestamp(System.currentTimeMillis()));
        cinema.setChanged(new Timestamp(System.currentTimeMillis()));
        cinema.setLocationId(cinemaCreateRequest.getLocationId());
        cinema.setMovieId(cinemaCreateRequest.getMovieId());

        return cinemaHibernateRepository.save(cinema);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CinemaHibernate updateCinema(@PathVariable Long id,
                                        @RequestBody CinemaCreateRequest cinemaCreateRequest) {

        CinemaHibernate cinema = cinemaHibernateRepository.findById(id);

        cinema.setTicketsCount(cinemaCreateRequest.getTicketsCount());
        cinema.setPhoneNumber(cinemaCreateRequest.getPhoneNumber());
        cinema.setPaymentMethod(cinemaCreateRequest.getPaymentMethod());
        cinema.setChanged(new Timestamp(System.currentTimeMillis()));
        cinema.setLocationId(cinemaCreateRequest.getLocationId());
        cinema.setMovieId(cinemaCreateRequest.getMovieId());
        return cinemaHibernateRepository.update(cinema);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteCinema(@PathVariable Long id) {
        return cinemaHibernateRepository.delete(id);
    }
}

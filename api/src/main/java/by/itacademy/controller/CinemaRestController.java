package by.itacademy.controller;

import by.itacademy.controller.request.CinemaCreateRequest;
import by.itacademy.repository.CinemaRepository;
import by.itacademy.domain.Cinema;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.sql.Timestamp;
import java.util.List;

@Log4j
@RestController
@RequestMapping("/rest/cinemas/hibernate")
@RequiredArgsConstructor
public class CinemaRestController {

    private final CinemaRepository cinemaRepository;

    @GetMapping
    public ResponseEntity<List<Cinema>> findAllCinemas() {
        return new ResponseEntity<>(cinemaRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cinema findCinemaById(@PathVariable Long id) {
        try {
            Cinema ticket = cinemaRepository.findById(id);
            log.info("Ok");
            return ticket;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cinema saveCinema(@RequestBody CinemaCreateRequest cinemaCreateRequest) {
        Cinema cinema = new Cinema();
        cinema.setName(cinemaCreateRequest.getName());
        cinema.setAddress(cinemaCreateRequest.getAddress());
        cinema.setPhoneNumber(cinemaCreateRequest.getPhoneNumber());
        cinema.setPaymentMethod(cinemaCreateRequest.getPaymentMethod());
        cinema.setCreated(new Timestamp(System.currentTimeMillis()));
        cinema.setChanged(new Timestamp(System.currentTimeMillis()));
        cinema.setLocationId(cinemaCreateRequest.getLocationId());
        cinema.setMovieId(cinemaCreateRequest.getMovieId());

        return cinemaRepository.save(cinema);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cinema updateCinema(@PathVariable Long id,
                               @RequestBody CinemaCreateRequest cinemaCreateRequest) {

        Cinema cinema;
        try {
            cinema = cinemaRepository.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

        log.info("Ok");

        cinema.setName(cinemaCreateRequest.getName());
        cinema.setAddress(cinemaCreateRequest.getAddress());
        cinema.setPhoneNumber(cinemaCreateRequest.getPhoneNumber());
        cinema.setPaymentMethod(cinemaCreateRequest.getPaymentMethod());
        cinema.setChanged(new Timestamp(System.currentTimeMillis()));
        cinema.setLocationId(cinemaCreateRequest.getLocationId());
        cinema.setMovieId(cinemaCreateRequest.getMovieId());
        return cinemaRepository.update(cinema);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteCinema(@PathVariable Long id) {
        return cinemaRepository.delete(id);
    }
}

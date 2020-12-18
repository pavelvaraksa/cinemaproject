package by.itacademy.controller;

import by.itacademy.controller.request.CinemaCreateRequest;
import by.itacademy.domain.Cinema;
import by.itacademy.service.CinemaService;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequestMapping("/rest/cinemas")
@RequiredArgsConstructor
public class CinemaRestController {

    private final CinemaService cinemaService;

    @GetMapping
    public ResponseEntity<List<Cinema>> findAllCinemas() {
        return new ResponseEntity<>(cinemaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cinema findCinemaById(@PathVariable Long id) {
        return cinemaService.findById(id);
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
        return cinemaService.save(cinema);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cinema updateCinema(@PathVariable Long id,
                               @RequestBody CinemaCreateRequest cinemaCreateRequest) {

        Cinema cinema = cinemaService.findById(id);

        cinema.setName(cinemaCreateRequest.getName());
        cinema.setAddress(cinemaCreateRequest.getAddress());
        cinema.setPhoneNumber(cinemaCreateRequest.getPhoneNumber());
        cinema.setPaymentMethod(cinemaCreateRequest.getPaymentMethod());
        cinema.setChanged(new Timestamp(System.currentTimeMillis()));
        cinema.setLocationId(cinemaCreateRequest.getLocationId());
        cinema.setMovieId(cinemaCreateRequest.getMovieId());
        return cinemaService.update(cinema);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteCinema(@PathVariable Long id) {
        return cinemaService.delete(id);
    }
}

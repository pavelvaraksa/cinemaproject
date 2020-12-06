package by.itacademy.controller;

import by.itacademy.controller.request.CinemaCreateRequest;
import by.itacademy.domain.Cinema;
import by.itacademy.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        cinema.setTicketsCount(cinemaCreateRequest.getTicketsCount());
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
        cinema.setTicketsCount(cinemaCreateRequest.getTicketsCount());
        cinema.setPhoneNumber(cinemaCreateRequest.getPhoneNumber());
        cinema.setPaymentMethod(cinemaCreateRequest.getPaymentMethod());
        cinema.setChanged(new Timestamp(System.currentTimeMillis()));
        cinema.setLocationId(cinemaCreateRequest.getLocationId());
        cinema.setMovieId(cinemaCreateRequest.getMovieId());
        return cinemaService.update(cinema);
    }

    @DeleteMapping("/{cinema}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteCinema(@PathVariable Long cinema) {
        return cinemaService.delete(cinema);
    }
}

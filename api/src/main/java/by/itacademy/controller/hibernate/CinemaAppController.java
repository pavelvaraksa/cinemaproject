package by.itacademy.controller.hibernate;

import by.itacademy.controller.request.CinemaCreateRequest;
import by.itacademy.dao.hibernate.CinemaAppRepository;
import by.itacademy.domain.hibernate.CinemaApp;
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
@RequestMapping("/rest/cinemas/hibernate")
@RequiredArgsConstructor
public class CinemaAppController {

    private final CinemaAppRepository cinemaAppRepository;

    @GetMapping
    public ResponseEntity<List<CinemaApp>> findAllCinemas() {
        return new ResponseEntity<>(cinemaAppRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CinemaApp findCinemaById(@PathVariable Long id) {
        return cinemaAppRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CinemaApp saveCinema(@RequestBody CinemaCreateRequest cinemaCreateRequest) {
        CinemaApp cinema = new CinemaApp();
        cinema.setName(cinemaCreateRequest.getName());
        cinema.setAddress(cinemaCreateRequest.getAddress());
        cinema.setPhoneNumber(cinemaCreateRequest.getPhoneNumber());
        cinema.setPaymentMethod(cinemaCreateRequest.getPaymentMethod());
        cinema.setCreated(new Timestamp(System.currentTimeMillis()));
        cinema.setChanged(new Timestamp(System.currentTimeMillis()));
        cinema.setLocationId(cinemaCreateRequest.getLocationId());
        cinema.setMovieId(cinemaCreateRequest.getMovieId());

        return cinemaAppRepository.save(cinema);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CinemaApp updateCinema(@PathVariable Long id,
                                  @RequestBody CinemaCreateRequest cinemaCreateRequest) {

        CinemaApp cinema = cinemaAppRepository.findById(id);

        cinema.setName(cinemaCreateRequest.getName());
        cinema.setAddress(cinemaCreateRequest.getAddress());
        cinema.setPhoneNumber(cinemaCreateRequest.getPhoneNumber());
        cinema.setPaymentMethod(cinemaCreateRequest.getPaymentMethod());
        cinema.setChanged(new Timestamp(System.currentTimeMillis()));
        cinema.setLocationId(cinemaCreateRequest.getLocationId());
        cinema.setMovieId(cinemaCreateRequest.getMovieId());
        return cinemaAppRepository.update(cinema);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteCinema(@PathVariable Long id) {
        return cinemaAppRepository.delete(id);
    }
}

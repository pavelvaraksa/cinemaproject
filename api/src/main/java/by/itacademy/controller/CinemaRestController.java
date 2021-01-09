package by.itacademy.controller;

import by.itacademy.controller.request.CinemaCreateRequest;
import by.itacademy.exception.ControllerException;
import by.itacademy.exception.ServiceException;
import by.itacademy.domain.Cinema;
import by.itacademy.service.CinemaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/rest/cinemas")
@RequiredArgsConstructor
public class CinemaRestController {

    private final CinemaService cinemaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cinema> findAllCinemas() throws ControllerException {
        try {
            return cinemaService.findAll();
        } catch (ServiceException e) {
            log.error("Can't find any cinemas.");
            throw new ControllerException("Can't find any cinemas." + e.getMessage());
        }
    }

    @GetMapping("/{cinemaId}")
    @ResponseStatus(HttpStatus.OK)
    public Cinema findCinemaById(@PathVariable Long cinemaId) throws ControllerException {
        try {
            return cinemaService.findById(cinemaId);
        } catch (ServiceException e) {
            log.error("Can't find an cinema.");
            throw new ControllerException("Can't find an cinema." + e.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cinema saveCinema(@RequestBody CinemaCreateRequest cinemaCreateRequest) throws ControllerException {
        String name = cinemaCreateRequest.getName();
        String address = cinemaCreateRequest.getAddress();

        if (name == null || name.isEmpty() || address == null || address.isEmpty()) {
            throw new ControllerException("Cinema name or address can't be null or empty.");
        }

        Cinema cinemaToSave = new Cinema();
        cinemaToSave.setName(cinemaCreateRequest.getName());
        cinemaToSave.setAddress(cinemaCreateRequest.getAddress());
        cinemaToSave.setPhoneNumber(cinemaCreateRequest.getPhoneNumber());
        cinemaToSave.setPaymentMethod(cinemaCreateRequest.getPaymentMethod());
        cinemaToSave.setCreated(new Timestamp(System.currentTimeMillis()));
        cinemaToSave.setChanged(new Timestamp(System.currentTimeMillis()));
        cinemaToSave.setLocationId(cinemaCreateRequest.getLocationId());
        cinemaToSave.setMovieId(cinemaCreateRequest.getMovieId());

        try {
            return cinemaService.save(cinemaToSave);
        } catch (ServiceException e) {
            log.error("Can't save a cinema.");
            throw new ControllerException("Can't save a cinema." + e.getMessage());
        }
    }

    @PutMapping("/{cinemaId}")
    @ResponseStatus(HttpStatus.OK)
    public Cinema updateCinema(@PathVariable Long cinemaId,
                               @RequestBody CinemaCreateRequest cinemaCreateRequest) throws ControllerException {
        String name = cinemaCreateRequest.getName();
        String address = cinemaCreateRequest.getAddress();

        if (name == null || name.isEmpty() || address == null || address.isEmpty()) {
            throw new ControllerException("Cinema name or address can't be null or empty.");
        }

        try {
            Cinema foundCinema = cinemaService.findById(cinemaId);
            foundCinema.setName(cinemaCreateRequest.getName());
            foundCinema.setAddress(cinemaCreateRequest.getAddress());
            foundCinema.setPhoneNumber(cinemaCreateRequest.getPhoneNumber());
            foundCinema.setPaymentMethod(cinemaCreateRequest.getPaymentMethod());
            foundCinema.setChanged(new Timestamp(System.currentTimeMillis()));
            return cinemaService.update(foundCinema);
        } catch (ServiceException e) {
            log.error("Can't find a cinema.");
            throw new ControllerException("Can't find a cinema." + e.getMessage());
        }
    }

    @DeleteMapping("/{cinemaId}")
    @ResponseStatus(HttpStatus.OK)
    public Cinema deleteCinema(@PathVariable Long cinemaId) throws ControllerException {
        try {
            return cinemaService.delete(cinemaId);
        } catch (ServiceException e) {
            log.error("Can't find a cinema.");
            throw new ControllerException("Can't find a cinema." + e.getMessage());
        }
    }
}

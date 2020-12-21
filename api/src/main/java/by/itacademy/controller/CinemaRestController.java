package by.itacademy.controller;

import by.itacademy.controller.request.CinemaCreateRequest;
import by.itacademy.exception.ControllerException;
import by.itacademy.exception.RepositoryException;
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
@RequestMapping("/rest/cinemas")
@RequiredArgsConstructor
public class CinemaRestController {

    private final CinemaRepository cinemaRepository;

    @GetMapping
    public ResponseEntity<List<Cinema>> findAllCinemas() throws ControllerException {
        try {
            log.info("Cinemas exist");
            return new ResponseEntity<>(cinemaRepository.findAll(), HttpStatus.OK);
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't find not existing cinemas");
        }
    }

    @GetMapping("/{cinemaId}")
    @ResponseStatus(HttpStatus.OK)
    public Cinema findCinemaById(@PathVariable Long cinemaId) throws ControllerException {
        try {
            Cinema cinemaToFindById = cinemaRepository.findById(cinemaId);
            log.info("Cinema with id " + cinemaId + " exists");
            return cinemaToFindById;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't find a not existing cinema");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cinema saveCinema(@RequestBody CinemaCreateRequest cinemaCreateRequest) throws ControllerException {
        try {
            Cinema cinemaToSave = new Cinema();
            cinemaToSave.setName(cinemaCreateRequest.getName());
            cinemaToSave.setAddress(cinemaCreateRequest.getAddress());
            cinemaToSave.setPhoneNumber(cinemaCreateRequest.getPhoneNumber());
            cinemaToSave.setPaymentMethod(cinemaCreateRequest.getPaymentMethod());
            cinemaToSave.setCreated(new Timestamp(System.currentTimeMillis()));
            cinemaToSave.setChanged(new Timestamp(System.currentTimeMillis()));
            cinemaToSave.setLocationId(cinemaCreateRequest.getLocationId());
            cinemaToSave.setMovieId(cinemaCreateRequest.getMovieId());

            log.info("Cinema " + cinemaToSave + " saved");
            return cinemaRepository.save(cinemaToSave);
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't save a not existing cinema");
        }
    }

    @PutMapping("/{cinemaId}")
    @ResponseStatus(HttpStatus.OK)
    public Cinema updateCinema(@PathVariable Long cinemaId,
                               @RequestBody CinemaCreateRequest cinemaCreateRequest) throws RepositoryException, ControllerException {

        Cinema cinemaToUpdate;

        try {
            cinemaToUpdate = cinemaRepository.findById(cinemaId);
            log.info("Cinema with id " + cinemaId + " updated");
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't update a not existing cinema");
        }

        cinemaToUpdate.setName(cinemaCreateRequest.getName());
        cinemaToUpdate.setAddress(cinemaCreateRequest.getAddress());
        cinemaToUpdate.setPhoneNumber(cinemaCreateRequest.getPhoneNumber());
        cinemaToUpdate.setPaymentMethod(cinemaCreateRequest.getPaymentMethod());
        cinemaToUpdate.setChanged(new Timestamp(System.currentTimeMillis()));
        cinemaToUpdate.setLocationId(cinemaCreateRequest.getLocationId());
        cinemaToUpdate.setMovieId(cinemaCreateRequest.getMovieId());
        return cinemaRepository.update(cinemaToUpdate);
    }

    @DeleteMapping("/{cinemaId}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteCinema(@PathVariable Long cinemaId) throws ControllerException {
        try {
            log.info("Cinema with id " + cinemaId + " deleted");
            return cinemaRepository.delete(cinemaId);
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't delete a not existing cinema");
        }
    }
}

package by.itacademy.controller;

import by.itacademy.controller.request.LocationCreateRequest;
import by.itacademy.exception.ControllerException;
import by.itacademy.exception.RepositoryException;
import by.itacademy.repository.LocationRepository;
import by.itacademy.domain.Location;
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
@RequestMapping("/rest/locations")
@RequiredArgsConstructor
public class LocationRestController {

    private final LocationRepository locationRepository;

    @GetMapping
    public ResponseEntity<List<Location>> findAllLocations() throws ControllerException {
        try {
            log.info("Locations exist");
            return new ResponseEntity<>(locationRepository.findAll(), HttpStatus.OK);
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't find not existing locations");
        }
    }

    @GetMapping("/{locationId}")
    @ResponseStatus(HttpStatus.OK)
    public Location findLocationById(@PathVariable Long locationId) throws ControllerException {
        try {
            Location locationToFindById = locationRepository.findById(locationId);
            log.info("Location with id " + locationId + " exists");
            return locationToFindById;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't find a not existing location");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location saveLocation(@RequestBody LocationCreateRequest locationCreateRequest) throws ControllerException {
        try {
            Location locationToSave = new Location();
            locationToSave.setLocation(locationCreateRequest.getLocation());
            locationToSave.setCreated(new Timestamp(System.currentTimeMillis()));
            locationToSave.setChanged(new Timestamp(System.currentTimeMillis()));

            log.info("Location " + locationToSave + " was saved");
            return locationRepository.save(locationToSave);
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't save a not existing location");
        }
    }

    @PutMapping("/{locationId}")
    @ResponseStatus(HttpStatus.OK)
    public Location updateLocation(@PathVariable Long locationId,
                                   @RequestBody LocationCreateRequest locationCreateRequest) throws RepositoryException, ControllerException {

        Location locationToUpdate;

        try {
            locationToUpdate = locationRepository.findById(locationId);
            log.info("Location with id " + locationId + " was updated");
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't update a not existing location");
        }

        locationToUpdate.setLocation(locationCreateRequest.getLocation());
        locationToUpdate.setChanged(new Timestamp(System.currentTimeMillis()));
        return locationRepository.update(locationToUpdate);
    }

    @DeleteMapping("/{locationId}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteLocation(@PathVariable Location locationId) throws ControllerException {
        try {
            log.info("User with id " + locationId + " was deleted");
            return locationRepository.delete(locationId);
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't delete a not existing user");
        }
    }
}

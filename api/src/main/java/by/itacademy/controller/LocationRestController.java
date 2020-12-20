package by.itacademy.controller;

import by.itacademy.controller.request.LocationCreateRequest;
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
@RequestMapping("/rest/locations/hibernate")
@RequiredArgsConstructor
public class LocationRestController {

    private final LocationRepository locationRepository;

    @GetMapping
    public ResponseEntity<List<Location>> findAllLocations() {
        return new ResponseEntity<>(locationRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Location findLocationById(@PathVariable Long id) {
        try {
            Location location = locationRepository.findById(id);
            log.info("Ok");
            return location;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location saveLocation(@RequestBody LocationCreateRequest locationCreateRequest) {
        Location location = new Location();
        location.setLocation(locationCreateRequest.getLocation());
        location.setCreated(new Timestamp(System.currentTimeMillis()));
        location.setChanged(new Timestamp(System.currentTimeMillis()));

        return locationRepository.save(location);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Location updateLocation(@PathVariable Long id,
                                   @RequestBody LocationCreateRequest locationCreateRequest) {

        Location location;
        try {
            location = locationRepository.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

        location.setLocation(locationCreateRequest.getLocation());
        location.setChanged(new Timestamp(System.currentTimeMillis()));
        return locationRepository.update(location);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteLocation(@PathVariable Long id) {
        return locationRepository.delete(id);
    }
}

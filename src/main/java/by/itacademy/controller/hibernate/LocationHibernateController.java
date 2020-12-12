package by.itacademy.controller.hibernate;

import by.itacademy.controller.request.LocationCreateRequest;
import by.itacademy.dao.hibernate.LocationHibernateRepository;
import by.itacademy.domain.hibernate.LocationHibernate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/rest/locations/hibernate")
@RequiredArgsConstructor
public class LocationHibernateController {

    private final LocationHibernateRepository locationHibernateRepository;

    @GetMapping
    public ResponseEntity<List<LocationHibernate>> findAllLocations() {
        return new ResponseEntity<>(locationHibernateRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LocationHibernate findLocationById(@PathVariable Long id) {
        return locationHibernateRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocationHibernate saveLocation(@RequestBody LocationCreateRequest locationCreateRequest) {
        LocationHibernate location = new LocationHibernate();
        location.setLocation(locationCreateRequest.getLocation());
        location.setCreated(new Timestamp(System.currentTimeMillis()));
        location.setChanged(new Timestamp(System.currentTimeMillis()));

        return locationHibernateRepository.save(location);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LocationHibernate updateLocation(@PathVariable Long id,
                                            @RequestBody LocationCreateRequest locationCreateRequest) {

        LocationHibernate location = locationHibernateRepository.findById(id);

        location.setLocation(locationCreateRequest.getLocation());
        location.setChanged(new Timestamp(System.currentTimeMillis()));
        return locationHibernateRepository.update(location);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteLocation(@PathVariable Long id) {
        return locationHibernateRepository.delete(id);
    }
}

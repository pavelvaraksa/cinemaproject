package by.itacademy.controller.hibernate;

import by.itacademy.controller.request.LocationCreateRequest;
import by.itacademy.dao.hibernate.LocationAppRepository;
import by.itacademy.domain.hibernate.LocationApp;
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
@RequestMapping("/rest/locations/hibernate")
@RequiredArgsConstructor
public class LocationAppController {

    private final LocationAppRepository locationAppRepository;

    @GetMapping
    public ResponseEntity<List<LocationApp>> findAllLocations() {
        return new ResponseEntity<>(locationAppRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LocationApp findLocationById(@PathVariable Long id) {
        return locationAppRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocationApp saveLocation(@RequestBody LocationCreateRequest locationCreateRequest) {
        LocationApp location = new LocationApp();
        location.setLocation(locationCreateRequest.getLocation());
        location.setCreated(new Timestamp(System.currentTimeMillis()));
        location.setChanged(new Timestamp(System.currentTimeMillis()));

        return locationAppRepository.save(location);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LocationApp updateLocation(@PathVariable Long id,
                                      @RequestBody LocationCreateRequest locationCreateRequest) {

        LocationApp location = locationAppRepository.findById(id);

        location.setLocation(locationCreateRequest.getLocation());
        location.setChanged(new Timestamp(System.currentTimeMillis()));
        return locationAppRepository.update(location);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteLocation(@PathVariable Long id) {
        return locationAppRepository.delete(id);
    }
}

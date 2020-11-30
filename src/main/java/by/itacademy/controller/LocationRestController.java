package by.itacademy.controller;

import by.itacademy.controller.request.LocationCreateRequest;
import by.itacademy.domain.Location;
import by.itacademy.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/locations")
@RequiredArgsConstructor
public class LocationRestController {

    private final LocationService locationService;

    @GetMapping
    public ResponseEntity<List<Location>> findAllLocations() {
        return new ResponseEntity<>(locationService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Location findLocationById(@PathVariable Long id) {
        return locationService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location saveLocation(@RequestBody LocationCreateRequest locationCreateRequest) {
        Location location = new Location();
        location.setLocation(locationCreateRequest.getLocation());
        return locationService.save(location);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Location updateLocation(@PathVariable Long id,
                                   @RequestBody LocationCreateRequest locationCreateRequest) {

        Location location = locationService.findById(id);

        location.setLocation(locationCreateRequest.getLocation());
        return locationService.update(location);
    }

    @DeleteMapping("/{location}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteLocation(@PathVariable Location location) {
        return locationService.delete(location);
    }
}

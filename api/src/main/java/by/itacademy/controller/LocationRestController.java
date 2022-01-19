package by.itacademy.controller;

import by.itacademy.controller.request.LocationCreateRequest;
import by.itacademy.exception.ControllerException;
import by.itacademy.exception.ServiceException;
import by.itacademy.domain.Location;
import by.itacademy.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
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
@RequestMapping("/rest/locations")
@RequiredArgsConstructor
public class LocationRestController {

    private final LocationService locationService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Location> findAllLocations() throws ControllerException {

        try {
            return locationService.findAll();
        } catch (ServiceException e) {
            log.error("Can't find any locations.");
            throw new ControllerException("Can't find any locations." + e.getMessage());
        }
    }

    @GetMapping("/{locationId}")
    @ResponseStatus(HttpStatus.OK)
    public Location findLocationById(@PathVariable Long locationId) throws ControllerException {

        try {
            return locationService.findById(locationId);
        } catch (ServiceException e) {
            log.error("Can't find a location.");
            throw new ControllerException("Can't find a location." + e.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location saveLocation(@RequestBody LocationCreateRequest locationCreateRequest) throws ControllerException {

        String location = locationCreateRequest.getLocation();

        if (location == null || location.isEmpty()) {
            throw new ControllerException("Location can't be null or empty.");
        }

        Location locationToSave = new Location();
        locationToSave.setLocation(locationCreateRequest.getLocation());
        locationToSave.setCreated(new Timestamp(System.currentTimeMillis()));
        locationToSave.setChanged(new Timestamp(System.currentTimeMillis()));
        locationToSave.setEventId(locationCreateRequest.getEventId());

        try {
            return locationService.save(locationToSave);
        } catch (ServiceException e) {
            log.error("Can't save a location.");
            throw new ControllerException("Can't save a location." + e.getMessage());
        }
    }

    @PutMapping("/{locationId}")
    @ResponseStatus(HttpStatus.OK)
    public Location updateLocation(@PathVariable Long locationId,
                                   @RequestBody LocationCreateRequest locationCreateRequest) throws ControllerException {

        String location = locationCreateRequest.getLocation();

        if (location == null || location.isEmpty()) {
            throw new ControllerException("Location can't be null or empty.");
        }

        try {
            Location foundLocation = locationService.findById(locationId);
            foundLocation.setLocation(locationCreateRequest.getLocation());
            foundLocation.setChanged(new Timestamp(System.currentTimeMillis()));
            return locationService.update(foundLocation);
        } catch (ServiceException e) {
            log.error("Can't find a location.");
            throw new ControllerException("Can't find a location." + e.getMessage());
        }
    }

    @DeleteMapping("/{locationId}")
    @ResponseStatus(HttpStatus.OK)
    public Location deleteLocation(@PathVariable Long locationId) throws ControllerException {

        try {
            return locationService.delete(locationId);
        } catch (ServiceException e) {
            log.error("Can't find a location.");
            throw new ControllerException("Can't find a location." + e.getMessage());
        }
    }
}

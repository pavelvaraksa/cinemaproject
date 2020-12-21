package by.itacademy.service.impl;

import by.itacademy.domain.Location;
import by.itacademy.repository.LocationRepository;
import by.itacademy.service.LocationService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> findAll() {
        try {
            List<Location> locationsToFind = locationRepository.findAll();
            log.info("Locations " + locationsToFind + " are exist");
            return locationsToFind;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Location findById(Long locationId) {
        try {
            Location locationToFindById = locationRepository.findById(locationId);
            log.info("Location " + locationId + " is exist");
            return locationToFindById;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Location save(Location location) {
        try {
            Location locationToSave = locationRepository.save(location);
            log.info("Location " + location + " saved");
            return locationToSave;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Location update(Location location) {
        try {
            Location locationToUpdate = locationRepository.update(location);
            log.info("Location " + location + " updated");
            return locationToUpdate;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Long delete(Long locationId) {
        try {
            Long locationToDelete = locationRepository.delete(locationId);
            log.info("Location " + locationId + " deleted");
            return locationToDelete;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
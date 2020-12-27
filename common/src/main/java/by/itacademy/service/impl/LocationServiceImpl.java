package by.itacademy.service.impl;

import by.itacademy.domain.Location;
import by.itacademy.domain.User;
import by.itacademy.exception.RepositoryException;
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
            log.info("Locations " + locationsToFind + " exist");
            return locationsToFind;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Location findById(Long locationId) {
        try {
            Location locationToFindById = locationRepository.findById(locationId);
            log.info("Location with id " + locationId + " exists");
            return locationToFindById;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Location save(Location location) {
        try {
            Location locationToSave = locationRepository.save(location);
            log.info("Location " + location + " was saved");
            return locationToSave;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Location update(Location location) {
        try {
            Location locationToUpdate = locationRepository.update(location);
            log.info("Location " + location + " was updated");
            return locationToUpdate;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Location delete(Long locationId) {
        try {
            Location locationToDelete = locationRepository.delete(locationId);
            log.info("Location with id " + locationId + " was deleted");
            return locationToDelete;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
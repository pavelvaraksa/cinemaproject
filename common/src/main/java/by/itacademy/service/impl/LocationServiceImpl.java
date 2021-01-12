package by.itacademy.service.impl;

import by.itacademy.domain.Location;
import by.itacademy.exception.RepositoryException;
import by.itacademy.exception.ServiceException;
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
    public List<Location> findAll() throws ServiceException {

        List<Location> existingLocations;

        try {
            existingLocations = locationRepository.findAll();
            if (existingLocations.isEmpty()) {
                String errorMessage = "The list is empty.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            } else {
                log.info("Locations exist.");
                return existingLocations;
            }
        } catch (RepositoryException e) {
            throw new ServiceException("Location service exception while trying to find all locations." + e.getMessage());
        }
    }

    @Override
    public Location findById(Long locationId) throws ServiceException {

        Location locationToFindById;

        try {
            locationToFindById = locationRepository.findById(locationId);
            if (locationToFindById == null) {
                String errorMessage = "Location id can't be null.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException e) {
            throw new ServiceException("Location service exception while trying to find a location." + e.getMessage());
        }

        try {
            log.info("Location with id " + locationId + " exists.");
            return locationRepository.findById(locationId);
        } catch (RepositoryException e) {
            String errorMessage = "Can't find a location.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Location save(Location location) throws ServiceException {

        List<Location> existingLocations;

        try {
            existingLocations = locationRepository.findAll();
        } catch (RepositoryException e) {
            String errorMessage = "Can't get all locations.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }

        for (Location existingLocation : existingLocations) {
            boolean hasSameLocation = existingLocation.getLocation().equals(location.getLocation());

            if (hasSameLocation) {
                String errorMessage = "Location " + location.getLocation() + " already exists.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        }

        try {
            Location savedLocation = locationRepository.save(location);
            log.info("Location " + location + " was saved.");
            return savedLocation;
        } catch (RepositoryException e) {
            throw new ServiceException("Location service exception while trying to save a location:" + e.getMessage());
        }
    }

    @Override
    public Location update(Location location) throws ServiceException {

        try {
            log.info("Location " + location + " was updated.");
            return locationRepository.update(location);
        } catch (RepositoryException e) {
            String errorMessage = "Can't get a location.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Location delete(Long locationId) throws ServiceException {

        Location locationToFindById;

        try {
            locationToFindById = locationRepository.findById(locationId);
            if (locationToFindById == null) {
                String errorMessage = "Location id can't be null.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException e) {
            throw new ServiceException("Location service exception while trying to delete a location." + e.getMessage());
        }

        try {
            log.info("Location with id " + locationId + " was deleted.");
            return locationRepository.delete(locationId);
        } catch (RepositoryException e) {
            String errorMessage = "Can't find a location.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
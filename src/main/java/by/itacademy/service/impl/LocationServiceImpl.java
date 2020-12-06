package by.itacademy.service.impl;

import by.itacademy.domain.Location;
import by.itacademy.dao.jdbctemplate.LocationRepository;
import by.itacademy.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location findById(Long locationId) {
        return locationRepository.findById(locationId);
    }

    @Override
    public Location save(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location update(Location location) {
        return locationRepository.update(location);
    }

    @Override
    public Long delete(Long location) {
        return locationRepository.delete(location);
    }
}

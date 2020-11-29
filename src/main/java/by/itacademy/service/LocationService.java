package by.itacademy.service;

import by.itacademy.domain.Location;

import java.util.List;

public interface LocationService {

    List<Location> findAll();

    Location findById(Long locationId);

    Location save(Location location);

    Location update(Location location);

    Long delete(Location location);
}

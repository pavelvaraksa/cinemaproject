package by.itacademy.service;

import by.itacademy.domain.Location;
import by.itacademy.exception.ServiceException;

import java.util.List;

public interface LocationService {

    List<Location> findAll() throws ServiceException;

    Location findById(Long locationId) throws ServiceException;

    Location save(Location location) throws ServiceException;

    Location update(Location location) throws ServiceException;

    Location delete(Long locationId) throws ServiceException;
}

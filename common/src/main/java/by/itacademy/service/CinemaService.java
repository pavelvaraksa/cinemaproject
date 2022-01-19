package by.itacademy.service;

import by.itacademy.domain.Cinema;
import by.itacademy.exception.ServiceException;

import java.util.List;

public interface CinemaService {

    List<Cinema> findAll() throws ServiceException;

    Cinema findById(Long cinemaId) throws ServiceException;

    Cinema save(Cinema cinema) throws ServiceException;

    Cinema update(Cinema cinema) throws ServiceException;

    Cinema delete(Long cinemaId) throws ServiceException;
}

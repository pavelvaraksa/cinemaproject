package by.itacademy.service;

import by.itacademy.domain.Cinema;
import by.itacademy.exception.RepositoryException;

import java.util.List;

public interface CinemaService {

    List<Cinema> findAll() throws RepositoryException;

    Cinema findById(Long cinemaId);

    Cinema save(Cinema cinema);

    Cinema update(Cinema cinema);

    Long delete(Cinema cinemaId);
}

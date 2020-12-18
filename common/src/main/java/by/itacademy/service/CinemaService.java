package by.itacademy.service;

import by.itacademy.domain.Cinema;

import java.util.List;

public interface CinemaService {

    List<Cinema> findAll();

    Cinema findById(Long cinemaId);

    Cinema save(Cinema cinema);

    Cinema update(Cinema cinema);

    Long delete(Long cinema);
}

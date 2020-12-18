package by.itacademy.service.impl;

import by.itacademy.domain.Cinema;
import by.itacademy.dao.jdbctemplate.CinemaRepository;
import by.itacademy.service.CinemaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;

    public CinemaServiceImpl(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }

    @Override
    public Cinema findById(Long cinemaId) {
        return cinemaRepository.findById(cinemaId);
    }

    @Override
    public Cinema save(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    @Override
    public Cinema update(Cinema cinema) {
        return cinemaRepository.update(cinema);
    }

    @Override
    public Long delete(Long cinema) {
        return cinemaRepository.delete(cinema);
    }
}

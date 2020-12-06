package by.itacademy.service.impl;

import by.itacademy.domain.Movie;
import by.itacademy.dao.jdbctemplate.MovieRepository;
import by.itacademy.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long movieId) {
        return movieRepository.findById(movieId);
    }

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie update(Movie movie) {
        return movieRepository.update(movie);
    }

    @Override
    public Long delete(Long movie) {
        return movieRepository.delete(movie);
    }
}

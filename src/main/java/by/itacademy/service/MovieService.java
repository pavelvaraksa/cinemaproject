package by.itacademy.service;

import by.itacademy.domain.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> findAll();

    Movie findById(Long movieId);

    Movie save(Movie movie);

    Movie update(Movie movie);

    Long delete(Movie movie);
}

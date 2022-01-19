package by.itacademy.service;

import by.itacademy.domain.Movie;
import by.itacademy.exception.ServiceException;

import java.util.List;

public interface MovieService {

    List<Movie> findAll() throws ServiceException;

    Movie findById(Long movieId) throws ServiceException;

    Movie save(Movie movie) throws ServiceException;

    Movie update(Movie movie) throws ServiceException;

    Movie delete(Long movieId) throws ServiceException;
}

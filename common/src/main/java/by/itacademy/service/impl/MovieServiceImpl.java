package by.itacademy.service.impl;

import by.itacademy.domain.Movie;
import by.itacademy.exception.RepositoryException;
import by.itacademy.exception.ServiceException;
import by.itacademy.repository.MovieRepository;
import by.itacademy.service.MovieService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> findAll() throws ServiceException {

        List<Movie> existingMovies;

        try {
            existingMovies = movieRepository.findAll();
            if (existingMovies.isEmpty()) {
                String errorMessage = "The list is empty.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            } else {
                log.info("Movies exist.");
                return existingMovies;
            }
        } catch (RepositoryException e) {
            throw new ServiceException("Movie service exception while trying to find all movies." + e.getMessage());
        }
    }

    @Override
    public Movie findById(Long movieId) throws ServiceException {

        Movie movieToFindById;

        try {
            movieToFindById = movieRepository.findById(movieId);
            if (movieToFindById == null) {
                String errorMessage = "Movie id can't be null.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException e) {
            throw new ServiceException("Movie service exception while trying to find a movie." + e.getMessage());
        }

        try {
            log.info("Movie with id " + movieId + " exists.");
            return movieRepository.findById(movieId);
        } catch (RepositoryException e) {
            String errorMessage = "Can't find a movie.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Movie save(Movie movie) throws ServiceException {

        List<Movie> existingMovies;

        try {
            existingMovies = movieRepository.findAll();
        } catch (RepositoryException e) {
            String errorMessage = "Can't get all movies.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }

        for (Movie existingMovie : existingMovies) {
            boolean hasSameMovie = existingMovie.getTitle().equals(movie.getTitle());

            if (hasSameMovie) {
                String errorMessage = "Movie with title " + movie.getTitle() + " already exists.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        }

        try {
            Movie savedMovie = movieRepository.save(movie);
            log.info("Movie " + movie + " was saved");
            return savedMovie;
        } catch (RepositoryException e) {
            throw new ServiceException("Movie service exception while trying to save a movie:" + e.getMessage());
        }
    }

    @Override
    public Movie update(Movie movie) throws ServiceException {

        try {
            log.info("Movie " + movie + " was updated.");
            return movieRepository.update(movie);
        } catch (RepositoryException e) {
            String errorMessage = "Can't get a movie.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Movie delete(Long movieId) throws ServiceException {

        Movie movieToFindById;

        try {
            movieToFindById = movieRepository.findById(movieId);
            if (movieToFindById == null) {
                String errorMessage = "Movie id can't be null.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException e) {
            throw new ServiceException("Movie service exception while trying to delete a movie." + e.getMessage());
        }

        try {
            log.info("Movie with id " + movieId + " was deleted.");
            return movieRepository.delete(movieId);
        } catch (RepositoryException e) {
            String errorMessage = "Can't find a movie.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
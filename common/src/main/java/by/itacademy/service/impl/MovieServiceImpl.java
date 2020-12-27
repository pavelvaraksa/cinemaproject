package by.itacademy.service.impl;

import by.itacademy.domain.Movie;
import by.itacademy.domain.Movie;
import by.itacademy.exception.RepositoryException;
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
    public List<Movie> findAll() {
        try {
            List<Movie> moviesToFind = movieRepository.findAll();
            log.info("Movies " + moviesToFind + " exist");
            return moviesToFind;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Movie findById(Long movieId) {
        try {
            Movie movieToFindById = movieRepository.findById(movieId);
            log.info("Movie with id " + movieId + " exists");
            return movieToFindById;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Movie save(Movie movie) {
        try {
            Movie movieToSave = movieRepository.save(movie);
            log.info("Movie " + movie + " was saved");
            return movieToSave;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Movie update(Movie movie) {
        try {
            Movie movieToUpdate = movieRepository.update(movie);
            log.info("Movie " + movie + " was updated");
            return movieToUpdate;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Movie delete(Long movieId) {
        try {
            Movie movieToDelete = movieRepository.delete(movieId);
            log.info("Movie with id " + movieId + " was deleted");
            return movieToDelete;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
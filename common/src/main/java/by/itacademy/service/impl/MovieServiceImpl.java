package by.itacademy.service.impl;

import by.itacademy.domain.Movie;
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
            log.info("Movies " + moviesToFind + " are exist");
            return moviesToFind;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Movie findById(Long movieId) {
        try {
            Movie movieToFindById = movieRepository.findById(movieId);
            log.info("Movie " + movieId + " is exist");
            return movieToFindById;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Movie save(Movie movie) {
        try {
            Movie movieToSave = movieRepository.save(movie);
            log.info("Movie " + movie + " saved");
            return movieToSave;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Movie update(Movie movie) {
        try {
            Movie movieToUpdate = movieRepository.update(movie);
            log.info("Movie " + movie + " updated");
            return movieToUpdate;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Long delete(Long movieId) {
        try {
            Long movieToDelete = movieRepository.delete(movieId);
            log.info("Movie " + movieId + " deleted");
            return movieToDelete;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}

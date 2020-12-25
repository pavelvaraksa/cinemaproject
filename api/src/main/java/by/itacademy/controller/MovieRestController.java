package by.itacademy.controller;

import by.itacademy.controller.request.MovieCreateRequest;
import by.itacademy.exception.ControllerException;
import by.itacademy.exception.RepositoryException;
import by.itacademy.repository.MovieRepository;
import by.itacademy.domain.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.sql.Timestamp;
import java.util.List;

@Log4j
@RestController
@RequestMapping("/rest/movies")
@RequiredArgsConstructor
public class MovieRestController {

    private final MovieRepository movieRepository;

    @GetMapping
    public ResponseEntity<List<Movie>> findAllMovies() throws ControllerException {
        try {
            log.info("Movies exist");
            return new ResponseEntity<>(movieRepository.findAll(), HttpStatus.OK);
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't find not existing movies");
        }
    }

    @GetMapping("/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public Movie findMovieById(@PathVariable Long movieId) throws ControllerException {
        try {
            Movie movieToFindById = movieRepository.findById(movieId);
            log.info("Movie with id " + movieId + " exists");
            return movieToFindById;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't find a not existing movie");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie saveMovie(@RequestBody MovieCreateRequest movieCreateRequest) throws ControllerException {
        try {
            Movie movieToSave = new Movie();
            movieToSave.setTitle(movieCreateRequest.getTitle());
            movieToSave.setGenre(movieCreateRequest.getGenre());
            movieToSave.setYear(movieCreateRequest.getYear());
            movieToSave.setDuration(movieCreateRequest.getDuration());
            movieToSave.setCreated(new Timestamp(System.currentTimeMillis()));
            movieToSave.setChanged(new Timestamp(System.currentTimeMillis()));

            log.info("Movie " + movieToSave + " was saved");
            return movieRepository.save(movieToSave);
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't save a not existing movie");
        }
    }

    @PutMapping("/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public Movie updateMovie(@PathVariable Long movieId,
                             @RequestBody MovieCreateRequest movieCreateRequest) throws RepositoryException, ControllerException {

        Movie movieToUpdate;

        try {
            movieToUpdate = movieRepository.findById(movieId);
            log.info("Movie with id " + movieId + " was updated");
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't update a not existing movie");
        }

        movieToUpdate.setTitle(movieCreateRequest.getTitle());
        movieToUpdate.setGenre(movieCreateRequest.getGenre());
        movieToUpdate.setYear(movieCreateRequest.getYear());
        movieToUpdate.setDuration(movieCreateRequest.getDuration());
        movieToUpdate.setChanged(new Timestamp(System.currentTimeMillis()));
        return movieRepository.update(movieToUpdate);
    }

    @DeleteMapping("/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteMovie(@PathVariable Movie movieId) throws ControllerException {
        try {
            log.info("User with id " + movieId + " was deleted");
            return movieRepository.delete(movieId);
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't delete a not existing user");
        }
    }
}

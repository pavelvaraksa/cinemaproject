package by.itacademy.controller;

import by.itacademy.controller.request.MovieCreateRequest;
import by.itacademy.exception.ControllerException;
import by.itacademy.exception.ServiceException;
import by.itacademy.domain.Movie;
import by.itacademy.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
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

    private final MovieService movieService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> findAllMovies() throws ControllerException {

        try {
            return movieService.findAll();
        } catch (ServiceException e) {
            log.error("Can't find any movies.");
            throw new ControllerException("Can't find any movies." + e.getMessage());
        }
    }

    @GetMapping("/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public Movie findMovieById(@PathVariable Long movieId) throws ControllerException {

        try {
            return movieService.findById(movieId);
        } catch (ServiceException e) {
            log.error("Can't find a movie.");
            throw new ControllerException("Can't find a movie." + e.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie saveMovie(@RequestBody MovieCreateRequest movieCreateRequest) throws ControllerException {

        String title = movieCreateRequest.getTitle();
        String genre = movieCreateRequest.getGenre();
        int year = movieCreateRequest.getYear();
        int duration = movieCreateRequest.getDuration();

        if (title == null || title.isEmpty() || genre == null || genre.isEmpty()) {
            throw new ControllerException("Movie title or genre can't be null or empty.");
        }

        if (year == 0 || duration == 0) {
            throw new ControllerException("Movie year or duration can't be null.");
        }

        Movie movieToSave = new Movie();
        movieToSave.setTitle(movieCreateRequest.getTitle());
        movieToSave.setGenre(movieCreateRequest.getGenre());
        movieToSave.setYear(movieCreateRequest.getYear());
        movieToSave.setDuration(movieCreateRequest.getDuration());
        movieToSave.setCreated(new Timestamp(System.currentTimeMillis()));
        movieToSave.setChanged(new Timestamp(System.currentTimeMillis()));
        movieToSave.setCinemaId(movieCreateRequest.getCinemaId());

        try {
            return movieService.save(movieToSave);
        } catch (ServiceException e) {
            log.error("Can't save a movie.");
            throw new ControllerException("Can't save a movie." + e.getMessage());
        }
    }

    @PutMapping("/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public Movie updateMovie(@PathVariable Long movieId,
                             @RequestBody MovieCreateRequest movieCreateRequest) throws ControllerException {

        String title = movieCreateRequest.getTitle();
        String genre = movieCreateRequest.getGenre();
        int year = movieCreateRequest.getYear();
        int duration = movieCreateRequest.getDuration();

        if (title == null || title.isEmpty() || genre == null || genre.isEmpty()) {
            throw new ControllerException("Movie title or genre can't be null or empty.");
        }

        if (year == 0 || duration == 0) {
            throw new ControllerException("Movie year or duration can't be null.");
        }

        try {
            Movie foundMovie = movieService.findById(movieId);
            foundMovie.setTitle(movieCreateRequest.getTitle());
            foundMovie.setGenre(movieCreateRequest.getGenre());
            foundMovie.setYear(movieCreateRequest.getYear());
            foundMovie.setDuration(movieCreateRequest.getDuration());
            foundMovie.setChanged(new Timestamp(System.currentTimeMillis()));
            return movieService.update(foundMovie);
        } catch (ServiceException e) {
            log.error("Can't find a movie.");
            throw new ControllerException("Can't find a movie." + e.getMessage());
        }
    }

    @DeleteMapping("/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public Movie deleteMovie(@PathVariable Long movieId) throws ControllerException {

        try {
            return movieService.delete(movieId);
        } catch (ServiceException e) {
            log.error("Can't find a movie.");
            throw new ControllerException("Can't find a movie." + e.getMessage());
        }
    }
}

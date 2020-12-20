package by.itacademy.controller;

import by.itacademy.controller.request.MovieCreateRequest;
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
@RequestMapping("/rest/movies/hibernate")
@RequiredArgsConstructor
public class MovieRestController {

    private final MovieRepository movieRepository;

    @GetMapping
    public ResponseEntity<List<Movie>> findAllMovies() {
        return new ResponseEntity<>(movieRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Movie findMovieById(@PathVariable Long id) {
        try {
            Movie testUser = movieRepository.findById(id);
            log.info("Ok");
            return testUser;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie saveMovie(@RequestBody MovieCreateRequest movieCreateRequest) {
        Movie movie = new Movie();
        movie.setTitle(movieCreateRequest.getTitle());
        movie.setGenre(movieCreateRequest.getGenre());
        movie.setYear(movieCreateRequest.getYear());
        movie.setDuration(movieCreateRequest.getDuration());
        movie.setCreated(new Timestamp(System.currentTimeMillis()));
        movie.setChanged(new Timestamp(System.currentTimeMillis()));

        return movieRepository.save(movie);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Movie updateMovie(@PathVariable Long id,
                             @RequestBody MovieCreateRequest movieCreateRequest) {

        Movie movie;
        try {
            movie = movieRepository.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

        movie.setTitle(movieCreateRequest.getTitle());
        movie.setGenre(movieCreateRequest.getGenre());
        movie.setYear(movieCreateRequest.getYear());
        movie.setDuration(movieCreateRequest.getDuration());
        movie.setChanged(new Timestamp(System.currentTimeMillis()));
        return movieRepository.update(movie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteMovie(@PathVariable Long id) {
        return movieRepository.delete(id);
    }
}

package by.itacademy.controller;

import by.itacademy.controller.request.MovieCreateRequest;
import by.itacademy.domain.Movie;
import by.itacademy.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/rest/movies")
@RequiredArgsConstructor
public class MovieRestController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> findAllMovies() {
        return new ResponseEntity<>(movieService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Movie findMovieById(@PathVariable Long id) {
        return movieService.findById(id);
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
        return movieService.save(movie);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Movie updateMovie(@PathVariable Long id,
                             @RequestBody MovieCreateRequest movieCreateRequest) {

        Movie movie = movieService.findById(id);

        movie.setTitle(movieCreateRequest.getTitle());
        movie.setGenre(movieCreateRequest.getGenre());
        movie.setYear(movieCreateRequest.getYear());
        movie.setDuration(movieCreateRequest.getDuration());
        movie.setChanged(new Timestamp(System.currentTimeMillis()));
        return movieService.update(movie);
    }

    @DeleteMapping("/{movie}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteMovie(@PathVariable Long movie) {
        return movieService.delete(movie);
    }
}

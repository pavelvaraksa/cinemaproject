package by.itacademy.controller.hibernate;

import by.itacademy.controller.request.MovieCreateRequest;
import by.itacademy.dao.hibernate.MovieAppRepository;
import by.itacademy.domain.hibernate.MovieApp;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequestMapping("/rest/movies/hibernate")
@RequiredArgsConstructor
public class MovieAppController {

    private final MovieAppRepository movieAppRepository;

    @GetMapping
    public ResponseEntity<List<MovieApp>> findAllMovies() {
        return new ResponseEntity<>(movieAppRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieApp findMovieById(@PathVariable Long id) {
        return movieAppRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieApp saveMovie(@RequestBody MovieCreateRequest movieCreateRequest) {
        MovieApp movie = new MovieApp();
        movie.setTitle(movieCreateRequest.getTitle());
        movie.setGenre(movieCreateRequest.getGenre());
        movie.setYear(movieCreateRequest.getYear());
        movie.setDuration(movieCreateRequest.getDuration());
        movie.setCreated(new Timestamp(System.currentTimeMillis()));
        movie.setChanged(new Timestamp(System.currentTimeMillis()));

        return movieAppRepository.save(movie);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieApp updateMovie(@PathVariable Long id,
                                @RequestBody MovieCreateRequest movieCreateRequest) {

        MovieApp movie = movieAppRepository.findById(id);

        movie.setTitle(movieCreateRequest.getTitle());
        movie.setGenre(movieCreateRequest.getGenre());
        movie.setYear(movieCreateRequest.getYear());
        movie.setDuration(movieCreateRequest.getDuration());
        movie.setChanged(new Timestamp(System.currentTimeMillis()));
        return movieAppRepository.update(movie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteMovie(@PathVariable Long id) {
        return movieAppRepository.delete(id);
    }
}

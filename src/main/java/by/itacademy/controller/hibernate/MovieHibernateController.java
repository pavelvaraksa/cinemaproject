package by.itacademy.controller.hibernate;

import by.itacademy.controller.request.MovieCreateRequest;
import by.itacademy.dao.hibernate.MovieHibernateRepository;
import by.itacademy.domain.hibernate.MovieHibernate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/rest/movies/hibernate")
@RequiredArgsConstructor
public class MovieHibernateController {

    private final MovieHibernateRepository movieHibernateRepository;

    @GetMapping
    public ResponseEntity<List<MovieHibernate>> findAllMovies() {
        return new ResponseEntity<>(movieHibernateRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieHibernate findMovieById(@PathVariable Long id) {
        return movieHibernateRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieHibernate saveMovie(@RequestBody MovieCreateRequest movieCreateRequest) {
        MovieHibernate movie = new MovieHibernate();
        movie.setTitle(movieCreateRequest.getTitle());
        movie.setGenre(movieCreateRequest.getGenre());
        movie.setYear(movieCreateRequest.getYear());
        movie.setDuration(movieCreateRequest.getDuration());
        movie.setCreated(new Timestamp(System.currentTimeMillis()));
        movie.setChanged(new Timestamp(System.currentTimeMillis()));

        return movieHibernateRepository.save(movie);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieHibernate updateMovie(@PathVariable Long id,
                                      @RequestBody MovieCreateRequest movieCreateRequest) {

        MovieHibernate movie = movieHibernateRepository.findById(id);

        movie.setTitle(movieCreateRequest.getTitle());
        movie.setGenre(movieCreateRequest.getGenre());
        movie.setYear(movieCreateRequest.getYear());
        movie.setDuration(movieCreateRequest.getDuration());
        movie.setChanged(new Timestamp(System.currentTimeMillis()));
        return movieHibernateRepository.update(movie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteMovie(@PathVariable Long id) {
        return movieHibernateRepository.delete(id);
    }
}

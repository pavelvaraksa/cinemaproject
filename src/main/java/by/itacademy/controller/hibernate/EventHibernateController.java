package by.itacademy.controller.hibernate;

import by.itacademy.controller.request.EventCreateRequest;
import by.itacademy.dao.hibernate.EventHibernateRepository;
import by.itacademy.domain.hibernate.EventHibernate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/rest/events/hibernate")
@RequiredArgsConstructor
public class EventHibernateController {

    private final EventHibernateRepository eventHibernateRepository;

    @GetMapping
    public ResponseEntity<List<EventHibernate>> findAllEvents() {
        return new ResponseEntity<>(eventHibernateRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventHibernate findEventById(@PathVariable Long id) {
        return eventHibernateRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventHibernate saveEvent(@RequestBody EventCreateRequest eventCreateRequest) {
        EventHibernate event = new EventHibernate();
        event.setDate(eventCreateRequest.getDate());
        event.setTime(eventCreateRequest.getTime());
        event.setCreated(new Timestamp(System.currentTimeMillis()));
        event.setChanged(new Timestamp(System.currentTimeMillis()));
        event.setMovieId(eventCreateRequest.getMovieId());
        event.setCinemaId(eventCreateRequest.getCinemaId());

        return eventHibernateRepository.save(event);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventHibernate updateEvent(@PathVariable Long id,
                                      @RequestBody EventCreateRequest eventCreateRequest) {

        EventHibernate event = eventHibernateRepository.findById(id);

        event.setDate(eventCreateRequest.getDate());
        event.setTime(eventCreateRequest.getTime());
        event.setChanged(new Timestamp(System.currentTimeMillis()));
        event.setMovieId(eventCreateRequest.getMovieId());
        event.setCinemaId(eventCreateRequest.getCinemaId());
        return eventHibernateRepository.update(event);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteEvent(@PathVariable Long id) {
        return eventHibernateRepository.delete(id);
    }
}

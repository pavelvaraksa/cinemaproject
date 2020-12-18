package by.itacademy.controller.hibernate;

import by.itacademy.controller.request.EventCreateRequest;
import by.itacademy.dao.hibernate.EventAppRepository;
import by.itacademy.domain.hibernate.EventApp;
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
@RequestMapping("/rest/events/hibernate")
@RequiredArgsConstructor
public class EventAppController {

    private final EventAppRepository eventAppRepository;

    @GetMapping
    public ResponseEntity<List<EventApp>> findAllEvents() {
        return new ResponseEntity<>(eventAppRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventApp findEventById(@PathVariable Long id) {
        return eventAppRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventApp saveEvent(@RequestBody EventCreateRequest eventCreateRequest) {
        EventApp event = new EventApp();
        event.setDate(eventCreateRequest.getDate());
        event.setTime(eventCreateRequest.getTime());
        event.setTicketsCount(eventCreateRequest.getTicketsCount());
        event.setCreated(new Timestamp(System.currentTimeMillis()));
        event.setChanged(new Timestamp(System.currentTimeMillis()));
        event.setMovieId(eventCreateRequest.getMovieId());
        event.setCinemaId(eventCreateRequest.getCinemaId());

        return eventAppRepository.save(event);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventApp updateEvent(@PathVariable Long id,
                                @RequestBody EventCreateRequest eventCreateRequest) {

        EventApp event = eventAppRepository.findById(id);

        event.setDate(eventCreateRequest.getDate());
        event.setTime(eventCreateRequest.getTime());
        event.setTicketsCount(eventCreateRequest.getTicketsCount());
        event.setChanged(new Timestamp(System.currentTimeMillis()));
        event.setMovieId(eventCreateRequest.getMovieId());
        event.setCinemaId(eventCreateRequest.getCinemaId());
        return eventAppRepository.update(event);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteEvent(@PathVariable Long id) {
        return eventAppRepository.delete(id);
    }
}

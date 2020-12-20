package by.itacademy.controller;

import by.itacademy.controller.request.EventCreateRequest;
import by.itacademy.repository.EventRepository;
import by.itacademy.domain.Event;
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
@RequestMapping("/rest/events/hibernate")
@RequiredArgsConstructor
public class EventRestController {

    private final EventRepository eventRepository;

    @GetMapping
    public ResponseEntity<List<Event>> findAllEvents() {
        return new ResponseEntity<>(eventRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Event findEventById(@PathVariable Long id) {
        try {
            Event event = eventRepository.findById(id);
            log.info("Ok");
            return event;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event saveEvent(@RequestBody EventCreateRequest eventCreateRequest) {
        Event event = new Event();
        event.setDate(eventCreateRequest.getDate());
        event.setTime(eventCreateRequest.getTime());
        event.setTicketsCount(eventCreateRequest.getTicketsCount());
        event.setCreated(new Timestamp(System.currentTimeMillis()));
        event.setChanged(new Timestamp(System.currentTimeMillis()));
        event.setMovieId(eventCreateRequest.getMovieId());
        event.setCinemaId(eventCreateRequest.getCinemaId());

        return eventRepository.save(event);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Event updateEvent(@PathVariable Long id,
                             @RequestBody EventCreateRequest eventCreateRequest) {

        Event event;
        try {
            event = eventRepository.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

        event.setDate(eventCreateRequest.getDate());
        event.setTime(eventCreateRequest.getTime());
        event.setTicketsCount(eventCreateRequest.getTicketsCount());
        event.setChanged(new Timestamp(System.currentTimeMillis()));
        event.setMovieId(eventCreateRequest.getMovieId());
        event.setCinemaId(eventCreateRequest.getCinemaId());
        return eventRepository.update(event);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteEvent(@PathVariable Long id) {
        return eventRepository.delete(id);
    }
}

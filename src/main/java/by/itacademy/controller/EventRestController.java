package by.itacademy.controller;

import by.itacademy.controller.request.EventCreateRequest;
import by.itacademy.domain.Event;
import by.itacademy.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/events")
@RequiredArgsConstructor
public class EventRestController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<Event>> findAllEvents() {
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Event findEventById(@PathVariable Long id) {
        return eventService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event saveEvent(@RequestBody EventCreateRequest eventCreateRequest) {
        Event event = new Event();
        event.setDate(eventCreateRequest.getDate());
        event.setTime(eventCreateRequest.getTime());
        event.setMovieId(eventCreateRequest.getMovieId());
        event.setCinemaId(eventCreateRequest.getCinemaId());
        return eventService.save(event);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Event updateEvent(@PathVariable Long id,
                             @RequestBody EventCreateRequest eventCreateRequest) {

        Event event = eventService.findById(id);

        event.setDate(eventCreateRequest.getDate());
        event.setTime(eventCreateRequest.getTime());
        event.setMovieId(eventCreateRequest.getMovieId());
        event.setCinemaId(eventCreateRequest.getCinemaId());
        return eventService.update(event);
    }

    @DeleteMapping("/{event}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteEvent(@PathVariable Event event) {
        return eventService.delete(event);
    }
}

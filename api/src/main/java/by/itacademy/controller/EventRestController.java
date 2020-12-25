package by.itacademy.controller;

import by.itacademy.controller.request.EventCreateRequest;
import by.itacademy.exception.ControllerException;
import by.itacademy.exception.RepositoryException;
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
@RequestMapping("/rest/events")
@RequiredArgsConstructor
public class EventRestController {

    private final EventRepository eventRepository;

    @GetMapping
    public ResponseEntity<List<Event>> findAllEvents() throws ControllerException {
        try {
            log.info("Events exist");
            return new ResponseEntity<>(eventRepository.findAll(), HttpStatus.OK);
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't find not existing events");
        }
    }

    @GetMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public Event findEventById(@PathVariable Long eventId) throws ControllerException {
        try {
            Event eventToFindById = eventRepository.findById(eventId);
            log.info("Event with id " + eventId + " exists");
            return eventToFindById;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't find a not existing event");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event saveEvent(@RequestBody EventCreateRequest eventCreateRequest) throws ControllerException {
        try {
            Event eventToSave = new Event();
            eventToSave.setDate(eventCreateRequest.getDate());
            eventToSave.setTime(eventCreateRequest.getTime());
            eventToSave.setTicketsCount(eventCreateRequest.getTicketsCount());
            eventToSave.setCreated(new Timestamp(System.currentTimeMillis()));
            eventToSave.setChanged(new Timestamp(System.currentTimeMillis()));
            eventToSave.setMovieId(eventCreateRequest.getMovieId());
            eventToSave.setCinemaId(eventCreateRequest.getCinemaId());

            log.info("Event " + eventToSave + " was saved");
            return eventRepository.save(eventToSave);
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't save a not existing event");
        }
    }

    @PutMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public Event updateEvent(@PathVariable Long eventId,
                             @RequestBody EventCreateRequest eventCreateRequest) throws RepositoryException, ControllerException {

        Event eventToUpdate;

        try {
            eventToUpdate = eventRepository.findById(eventId);
            log.info("Event with id " + eventId + " was updated");
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't update a not existing event");
        }

        eventToUpdate.setDate(eventCreateRequest.getDate());
        eventToUpdate.setTime(eventCreateRequest.getTime());
        eventToUpdate.setTicketsCount(eventCreateRequest.getTicketsCount());
        eventToUpdate.setChanged(new Timestamp(System.currentTimeMillis()));
        eventToUpdate.setMovieId(eventCreateRequest.getMovieId());
        eventToUpdate.setCinemaId(eventCreateRequest.getCinemaId());
        return eventRepository.update(eventToUpdate);
    }

    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteEvent(@PathVariable Event eventId) throws ControllerException {
        try {
            log.info("User with id " + eventId + " was deleted");
            return eventRepository.delete(eventId);
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't delete a not existing user");
        }
    }
}

package by.itacademy.controller;

import by.itacademy.controller.request.EventCreateRequest;
import by.itacademy.exception.ControllerException;
import by.itacademy.exception.ServiceException;
import by.itacademy.domain.Event;
import by.itacademy.service.EventService;
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
@RequestMapping("/rest/events")
@RequiredArgsConstructor
public class EventRestController {

    private final EventService eventService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Event> findAllEvents() throws ControllerException {
        try {
            return eventService.findAll();
        } catch (ServiceException e) {
            log.error("Can't find any events.");
            throw new ControllerException("Can't find any events." + e.getMessage());
        }
    }

    @GetMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public Event findEventById(@PathVariable Long eventId) throws ControllerException {
        try {
            return eventService.findById(eventId);
        } catch (ServiceException e) {
            log.error("Can't find an event.");
            throw new ControllerException("Can't find an event." + e.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event saveEvent(@RequestBody EventCreateRequest eventCreateRequest) throws ControllerException {
        String date = eventCreateRequest.getDate();
        String time = eventCreateRequest.getTime();

        if (date == null || date.isEmpty() || time == null || time.isEmpty()) {
            throw new ControllerException("Event date or time can't be null or empty.");
        }

        Event eventToSave = new Event();
        eventToSave.setDate(eventCreateRequest.getDate());
        eventToSave.setTime(eventCreateRequest.getTime());
        eventToSave.setTicketsCount(eventCreateRequest.getTicketsCount());
        eventToSave.setCreated(new Timestamp(System.currentTimeMillis()));
        eventToSave.setChanged(new Timestamp(System.currentTimeMillis()));
        eventToSave.setMovieId(eventCreateRequest.getMovieId());
        eventToSave.setCinemaId(eventCreateRequest.getCinemaId());
        eventToSave.setTicketId(eventCreateRequest.getTicketId());

        try {
            return eventService.save(eventToSave);
        } catch (ServiceException e) {
            log.error("Can't save an event.");
            throw new ControllerException("Can't save an event." + e.getMessage());
        }
    }

    @PutMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public Event updateEvent(@PathVariable Long eventId,
                             @RequestBody EventCreateRequest eventCreateRequest) throws ControllerException {
        String date = eventCreateRequest.getDate();
        String time = eventCreateRequest.getTime();

        if (date == null || date.isEmpty() || time == null || time.isEmpty()) {
            throw new ControllerException("Event date or time can't be null or empty.");
        }

        try {
            Event foundEvent = eventService.findById(eventId);
            foundEvent.setDate(eventCreateRequest.getDate());
            foundEvent.setTime(eventCreateRequest.getTime());
            foundEvent.setTicketsCount(eventCreateRequest.getTicketsCount());
            foundEvent.setChanged(new Timestamp(System.currentTimeMillis()));
            return eventService.update(foundEvent);
        } catch (ServiceException e) {
            log.error("Can't find an event.");
            throw new ControllerException("Can't find an event." + e.getMessage());
        }
    }

    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public Event deleteEvent(@PathVariable Long eventId) throws ControllerException {
        try {
            return eventService.delete(eventId);
        } catch (ServiceException e) {
            log.error("Can't find an event.");
            throw new ControllerException("Can't find an event." + e.getMessage());
        }
    }
}

package by.itacademy.service.impl;

import by.itacademy.domain.Event;
import by.itacademy.domain.User;
import by.itacademy.exception.RepositoryException;
import by.itacademy.repository.EventRepository;
import by.itacademy.service.EventService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> findAll() {
        try {
            List<Event> eventsToFind = eventRepository.findAll();
            log.info("Events " + eventsToFind + " exist");
            return eventsToFind;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Event findById(Long eventId) {
        try {
            Event eventToFindById = eventRepository.findById(eventId);
            log.info("Event with id " + eventId + " exists");
            return eventToFindById;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Event save(Event event) {
        try {
            Event eventToSave = eventRepository.save(event);
            log.info("Event " + event + " was saved");
            return eventToSave;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Event update(Event event) {
        try {
            Event eventToUpdate = eventRepository.update(event);
            log.info("Event " + event + " was updated");
            return eventToUpdate;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Event delete(Long eventId) {
        try {
            Event eventToDelete = eventRepository.delete(eventId);
            log.info("Event with id " + eventId + " was deleted");
            return eventToDelete;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
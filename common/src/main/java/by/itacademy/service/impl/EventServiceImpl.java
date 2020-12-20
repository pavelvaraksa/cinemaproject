package by.itacademy.service.impl;

import by.itacademy.domain.Event;
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
            log.info("Events " + eventsToFind + " are exist");
            return eventsToFind;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Event findById(Long eventId) {
        try {
            Event eventToFindById = eventRepository.findById(eventId);
            log.info("Event " + eventId + " is exist");
            return eventToFindById;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Event save(Event event) {
        try {
            Event eventToSave = eventRepository.save(event);
            log.info("Event " + event + " saved");
            return eventToSave;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Event update(Event event) {
        try {
            Event eventToUpdate = eventRepository.update(event);
            log.info("Event " + event + " updated");
            return eventToUpdate;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Long delete(Long eventId) {
        try {
            Long eventToDelete = eventRepository.delete(eventId);
            log.info("Event " + eventId + " deleted");
            return eventToDelete;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}

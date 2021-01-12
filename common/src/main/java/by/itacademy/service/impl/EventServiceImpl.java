package by.itacademy.service.impl;

import by.itacademy.domain.Event;
import by.itacademy.exception.RepositoryException;
import by.itacademy.exception.ServiceException;
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
    public List<Event> findAll() throws ServiceException {

        List<Event> existingEvents;

        try {
            existingEvents = eventRepository.findAll();
            if (existingEvents.isEmpty()) {
                String errorMessage = "The list is empty.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            } else {
                log.info("Events exist.");
                return existingEvents;
            }
        } catch (RepositoryException e) {
            throw new ServiceException("Event service exception while trying to find all events." + e.getMessage());
        }
    }

    @Override
    public Event findById(Long eventId) throws ServiceException {

        Event eventToFindById;

        try {
            eventToFindById = eventRepository.findById(eventId);
            if (eventToFindById == null) {
                String errorMessage = "Event id can't be null.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException e) {
            throw new ServiceException("Event service exception while trying to find an event." + e.getMessage());
        }

        try {
            log.info("Event with id " + eventId + " exists.");
            return eventRepository.findById(eventId);
        } catch (RepositoryException e) {
            String errorMessage = "Can't find an event.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Event save(Event event) throws ServiceException {

        List<Event> existingEvents;

        try {
            existingEvents = eventRepository.findAll();
        } catch (RepositoryException e) {
            String errorMessage = "Can't get all events.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }

        for (Event existingEvent : existingEvents) {
            boolean hasSameEvent = existingEvent.getDate().equals(event.getDate());

            if (hasSameEvent) {
                String errorMessage = "Event " + event.getId() + " already exists.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        }

        try {
            Event savedEvent = eventRepository.save(event);
            log.info("Event " + event.getId() + " was saved.");
            return savedEvent;
        } catch (RepositoryException e) {
            throw new ServiceException("Event service exception while trying to save a event:" + e.getMessage());
        }
    }

    @Override
    public Event update(Event event) throws ServiceException {

        try {
            log.info("Event " + event.getId() + " was updated.");
            return eventRepository.update(event);
        } catch (RepositoryException e) {
            String errorMessage = "Can't get an event.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Event delete(Long eventId) throws ServiceException {

        Event eventToFindById;

        try {
            eventToFindById = eventRepository.findById(eventId);
            if (eventToFindById == null) {
                String errorMessage = "Event id can't be null.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException e) {
            throw new ServiceException("Event service exception while trying to delete an event." + e.getMessage());
        }

        try {
            log.info("Event " + eventId + " was deleted.");
            return eventRepository.delete(eventId);
        } catch (RepositoryException e) {
            String errorMessage = "Can't find an event.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
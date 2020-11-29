package by.itacademy.service.impl;

import by.itacademy.domain.Event;
import by.itacademy.repository.EventRepository;
import by.itacademy.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event findById(Long eventId) {
        return eventRepository.findById(eventId);
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event update(Event event) {
        return eventRepository.update(event);
    }

    @Override
    public Long delete(Event event) {
        return eventRepository.delete(event);
    }
}

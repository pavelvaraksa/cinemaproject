package by.itacademy.service;

import by.itacademy.domain.Event;

import java.util.List;

public interface EventService {

    List<Event> findAll();

    Event findById(Long eventId);

    Event save(Event event);

    Event update(Event event);

    Long delete(Event eventId);
}

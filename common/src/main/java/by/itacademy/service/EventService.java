package by.itacademy.service;

import by.itacademy.domain.Event;
import by.itacademy.exception.ServiceException;

import java.util.List;

public interface EventService {

    List<Event> findAll() throws ServiceException;

    Event findById(Long eventId) throws ServiceException;

    Event save(Event event) throws ServiceException;

    Event update(Event event) throws ServiceException;

    Event delete(Long eventId) throws ServiceException;
}

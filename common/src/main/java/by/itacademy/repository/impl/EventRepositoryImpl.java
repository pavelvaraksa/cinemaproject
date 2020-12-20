package by.itacademy.repository.impl;

import by.itacademy.domain.Event;
import by.itacademy.exception.RepositoryException;
import by.itacademy.repository.EventRepository;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
@Log4j
public class EventRepositoryImpl implements EventRepository {

    private final SessionFactory sessionFactory;

    public EventRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Event> findAll() throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            String eventsToFind = "select u from Event u";
            if (session != null) {
                return session.createQuery(eventsToFind, Event.class).list();
            } else {
                throw new RepositoryException("No one event does not exist");
            }
        }
    }

    @Override
    public Event findById(Long eventId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            Event eventToFindById = session.find(Event.class, eventId);
            if (eventToFindById != null) {
                return eventToFindById;
            } else {
                throw new RepositoryException("Event with id " + eventId + " does not exist");
            }
        }
    }

    @Override
    public Event save(Event event) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            Event eventToSave = session.find(Event.class, event);
            if (eventToSave != null) {
                return eventToSave;
            } else {
                throw new RepositoryException("Event " + event + " saved");
            }
        }
    }

    @Override
    public Event update(Event eventId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            Event eventToUpdate = session.find(Event.class, eventId);
            if (eventToUpdate != null) {
                return eventToUpdate;
            } else {
                throw new RepositoryException("Event with id " + eventId + " updated");
            }
        }
    }

    @Override
    public Long delete(Long eventId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.delete(eventId);
            transaction.commit();
            log.info("Event with id " + eventId + " deleted");
            return eventId;
        }
    }
}

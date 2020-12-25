package by.itacademy.repository.impl;

import by.itacademy.exception.RepositoryException;
import by.itacademy.repository.EventRepository;
import by.itacademy.domain.Event;
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
            if (session != null) {
                String hqlQuery = "select u from Event u";
                return session.createQuery(hqlQuery, Event.class).list();
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
                throw new RepositoryException("Event does not exist");
            }
        }
    }

    @Override
    public Event save(Event event) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            session.save(event);
            if (event != null) {
                return event;
            } else {
                throw new RepositoryException("Event does not save");
            }
        }
    }

    @Override
    public Event update(Event eventId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.update(eventId);
            transaction.commit();
            if (eventId != null) {
                return eventId;
            } else {
                throw new RepositoryException("Event does not update");
            }
        }
    }

    @Override
    public Long delete(Event eventId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            session.delete(eventId);
            if (eventId != null) {
                return eventId.getId();
            } else {
                throw new RepositoryException("Event does not delete");
            }
        }
    }
}

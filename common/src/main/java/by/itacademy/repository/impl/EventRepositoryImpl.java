package by.itacademy.repository.impl;

import by.itacademy.repository.EventRepository;
import by.itacademy.domain.Event;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class EventRepositoryImpl implements EventRepository {

    private final SessionFactory sessionFactory;

    public EventRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Event> findAll() {

        try (Session session = sessionFactory.openSession()) {
            String hqlQuery = "select e from Event e";
            return session.createQuery(hqlQuery, Event.class).list();
        }
    }

    @Override
    public Event findById(Long eventId) {

        try (Session session = sessionFactory.openSession()) {
            return session.find(Event.class, eventId);
        }
    }

    @Override
    public Event save(Event event) {

        try (Session session = sessionFactory.openSession()) {
            session.save(event);
            return event;
        }
    }

    @Override
    public Event update(Event eventId) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.update(eventId);
            transaction.commit();
            return eventId;
        }
    }

    @Override
    public Event delete(Long eventId) {

        try (Session session = sessionFactory.openSession()) {
            Event eventDeleteById = session.find(Event.class, eventId);
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.delete(eventDeleteById);
            transaction.commit();
            return eventDeleteById;
        }
    }
}

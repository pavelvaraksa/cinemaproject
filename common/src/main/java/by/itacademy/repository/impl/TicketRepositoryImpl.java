package by.itacademy.repository.impl;

import by.itacademy.repository.TicketRepository;
import by.itacademy.domain.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class TicketRepositoryImpl implements TicketRepository {

    private final SessionFactory sessionFactory;

    public TicketRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Ticket> findAll() {
        try (Session session = sessionFactory.openSession()) {
            String hqlQuery = "select u from Ticket u";
            return session.createQuery(hqlQuery, Ticket.class).list();
        }
    }

    @Override
    public Ticket findById(Long ticketId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Ticket.class, ticketId);
        }
    }

    @Override
    public Ticket save(Ticket ticket) {
        try (Session session = sessionFactory.openSession()) {
            session.save(ticket);
            return ticket;
        }
    }

    @Override
    public Ticket update(Ticket ticketId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.update(ticketId);
            transaction.commit();
            return ticketId;
        }
    }

    @Override
    public Ticket delete(Long ticketId) {
        try (Session session = sessionFactory.openSession()) {
            Ticket ticketDeleteById = session.find(Ticket.class, ticketId);
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.delete(ticketDeleteById);
            transaction.commit();
            return ticketDeleteById;
        }
    }
}

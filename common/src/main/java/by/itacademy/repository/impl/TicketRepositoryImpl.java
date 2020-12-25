package by.itacademy.repository.impl;

import by.itacademy.exception.RepositoryException;
import by.itacademy.repository.TicketRepository;
import by.itacademy.domain.Ticket;
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
public class TicketRepositoryImpl implements TicketRepository {

    private final SessionFactory sessionFactory;

    public TicketRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Ticket> findAll() throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            if (session != null) {
                String hqlQuery = "select u from Ticket u";
                return session.createQuery(hqlQuery, Ticket.class).list();
            } else {
                throw new RepositoryException("No one ticket does not exist");
            }
        }
    }

    @Override
    public Ticket findById(Long ticketId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            Ticket ticketToFindById = session.find(Ticket.class, ticketId);
            if (ticketToFindById != null) {
                return ticketToFindById;
            } else {
                throw new RepositoryException("Ticket does not exist");
            }
        }
    }

    @Override
    public Ticket save(Ticket ticket) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            session.save(ticket);
            if (ticket != null) {
                return ticket;
            } else {
                throw new RepositoryException("Ticket does not save");
            }
        }
    }

    @Override
    public Ticket update(Ticket ticketId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.update(ticketId);
            transaction.commit();
            if (ticketId != null) {
                return ticketId;
            } else {
                throw new RepositoryException("Ticket does not update");
            }
        }
    }

    @Override
    public Long delete(Ticket ticketId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            session.delete(ticketId);
            if (ticketId != null) {
                return ticketId.getId();
            } else {
                throw new RepositoryException("Ticket does not delete");
            }
        }
    }
}

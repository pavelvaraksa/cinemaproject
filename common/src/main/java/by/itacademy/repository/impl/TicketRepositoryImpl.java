package by.itacademy.repository.impl;

import by.itacademy.domain.Ticket;
import by.itacademy.exception.RepositoryException;
import by.itacademy.repository.TicketRepository;
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
            String ticketsToFind = "select u from Ticket u";
            if (session != null) {
                return session.createQuery(ticketsToFind, Ticket.class).list();
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
                throw new RepositoryException("Ticket with id " + ticketId + " does not exist");
            }
        }
    }

    @Override
    public Ticket save(Ticket ticket) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            Ticket ticketToSave = session.find(Ticket.class, ticket);
            if (ticketToSave != null) {
                return ticketToSave;
            } else {
                throw new RepositoryException("Ticket " + ticket + " saved");
            }
        }
    }

    @Override
    public Ticket update(Ticket ticketId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            Ticket ticketToUpdate = session.find(Ticket.class, ticketId);
            if (ticketToUpdate != null) {
                return ticketToUpdate;
            } else {
                throw new RepositoryException("Ticket with id " + ticketId + " updated");
            }
        }
    }

    @Override
    public Long delete(Long ticketId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.delete(ticketId);
            transaction.commit();
            log.info("Ticket with id " + ticketId + " deleted");
            return ticketId;
        }
    }
}

package by.itacademy.service.impl;

import by.itacademy.domain.Ticket;
import by.itacademy.domain.User;
import by.itacademy.exception.RepositoryException;
import by.itacademy.repository.TicketRepository;
import by.itacademy.service.TicketService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> findAll() {
        try {
            List<Ticket> ticketsToFind = ticketRepository.findAll();
            log.info("Tickets " + ticketsToFind + " exist");
            return ticketsToFind;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Ticket findById(Long ticketId) {
        try {
            Ticket ticketToFindById = ticketRepository.findById(ticketId);
            log.info("Ticket with id " + ticketId + " exists");
            return ticketToFindById;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Ticket save(Ticket ticket) {
        try {
            Ticket ticketToSave = ticketRepository.save(ticket);
            log.info("Ticket " + ticket + " was saved");
            return ticketToSave;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Ticket update(Ticket ticket) {
        try {
            Ticket ticketToUpdate = ticketRepository.update(ticket);
            log.info("Ticket " + ticket + " was updated");
            return ticketToUpdate;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Ticket delete(Long ticketId) {
        try {
            Ticket ticketToDelete = ticketRepository.delete(ticketId);
            log.info("Ticket with id " + ticketId + " was deleted");
            return ticketToDelete;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
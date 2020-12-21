package by.itacademy.service.impl;

import by.itacademy.domain.Ticket;
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
            log.info("Tickets " + ticketsToFind + " are exist");
            return ticketsToFind;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Ticket findById(Long ticketId) {
        try {
            Ticket ticketToFindById = ticketRepository.findById(ticketId);
            log.info("Ticket " + ticketId + " is exist");
            return ticketToFindById;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Ticket save(Ticket ticket) {
        try {
            Ticket ticketToSave = ticketRepository.save(ticket);
            log.info("Ticket " + ticket + " saved");
            return ticketToSave;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Ticket update(Ticket ticket) {
        try {
            Ticket ticketToUpdate = ticketRepository.update(ticket);
            log.info("Ticket " + ticket + " updated");
            return ticketToUpdate;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Long delete(Long ticketId) {
        try {
            Long ticketToDelete = ticketRepository.delete(ticketId);
            log.info("Ticket " + ticketId + " deleted");
            return ticketToDelete;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
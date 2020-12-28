package by.itacademy.service.impl;

import by.itacademy.domain.Ticket;
import by.itacademy.exception.RepositoryException;
import by.itacademy.exception.ServiceException;
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
    public List<Ticket> findAll() throws ServiceException {
        List<Ticket> existingTickets;
        try {
            existingTickets = ticketRepository.findAll();
            if (existingTickets.isEmpty()) {
                String errorMessage = "The list is empty.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            } else {
                log.info("Tickets exist.");
                return existingTickets;
            }
        } catch (RepositoryException e) {
            throw new ServiceException("Ticket service exception while trying to find all tickets." + e.getMessage());
        }
    }

    @Override
    public Ticket findById(Long ticketId) throws ServiceException {
        Ticket ticketToFindById;

        try {
            ticketToFindById = ticketRepository.findById(ticketId);
            if (ticketToFindById == null) {
                String errorMessage = "Ticket id can't be null.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException e) {
            throw new ServiceException("Ticket service exception while trying to find a ticket." + e.getMessage());
        }

        try {
            log.info("Ticket with id " + ticketId + " exists.");
            return ticketRepository.findById(ticketId);
        } catch (RepositoryException e) {
            String errorMessage = "Can't find a ticket.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Ticket save(Ticket ticket) throws ServiceException {
        List<Ticket> existingTickets;
        try {
            existingTickets = ticketRepository.findAll();
        } catch (RepositoryException e) {
            String errorMessage = "Can't get all tickets.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }

        for (Ticket existingTicket : existingTickets) {
            boolean hasSamePlaceNumber = existingTicket.getPlaceNumber() == ticket.getPlaceNumber();

            if (hasSamePlaceNumber) {
                String errorMessage = "Place number " + ticket.getPlaceNumber() + " already exists.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        }

        try {
            Ticket savedTicket = ticketRepository.save(ticket);
            log.info("Ticket " + ticket + " was saved");
            return savedTicket;
        } catch (RepositoryException e) {
            throw new ServiceException("Ticket service exception while trying to save a ticket:" + e.getMessage());
        }
    }

    @Override
    public Ticket update(Ticket ticket) throws ServiceException {
        try {
            return ticketRepository.update(ticket);
        } catch (RepositoryException e) {
            String errorMessage = "Can't get a ticket.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Ticket delete(Long ticketId) throws ServiceException {
        Ticket ticketToFindById;

        try {
            ticketToFindById = ticketRepository.findById(ticketId);
            if (ticketToFindById == null) {
                String errorMessage = "Ticket id can't be null.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException e) {
            throw new ServiceException("Ticket service exception while trying to delete a ticket." + e.getMessage());
        }

        try {
            log.info("Ticket with id " + ticketId + " was deleted.");
            return ticketRepository.delete(ticketId);
        } catch (RepositoryException e) {
            String errorMessage = "Can't find a ticket.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
package by.itacademy.service;

import by.itacademy.domain.Ticket;
import by.itacademy.exception.ServiceException;

import java.util.List;

public interface TicketService {

    List<Ticket> findAll() throws ServiceException;

    Ticket findById(Long ticketId) throws ServiceException;

    Ticket save(Ticket ticket) throws ServiceException;

    Ticket update(Ticket ticket) throws ServiceException;

    Ticket delete(Long ticketId) throws ServiceException;
}

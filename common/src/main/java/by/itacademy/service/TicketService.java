package by.itacademy.service;

import by.itacademy.domain.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> findAll();

    Ticket findById(Long ticketId);

    Ticket save(Ticket ticket);

    Ticket update(Ticket ticket);

    Long delete(Ticket ticketId);
}

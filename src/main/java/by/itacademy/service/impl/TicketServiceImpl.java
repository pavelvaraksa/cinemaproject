package by.itacademy.service.impl;

import by.itacademy.domain.Ticket;
import by.itacademy.repository.TicketRepository;
import by.itacademy.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findById(Long ticketId) {
        return ticketRepository.findById(ticketId);
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket update(Ticket ticket) {
        return ticketRepository.update(ticket);
    }

    @Override
    public Long delete(Ticket ticket) {
        return ticketRepository.delete(ticket);
    }
}

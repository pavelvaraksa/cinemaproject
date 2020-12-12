package by.itacademy.controller.hibernate;

import by.itacademy.controller.request.TicketCreateRequest;
import by.itacademy.dao.hibernate.TicketHibernateRepository;
import by.itacademy.domain.hibernate.TicketHibernate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/rest/tickets/hibernate")
@RequiredArgsConstructor
public class TicketHibernateController {

    private final TicketHibernateRepository ticketHibernateRepository;

    @GetMapping
    public ResponseEntity<List<TicketHibernate>> findAllTickets() {
        return new ResponseEntity<>(ticketHibernateRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TicketHibernate findTicketById(@PathVariable Long id) {
        return ticketHibernateRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketHibernate saveTicket(@RequestBody TicketCreateRequest ticketCreateRequest) {
        TicketHibernate ticket = new TicketHibernate();
        ticket.setPlaceNumber(ticketCreateRequest.getPlaceNumber());
        ticket.setPrice(ticketCreateRequest.getPrice());
        ticket.setCreated(new Timestamp(System.currentTimeMillis()));
        ticket.setChanged(new Timestamp(System.currentTimeMillis()));
        ticket.setUserId(ticketCreateRequest.getUserId());
        ticket.setEventId(ticketCreateRequest.getEventId());

        return ticketHibernateRepository.save(ticket);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TicketHibernate updateTicket(@PathVariable Long id,
                                        @RequestBody TicketCreateRequest ticketCreateRequest) {

        TicketHibernate ticket = ticketHibernateRepository.findById(id);

        ticket.setPlaceNumber(ticketCreateRequest.getPlaceNumber());
        ticket.setPrice(ticketCreateRequest.getPrice());
        ticket.setChanged(new Timestamp(System.currentTimeMillis()));
        ticket.setUserId(ticketCreateRequest.getUserId());
        ticket.setEventId(ticketCreateRequest.getEventId());
        return ticketHibernateRepository.update(ticket);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteTicket(@PathVariable Long id) {
        return ticketHibernateRepository.delete(id);
    }
}

package by.itacademy.controller;

import by.itacademy.controller.request.TicketCreateRequest;
import by.itacademy.domain.Ticket;
import by.itacademy.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/rest/tickets")
@RequiredArgsConstructor
public class TicketRestController {

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<Ticket>> findAllTickets() {
        return new ResponseEntity<>(ticketService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ticket findTicketById(@PathVariable Long id) {
        return ticketService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket saveTicket(@RequestBody TicketCreateRequest ticketCreateRequest) {
        Ticket ticket = new Ticket();
        ticket.setPlaceNumber(ticketCreateRequest.getPlaceNumber());
        ticket.setPrice(ticketCreateRequest.getPrice());
        ticket.setCreated(new Timestamp(System.currentTimeMillis()));
        ticket.setChanged(new Timestamp(System.currentTimeMillis()));
        ticket.setUserId(ticketCreateRequest.getUserId());
        ticket.setEventId(ticketCreateRequest.getEventId());
        return ticketService.save(ticket);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ticket updateTicket(@PathVariable Long id,
                               @RequestBody TicketCreateRequest ticketCreateRequest) {

        Ticket ticket = ticketService.findById(id);

        ticket.setPlaceNumber(ticketCreateRequest.getPlaceNumber());
        ticket.setPrice(ticketCreateRequest.getPrice());
        ticket.setChanged(new Timestamp(System.currentTimeMillis()));
        ticket.setUserId(ticketCreateRequest.getUserId());
        ticket.setEventId(ticketCreateRequest.getEventId());
        return ticketService.update(ticket);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteTicket(@PathVariable Long id) {
        return ticketService.delete(id);
    }
}

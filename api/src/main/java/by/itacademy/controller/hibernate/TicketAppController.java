package by.itacademy.controller.hibernate;

import by.itacademy.controller.request.TicketCreateRequest;
import by.itacademy.dao.hibernate.TicketAppRepository;
import by.itacademy.domain.hibernate.TicketApp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/rest/tickets/hibernate")
@RequiredArgsConstructor
public class TicketAppController {

    private final TicketAppRepository ticketAppRepository;

    @GetMapping
    public ResponseEntity<List<TicketApp>> findAllTickets() {
        return new ResponseEntity<>(ticketAppRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TicketApp findTicketById(@PathVariable Long id) {
        return ticketAppRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketApp saveTicket(@RequestBody TicketCreateRequest ticketCreateRequest) {
        TicketApp ticket = new TicketApp();
        ticket.setPlaceNumber(ticketCreateRequest.getPlaceNumber());
        ticket.setPrice(ticketCreateRequest.getPrice());
        ticket.setCreated(new Timestamp(System.currentTimeMillis()));
        ticket.setChanged(new Timestamp(System.currentTimeMillis()));
        ticket.setUserId(ticketCreateRequest.getUserId());
        ticket.setEventId(ticketCreateRequest.getEventId());

        return ticketAppRepository.save(ticket);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TicketApp updateTicket(@PathVariable Long id,
                                  @RequestBody TicketCreateRequest ticketCreateRequest) {

        TicketApp ticket = ticketAppRepository.findById(id);

        ticket.setPlaceNumber(ticketCreateRequest.getPlaceNumber());
        ticket.setPrice(ticketCreateRequest.getPrice());
        ticket.setChanged(new Timestamp(System.currentTimeMillis()));
        ticket.setUserId(ticketCreateRequest.getUserId());
        ticket.setEventId(ticketCreateRequest.getEventId());
        return ticketAppRepository.update(ticket);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteTicket(@PathVariable Long id) {
        return ticketAppRepository.delete(id);
    }
}

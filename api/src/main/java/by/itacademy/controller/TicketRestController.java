package by.itacademy.controller;

import by.itacademy.controller.request.TicketCreateRequest;
import by.itacademy.domain.Ticket;
import by.itacademy.exception.ControllerException;
import by.itacademy.exception.ServiceException;
import by.itacademy.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
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

@Log4j
@RestController
@RequestMapping("/rest/tickets")
@RequiredArgsConstructor
public class TicketRestController {

    private final TicketService ticketService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Ticket> findAllTickets() throws ControllerException {
        try {
            return ticketService.findAll();
        } catch (ServiceException e) {
            log.error("Can't find any tickets.");
            throw new ControllerException("Can't find any tickets." + e.getMessage());
        }
    }

    @GetMapping("/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    public Ticket findTicketById(@PathVariable Long ticketId) throws ControllerException {
        try {
            return ticketService.findById(ticketId);
        } catch (ServiceException e) {
            log.error("Can't find a ticket.");
            throw new ControllerException("Can't find a ticket." + e.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket saveTicket(@RequestBody TicketCreateRequest ticketCreateRequest) throws ControllerException {
        int placeNumber = ticketCreateRequest.getPlaceNumber();
        double price = ticketCreateRequest.getPrice();

        if (placeNumber == 0 || price == 0) {
            throw new ControllerException("Place number or price can't be null.");
        }

        Ticket ticketToSave = new Ticket();
        ticketToSave.setPlaceNumber(ticketCreateRequest.getPlaceNumber());
        ticketToSave.setPrice(ticketCreateRequest.getPrice());
        ticketToSave.setCreated(new Timestamp(System.currentTimeMillis()));
        ticketToSave.setChanged(new Timestamp(System.currentTimeMillis()));
        ticketToSave.setUserId(ticketCreateRequest.getUserId());
        ticketToSave.setEventId(ticketCreateRequest.getEventId());

        try {
            return ticketService.save(ticketToSave);
        } catch (ServiceException e) {
            log.error("Can't save a ticket.");
            throw new ControllerException("Can't save a ticket." + e.getMessage());
        }
    }

    @PutMapping("/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    public Ticket updateTicket(@PathVariable Long ticketId,
                               @RequestBody TicketCreateRequest ticketCreateRequest) throws ControllerException {
        int placeNumber = ticketCreateRequest.getPlaceNumber();
        double price = ticketCreateRequest.getPrice();

        if (placeNumber == 0 || price == 0) {
            throw new ControllerException("Place number or price can't be null.");
        }

        try {
            Ticket foundTicket = ticketService.findById(ticketId);
            foundTicket.setPlaceNumber(ticketCreateRequest.getPlaceNumber());
            foundTicket.setPrice(ticketCreateRequest.getPrice());
            foundTicket.setChanged(new Timestamp(System.currentTimeMillis()));
            return ticketService.update(foundTicket);
        } catch (ServiceException e) {
            log.error("Can't find a ticket.");
            throw new ControllerException("Can't find a ticket." + e.getMessage());
        }
    }

    @DeleteMapping("/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    public Ticket deleteTicket(@PathVariable Long ticketId) throws ControllerException {
        try {
            return ticketService.delete(ticketId);
        } catch (ServiceException e) {
            log.error("Can't find a ticket.");
            throw new ControllerException("Can't find a ticket." + e.getMessage());
        }
    }
}
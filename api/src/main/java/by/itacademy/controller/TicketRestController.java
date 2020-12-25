package by.itacademy.controller;

import by.itacademy.controller.request.TicketCreateRequest;
import by.itacademy.exception.ControllerException;
import by.itacademy.exception.RepositoryException;
import by.itacademy.repository.TicketRepository;
import by.itacademy.domain.Ticket;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
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

@Log4j
@RestController
@RequestMapping("/rest/tickets")
@RequiredArgsConstructor
public class TicketRestController {

    private final TicketRepository ticketRepository;

    @GetMapping
    public ResponseEntity<List<Ticket>> findAllTickets() throws ControllerException {
        try {
            log.info("Tickets exist");
            return new ResponseEntity<>(ticketRepository.findAll(), HttpStatus.OK);
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't find not existing tickets");
        }
    }

    @GetMapping("/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    public Ticket findTicketById(@PathVariable Long ticketId) throws ControllerException{
        try {
            Ticket ticketToFindById = ticketRepository.findById(ticketId);
            log.info("Ticket with id " + ticketId + " exists");
            return ticketToFindById;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't find a not existing ticket");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket saveTicket(@RequestBody TicketCreateRequest ticketCreateRequest) throws ControllerException{
        try {
            Ticket ticketToSave = new Ticket();
            ticketToSave.setPlaceNumber(ticketCreateRequest.getPlaceNumber());
            ticketToSave.setPrice(ticketCreateRequest.getPrice());
            ticketToSave.setCreated(new Timestamp(System.currentTimeMillis()));
            ticketToSave.setChanged(new Timestamp(System.currentTimeMillis()));
            ticketToSave.setUserId(ticketCreateRequest.getUserId());
            ticketToSave.setEventId(ticketCreateRequest.getEventId());

            log.info("Ticket " + ticketToSave + " was saved");
            return ticketRepository.save(ticketToSave);
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't save a not existing ticket");
        }
    }

    @PutMapping("/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    public Ticket updateTicket(@PathVariable Long ticketId,
                               @RequestBody TicketCreateRequest ticketCreateRequest) throws ControllerException, RepositoryException {
        Ticket ticketToUpdate;
        try {
            ticketToUpdate = ticketRepository.findById(ticketId);
            log.info("Ticket with id " + ticketId + " was updated");
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't update a not existing ticket");
        }

        ticketToUpdate.setPlaceNumber(ticketCreateRequest.getPlaceNumber());
        ticketToUpdate.setPrice(ticketCreateRequest.getPrice());
        ticketToUpdate.setChanged(new Timestamp(System.currentTimeMillis()));
        ticketToUpdate.setUserId(ticketCreateRequest.getUserId());
        ticketToUpdate.setEventId(ticketCreateRequest.getEventId());
        return ticketRepository.update(ticketToUpdate);
    }

    @DeleteMapping("/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteTicket(@PathVariable Ticket ticketId) throws ControllerException{
        try {
            log.info("User with id " + ticketId + " was deleted");
            return ticketRepository.delete(ticketId);
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't delete a not existing user");
        }
    }
}
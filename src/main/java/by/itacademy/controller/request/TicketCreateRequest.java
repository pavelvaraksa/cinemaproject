package by.itacademy.controller.request;

import lombok.Data;

@Data
public class TicketCreateRequest {

    private int placeNumber;

    private double price;

    private Long userId;

    private Long eventId;
}

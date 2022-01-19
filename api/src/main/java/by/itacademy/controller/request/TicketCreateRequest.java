package by.itacademy.controller.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class TicketCreateRequest {

    private int placeNumber;

    private double price;

    private Long userId;
}

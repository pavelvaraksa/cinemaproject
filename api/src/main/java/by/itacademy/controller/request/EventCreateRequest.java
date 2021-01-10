package by.itacademy.controller.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class EventCreateRequest {

    private String date;

    private String time;

    private int ticketsCount;

    private Long ticketId;
}

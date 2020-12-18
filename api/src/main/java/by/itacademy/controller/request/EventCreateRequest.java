package by.itacademy.controller.request;

import lombok.Data;

@Data
public class EventCreateRequest {

    private String date;

    private String time;

    private int ticketsCount;

    private Long movieId;

    private Long cinemaId;
}

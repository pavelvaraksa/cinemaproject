package by.itacademy.controller.request;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;


@Data
public class EventCreateRequest {

    private Date date;

    private Time time;

    private Long movieId;

    private Long cinemaId;
}

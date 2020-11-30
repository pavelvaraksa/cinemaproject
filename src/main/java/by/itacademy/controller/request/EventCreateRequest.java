package by.itacademy.controller.request;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class EventCreateRequest {

    private Date date;

    private Time time;

    private Long movieId;

    private Long cinemaId;
}

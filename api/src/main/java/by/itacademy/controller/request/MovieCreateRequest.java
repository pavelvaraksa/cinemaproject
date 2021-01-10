package by.itacademy.controller.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class MovieCreateRequest {

    private String title;

    private String genre;

    private int year;

    private int duration;

    private Long cinemaId;
}

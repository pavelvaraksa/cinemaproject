package by.itacademy.controller.request;

import lombok.Data;

@Data
public class MovieCreateRequest {

    private String title;

    private String genre;

    private int year;

    private int duration;
}

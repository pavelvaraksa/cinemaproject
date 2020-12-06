package by.itacademy.controller.request;

import lombok.Data;

@Data
public class CinemaCreateRequest {

    private String name;

    private int ticketsCount;

    private String phoneNumber;

    private String paymentMethod;

    private Long locationId;

    private Long movieId;
}
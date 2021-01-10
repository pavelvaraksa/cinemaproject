package by.itacademy.controller.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class CinemaCreateRequest {

    private String name;

    private String address;

    private String phoneNumber;

    private String paymentMethod;

    private Long locationId;

    private Long eventId;
}

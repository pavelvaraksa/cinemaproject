package by.itacademy.controller.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class UserCreateRequest {

    private String name;

    private String surname;

    private String login;

    private String password;
}

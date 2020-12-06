package by.itacademy.controller.request;

import lombok.Data;

@Data
public class UserCreateRequest {

    private String login;

    private String password;
}

package by.itacademy.controller.request;

import lombok.Data;

@Data
public class UserUpdateCreateRequest {

    private String login;

    private String password;
}

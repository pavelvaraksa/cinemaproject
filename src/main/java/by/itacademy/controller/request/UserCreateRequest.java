package by.itacademy.controller.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserCreateRequest {

    private String login;

    private String password;

    private String role;

    private Timestamp created;

    private Timestamp changed;
}

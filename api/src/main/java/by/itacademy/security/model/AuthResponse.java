package by.itacademy.security.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

    private String login;

    private String token;
}

package by.itacademy.domain;

import lombok.Data;

@Data
public class Role {

    private Long id;

    private SystemRoles roleName = SystemRoles.ROLE_USER;

    private Long userId;
}

package by.itacademy.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Role {

    private Long id;

    private SystemRoles roleUser = SystemRoles.ROLE_USER;

    private SystemRoles roleManager = SystemRoles.ROLE_MANAGER;

    private SystemRoles roleAdmin = SystemRoles.ROLE_ADMIN;

    private Long userId;
}

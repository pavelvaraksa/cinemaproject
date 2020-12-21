package by.itacademy.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Role {

    private Long id;

    private SystemRoles roleName = SystemRoles.ROLE_USER;

    private Long userId;
}

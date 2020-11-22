package by.itacademy.domain;

import lombok.Setter;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Role {

    private Long Id;

    private SystemRoles adminName = SystemRoles.ROLE_ADMIN;

    private SystemRoles managerName = SystemRoles.ROLE_MANAGER;

    private SystemRoles userName = SystemRoles.ROLE_USER;

    private Long adminId;

    private Long managerId;

    private Long userId;
}


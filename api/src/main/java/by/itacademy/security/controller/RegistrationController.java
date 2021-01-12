package by.itacademy.security.controller;

import by.itacademy.controller.request.UserCreateRequest;
import by.itacademy.domain.SystemRoles;
import by.itacademy.domain.User;
import by.itacademy.domain.UserRole;
import by.itacademy.exception.ServiceException;
import by.itacademy.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<Map<String, Object>> registration(@RequestBody UserCreateRequest userCreateRequest) throws ServiceException {

        User user = new User();
        user.setName(userCreateRequest.getName());
        user.setSurname(userCreateRequest.getSurname());
        user.setLogin(userCreateRequest.getLogin());
        user.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));
        user.setCreated(new java.sql.Timestamp(System.currentTimeMillis()));
        user.setChanged(new java.sql.Timestamp(System.currentTimeMillis()));
        user.setRole(new UserRole(SystemRoles.ROLE_USER, user));
        User savedUser = userService.save(user);

        Map<String, Object> result = new HashMap<>();

        result.put("id", savedUser.getId());
        result.put("login", savedUser.getLogin());

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}

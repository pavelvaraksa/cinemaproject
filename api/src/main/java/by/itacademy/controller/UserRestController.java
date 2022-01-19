package by.itacademy.controller;

import by.itacademy.controller.request.UserCreateRequest;
import by.itacademy.domain.SystemRoles;
import by.itacademy.domain.User;
import by.itacademy.domain.UserRole;
import by.itacademy.exception.ControllerException;
import by.itacademy.exception.ServiceException;
import by.itacademy.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Log4j
@RestController
@RequestMapping("/rest/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAllUsers() throws ControllerException {

        try {
            return userService.findAll();
        } catch (ServiceException e) {
            log.error("Can't find any users.");
            throw new ControllerException("Can't find any users." + e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User findUserById(@PathVariable Long userId) throws ControllerException {

        try {
            return userService.findById(userId);
        } catch (ServiceException e) {
            log.error("Can't find an user.");
            throw new ControllerException("Can't find an user." + e.getMessage());
        }
    }

    @PostMapping
    @ApiOperation(value = "Endpoint for creation users")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string")})

    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody UserCreateRequest userCreateRequest) throws ControllerException {

        String login = userCreateRequest.getLogin();
        String password = userCreateRequest.getPassword();

        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            throw new ControllerException("User login or password can't be null or empty.");
        }

        User userToSave = new User();
        userToSave.setName(userCreateRequest.getName());
        userToSave.setSurname(userCreateRequest.getSurname());
        userToSave.setLogin(userCreateRequest.getLogin());
        userToSave.setPassword(userCreateRequest.getPassword());
        userToSave.setCreated(new Timestamp(System.currentTimeMillis()));
        userToSave.setChanged(new Timestamp(System.currentTimeMillis()));
        userToSave.setRole(new UserRole(SystemRoles.ROLE_USER, userToSave));

        try {
            return userService.save(userToSave);
        } catch (ServiceException e) {
            log.error("Can't save an user.");
            throw new ControllerException("Can't save an user." + e.getMessage());
        }

    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable Long userId,
                           @RequestBody UserCreateRequest userCreateRequest) throws ControllerException {

        String login = userCreateRequest.getLogin();
        String password = userCreateRequest.getLogin();

        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            throw new ControllerException("User login or password can't be null or empty.");
        }

        try {
            User foundUser = userService.findById(userId);
            foundUser.setName(userCreateRequest.getName());
            foundUser.setSurname(userCreateRequest.getSurname());
            foundUser.setLogin(userCreateRequest.getLogin());
            foundUser.setPassword(userCreateRequest.getPassword());
            foundUser.setChanged(new Timestamp(System.currentTimeMillis()));
            return userService.update(foundUser);
        } catch (ServiceException e) {
            log.error("Can't find an user.");
            throw new ControllerException("Can't find an user." + e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User deleteUser(@PathVariable Long userId) throws ControllerException {

        try {
            return userService.delete(userId);
        } catch (ServiceException e) {
            log.error("Can't find an user.");
            throw new ControllerException("Can't find an user." + e.getMessage());
        }
    }
}

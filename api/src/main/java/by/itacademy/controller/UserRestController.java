package by.itacademy.controller;

import by.itacademy.controller.request.UserCreateRequest;
import by.itacademy.domain.SystemRoles;
import by.itacademy.domain.User;
import by.itacademy.domain.UserRole;
import by.itacademy.exception.ControllerException;
import by.itacademy.exception.RepositoryException;
import by.itacademy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Log4j
@RestController
@RequestMapping("/rest/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() throws ControllerException {
        try {
            log.info("Users exist");
            return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't find not existing users");
        }
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User findUserById(@PathVariable Long userId) throws ControllerException {
        try {
            User userToFindById = userRepository.findById(userId);
            log.info("User with id " + userId + " exists");
            return userToFindById;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't find a not existing user");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody UserCreateRequest userCreateRequest) throws ControllerException {
        try {
            User userToSave = new User();
            userToSave.setLogin(userCreateRequest.getLogin());
            userToSave.setPassword(userCreateRequest.getPassword());
            userToSave.setCreated(new Timestamp(System.currentTimeMillis()));
            userToSave.setChanged(new Timestamp(System.currentTimeMillis()));
            userToSave.setRole(new UserRole(SystemRoles.ROLE_USER, userToSave));

            log.info("User " + userToSave + " was saved");
            return userRepository.save(userToSave);
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't save a not existing user");
        }
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable Long userId,
                           @RequestBody UserCreateRequest userCreateRequest) throws RepositoryException, ControllerException {

        User userToUpdate;
        try {
            userToUpdate = userRepository.findById(userId);
            log.info("User with id " + userId + " was updated");
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't update a not existing user");
        }

        userToUpdate.setLogin(userCreateRequest.getLogin());
        userToUpdate.setPassword(userCreateRequest.getPassword());
        userToUpdate.setChanged(new Timestamp(System.currentTimeMillis()));
        return userRepository.update(userToUpdate);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteUser(@PathVariable User userId) throws ControllerException {
        try {
            log.info("User with id " + userId + " was deleted");
            return userRepository.delete(userId);
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ControllerException("Can't delete a not existing user");
        }
    }
}

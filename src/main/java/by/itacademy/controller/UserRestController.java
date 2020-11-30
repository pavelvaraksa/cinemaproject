package by.itacademy.controller;

import by.itacademy.controller.request.UserCreateRequest;
import by.itacademy.domain.User;
import by.itacademy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/rest/users")
@RequiredArgsConstructor
public class UserRestController {

    public final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody UserCreateRequest userCreateRequest) {
        User user = new User();
        user.setLogin(userCreateRequest.getLogin());
        user.setPassword(userCreateRequest.getPassword());
        user.setRole(userCreateRequest.getRole());
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setChanged(new Timestamp(System.currentTimeMillis()));
        return userService.save(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable Long id,
                           @RequestBody UserCreateRequest userCreateRequest) {

        User user = userService.findById(id);

        user.setLogin(userCreateRequest.getLogin());
        user.setPassword(userCreateRequest.getPassword());
        user.setRole(userCreateRequest.getRole());
        user.setChanged(new Timestamp(System.currentTimeMillis()));
        return userService.update(user);
    }

    @DeleteMapping("/{user}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteUser(@PathVariable User user) {
        return userService.delete(user);
    }
}

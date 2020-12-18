package by.itacademy.controller.hibernate;

import by.itacademy.controller.request.UserCreateRequest;
import by.itacademy.controller.request.UserUpdateCreateRequest;
import by.itacademy.dao.hibernate.UserAppRepository;
import by.itacademy.domain.SystemRoles;
import by.itacademy.domain.hibernate.UserApp;
import by.itacademy.domain.hibernate.UserRoleApp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/rest/users/hibernate")
@RequiredArgsConstructor
public class UserAppController {

    private final UserAppRepository userAppRepository;

    @GetMapping
    public ResponseEntity<List<UserApp>> findAllUsers() {
        return new ResponseEntity<>(userAppRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserApp findUserById(@PathVariable Long id) {
        return userAppRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserApp saveUser(@RequestBody UserCreateRequest userCreateRequest) {
        UserApp user = new UserApp();
        user.setLogin(userCreateRequest.getLogin());
        user.setPassword(userCreateRequest.getPassword());
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setChanged(new Timestamp(System.currentTimeMillis()));
        user.setRoles(Collections.singleton(new UserRoleApp(SystemRoles.ROLE_USER, user)));

        return userAppRepository.save(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserApp updateUser(@PathVariable Long id,
                              @RequestBody UserUpdateCreateRequest userUpdateCreateRequest) {

        UserApp user = userAppRepository.findById(id);

        user.setLogin(userUpdateCreateRequest.getLogin());
        user.setPassword(userUpdateCreateRequest.getPassword());
        user.setRoles(Collections.singleton(new UserRoleApp(SystemRoles.ROLE_MANAGER, user)));
        user.setChanged(new Timestamp(System.currentTimeMillis()));
        return userAppRepository.update(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteUser(@PathVariable Long id) {
        return userAppRepository.delete(id);
    }
}

package by.itacademy.controller.hibernate;

import by.itacademy.controller.request.UserCreateRequest;
import by.itacademy.dao.hibernate.UserHibernateRepository;
import by.itacademy.domain.SystemRoles;
import by.itacademy.domain.hibernate.UserHibernate;
import by.itacademy.domain.hibernate.UserRoleHibernate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/rest/users/hibernate")
@RequiredArgsConstructor
public class UserHibernateController {

    private final UserHibernateRepository userHibernateRepository;

    @GetMapping
    public ResponseEntity<List<UserHibernate>> findAllUsers() {
        return new ResponseEntity<>(userHibernateRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserHibernate findUserById(@PathVariable Long id) {
        return userHibernateRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserHibernate saveUser(@RequestBody UserCreateRequest userCreateRequest) {
        UserHibernate user = new UserHibernate();
        user.setLogin(userCreateRequest.getLogin());
        user.setPassword(userCreateRequest.getPassword());
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setChanged(new Timestamp(System.currentTimeMillis()));
        user.setRole(new UserRoleHibernate(SystemRoles.ROLE_USER, user));

        return userHibernateRepository.save(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserHibernate updateUser(@PathVariable Long id,
                                    @RequestBody UserCreateRequest userCreateRequest) {

        UserHibernate user = userHibernateRepository.findById(id);

        user.setLogin(userCreateRequest.getLogin());
        user.setPassword(userCreateRequest.getPassword());
        user.setChanged(new Timestamp(System.currentTimeMillis()));
        return userHibernateRepository.update(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteUser(@PathVariable Long id) {
        return userHibernateRepository.delete(id);
    }
}

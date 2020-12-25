package by.itacademy.service;

import by.itacademy.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long userId);

    User save(User user);

    User update(User user);

    Long delete(User userId);
}

package by.itacademy.service;

import by.itacademy.domain.User;
import by.itacademy.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll() throws ServiceException;

    User findById(Long userId) throws ServiceException;

    User save(User user) throws ServiceException;

    User update(User user) throws ServiceException;

    User delete(Long userId) throws ServiceException;
}

package by.itacademy.service.impl;

import by.itacademy.domain.User;
import by.itacademy.exception.RepositoryException;
import by.itacademy.exception.ServiceException;
import by.itacademy.repository.UserRepository;
import by.itacademy.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() throws ServiceException {

        List<User> existingUsers;

        try {
            existingUsers = userRepository.findAll();
            if (existingUsers.isEmpty()) {
                String errorMessage = "The list is empty.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            } else {
                log.info("Users exist.");
                return existingUsers;
            }
        } catch (RepositoryException e) {
            throw new ServiceException("User service exception while trying to find all users." + e.getMessage());
        }
    }

    @Override
    public User findById(Long userId) throws ServiceException {

        User userToFindById;

        try {
            userToFindById = userRepository.findById(userId);
            if (userToFindById == null) {
                String errorMessage = "User id can't be null.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException e) {
            throw new ServiceException("User service exception while trying to find an user." + e.getMessage());
        }

        try {
            log.info("User with id " + userId + " exists.");
            return userRepository.findById(userId);
        } catch (RepositoryException e) {
            String errorMessage = "Can't find an user.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public User save(User user) throws ServiceException {

        List<User> existingUsers;

        try {
            existingUsers = userRepository.findAll();
        } catch (RepositoryException e) {
            String errorMessage = "Can't get all users.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }

        for (User existingUser : existingUsers) {
            boolean hasSameUser = existingUser.getLogin().equals(user.getLogin());

            if (hasSameUser) {
                String errorMessage = "User with login " + user.getLogin() + " already exists.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        }

        try {
            User savedUser = userRepository.save(user);
            log.info("User with login " + user + " was saved.");
            return savedUser;
        } catch (RepositoryException e) {
            throw new ServiceException("User service exception while trying to save user:" + e.getMessage());
        }
    }

    @Override
    public User update(User user) throws ServiceException {

        try {
            log.info("User with login " + user + " was updated.");
            return userRepository.update(user);
        } catch (RepositoryException e) {
            String errorMessage = "Can't get an user.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public User delete(Long userId) throws ServiceException {

        User userToFindById;

        try {
            userToFindById = userRepository.findById(userId);
            if (userToFindById == null) {
                String errorMessage = "User id can't be null.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException e) {
            throw new ServiceException("User service exception while trying to delete an user." + e.getMessage());
        }

        try {
            log.info("User with id " + userId + " was deleted.");
            return userRepository.delete(userId);
        } catch (RepositoryException e) {
            String errorMessage = "Can't find an user.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
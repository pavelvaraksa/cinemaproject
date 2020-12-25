package by.itacademy.service.impl;

import by.itacademy.domain.User;
import by.itacademy.exception.RepositoryException;
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
    public List<User> findAll() {
        try {
            List<User> usersToFind = userRepository.findAll();
            if (usersToFind != null) {
                log.info("Users " + usersToFind + " exist");
                return usersToFind;
            }
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public User findById(Long userId) {
        try {
            User userToFindById = userRepository.findById(userId);
            if (userToFindById != null) {
                log.info("User with id " + userId + " exists");
                return userToFindById;
            }
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public User save(User user) {
        try {
            User userToSave = userRepository.save(user);
            log.info("User " + user + " was saved");
            return userToSave;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public User update(User user) {
        try {
            User userToUpdate = userRepository.update(user);
            log.info("User " + user + " was updated");
            return userToUpdate;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Long delete(User userId) {
        try {
            Long userToDelete = userRepository.delete(userId);
            log.info("User with id " + userId + " was deleted");
            return userToDelete;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
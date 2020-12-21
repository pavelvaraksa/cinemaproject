package by.itacademy.service.impl;

import by.itacademy.domain.User;
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
            log.info("Users " + usersToFind + " are exist");
            return usersToFind;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public User findById(Long userId) {
        try {
            User userToFindById = userRepository.findById(userId);
            log.info("User " + userId + " is exist");
            return userToFindById;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public User save(User user) {
        try {
            User userToSave = userRepository.save(user);
            log.info("User " + user + " saved");
            return userToSave;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public User update(User user) {
        try {
            User userToUpdate = userRepository.update(user);
            log.info("User " + user + " updated");
            return userToUpdate;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Long delete(Long userId) {
        try {
            Long userToDelete = userRepository.delete(userId);
            log.info("User " + userId + " deleted");
            return userToDelete;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
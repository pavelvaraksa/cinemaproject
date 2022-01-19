package by.itacademy.repository;

import by.itacademy.domain.User;
import by.itacademy.exception.RepositoryException;

public interface UserRepository extends CrudRepository<Long, User> {

    User findByLogin(String login) throws RepositoryException;
}

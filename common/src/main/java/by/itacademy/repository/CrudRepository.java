package by.itacademy.repository;

import by.itacademy.exception.RepositoryException;

import java.util.List;

public interface CrudRepository<K, V> {

    List<V> findAll() throws RepositoryException;

    V findById(K object) throws RepositoryException;

    V save(V object) throws RepositoryException;

    V update(V object) throws RepositoryException;

    K delete(K object) throws RepositoryException;
}
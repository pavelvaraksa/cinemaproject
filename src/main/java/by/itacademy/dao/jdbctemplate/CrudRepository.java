package by.itacademy.dao.jdbctemplate;

import java.util.List;

public interface CrudRepository<K, V> {

    List<V> findAll();

    V findById(K key);

    V save(V object);

    V update(V object);

    K delete(K object);
}

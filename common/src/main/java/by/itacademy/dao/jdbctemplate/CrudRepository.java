package by.itacademy.dao.jdbctemplate;

import java.util.List;

public interface CrudRepository<K, V> {

    List<V> findAll();

    V findById(K object);

    V save(V object);

    V update(V object);

    K delete(K object);
}

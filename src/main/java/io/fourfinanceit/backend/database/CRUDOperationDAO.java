package io.fourfinanceit.backend.database;


import java.util.List;
import java.util.Optional;

public interface CRUDOperationDAO <E, K> {
    void create(E entity);

    Optional<E> getById(K key);

    void update(E entity);

    void delete(E entity);

    Optional<List<E>> getAll();
}

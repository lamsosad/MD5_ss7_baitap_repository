package lam.ss7.model.service;

import java.util.Optional;

public interface IService<T>{
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);

    void remove(Long id);
}
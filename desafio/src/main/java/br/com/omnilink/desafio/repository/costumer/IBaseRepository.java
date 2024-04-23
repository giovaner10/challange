package br.com.omnilink.desafio.repository.costumer;

import java.util.List;

public interface IBaseRepository<T> {

    T findById(Integer id);

    List<T> findAll();

    void save(T entity);

    void update(T entity);

    void delete(T entity);
}

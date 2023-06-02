package com.cagatayiba.coursePlatform.dao;


import java.util.List;
import java.util.Optional;

public interface CrudDao<T, K>{
    List<T> getAll();
    Optional<T> get(K id);
    boolean delete(K id);
    boolean update(T object, K id);
    boolean create(T object);
}

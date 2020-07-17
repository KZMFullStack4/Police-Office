package com.moshka.dao;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface GeneralRepository<T,PK extends Serializable> {

    void save(T model);

    int saveAndGetLastId(T model);

    void remove(T model);

    T getById(PK id);

    List<T> getAll();

    void delete(PK id);


}

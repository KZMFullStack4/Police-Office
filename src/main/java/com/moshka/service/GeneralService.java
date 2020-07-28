package com.moshka.service;

import java.io.Serializable;
import java.util.List;

public interface GeneralService <T,PK extends Serializable>{

    void save(T model);

    int saveAndGetLastId(T model);

    T getById(PK id);

    void remove(T model);

    List<T> getAll();

    void delete(PK id);

}

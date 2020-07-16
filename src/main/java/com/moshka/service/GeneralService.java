package com.moshka.service;

import com.moshka.model.DossierModel;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GeneralService <T,PK extends Serializable>{

    void save(T model);

    int saveAndGetLastId(T model);

    T getById(PK id);

    void remove(T model);

    List<T> getAll();

    void delete(PK id);

}

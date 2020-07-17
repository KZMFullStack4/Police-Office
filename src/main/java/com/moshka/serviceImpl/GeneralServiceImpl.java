package com.moshka.serviceImpl;

import com.moshka.dao.GeneralRepository;
import com.moshka.dao.GeneralRepositoryImpl;
import com.moshka.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;



public class GeneralServiceImpl<T, PK extends Serializable> implements GeneralService<T, PK>{

    @Autowired
    protected GeneralRepository<T, PK> generalRepository;

    public GeneralServiceImpl() {
    }

    public GeneralServiceImpl(GeneralRepository<T, PK> generalRepository) {
        this.generalRepository = generalRepository;

    }


//    private GeneralRepositoryImpl<T,PK> generalRepository;

//    public GeneralServiceImpl() {
//    }
//
//    @Autowired
//    public GeneralServiceImpl(GeneralRepositoryImpl<T,PK> generalRepository){
//        this.generalRepository=generalRepository;
//    }

    @Override
    public void save(T model) {
        generalRepository.save(model);
    }

    @Override
    public int saveAndGetLastId(T model) {
       return  generalRepository.saveAndGetLastId(model);
    }

    @Override
    public T getById(PK id) {
        return generalRepository.getById(id);
    }

    @Override
    public void remove(T model) {
        generalRepository.remove(model);
    }

    @Override
    public List<T> getAll() {
        return generalRepository.getAll();
    }

    @Override
    public void delete(PK id) {
        generalRepository.delete(id);
    }
}

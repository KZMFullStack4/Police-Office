package com.moshka.dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional(propagation = Propagation.NESTED)
public class GeneralRepositoryImpl<T,PK extends  Serializable> implements GeneralRepository<T,PK> {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    @Qualifier("sessionFactory")
    public SessionFactory sessionFactory;

    private final Class<T> persistentClass;

    public GeneralRepositoryImpl(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    @Override
    public void save(T model) {
        getCurrentSession().saveOrUpdate(model);
    }

    @Override
    public int saveAndGetLastId(T model) {
        return (int) getCurrentSession().save(model);
    }

    @Override
    public void remove(T model) {
        getCurrentSession().remove(model);
    }

    @Override
    public T getById(PK id) {
        return getCurrentSession().get(persistentClass,id);
    }

    @Override
    public List<T> getAll() {
       return  getCurrentSession().createQuery("from " + persistentClass.getName()).list();
    }

    @Override
    public void delete(PK id) {
        getCurrentSession().delete(id);
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}

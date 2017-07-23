package com.artsoft.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractDao<PK extends Serializable, T> {
    private final Class persistanceClass;

    public AbstractDao() {
        this.persistanceClass = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return (T) getSession().get(persistanceClass, key);
    }

    protected Criteria createCriteria() {
        return getSession().createCriteria(persistanceClass);
    }
    public void delete(T entity) {
        getSession().delete(entity);
    }
    public void persist(T entity) {
        getSession().persist(entity);
    }
}

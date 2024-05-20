package com.io.dropwizardhibernate.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class RepositoryImpl<T, ID extends Serializable> extends AbstractDAO<T> {

    public RepositoryImpl(SessionFactory factory) {
        super(factory);
    }

    /**
     * Get current hibernate session
     * @return
     */
    public Session getCurrentSession() {
        return currentSession();
    }

    /**
     * get by id
     * @param id
     * @return
     */
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(get(id));
    }

    public T saveOrUpdate(T entity) {
        return persist(entity);
    }

    // TODO: batch save
    public void save(List<T> enitities) {
        int cnt = 0;
        for (T entity: enitities) {
            if (cnt == 50 || cnt % 50 == 0) {
                persist(entity);
                currentSession().flush();
                currentSession().clear();
            }
            cnt++;
        }
    }

    /**
     * delete by entity
     * @param entity
     */
    public void delete(T entity) {
        currentSession().delete(entity);
    }

    /**
     * delete entity by id
     * @param id
     */
    public void deleteById(ID id) {
        Optional<T> o = findById(id);
        if (!o.isPresent()) {
            throw new IllegalArgumentException(
                    String.format("Object:%s with id: %s does not exist", this.getClass().getSimpleName(), id));
        }
        delete(o.get());
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.services;

import com.sos.fso.cdoc.insc.exceptions.ValidationException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author abdel
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        if(entity == null)
            throw new ValidationException(entity.getClass().getSimpleName() + " object is null !!");
        
        getEntityManager().persist(entity);
        getEntityManager().getEntityManagerFactory().getCache().evictAll();
    }

    public void edit(T entity) {
        if(entity == null)
            throw new ValidationException(entity.getClass().getSimpleName() + " object is null !!");
        
        getEntityManager().merge(entity);
        getEntityManager().getEntityManagerFactory().getCache().evictAll();
    }

    public void remove(T entity) {
        if(entity == null)
            throw new ValidationException(entity.getClass().getSimpleName() + " object is null !!");
                
        getEntityManager().remove(getEntityManager().merge(entity));
        getEntityManager().getEntityManagerFactory().getCache().evictAll();
    }

    public T find(Object id) {
        if(id == null)
            throw new ValidationException(id.getClass().getSimpleName() + "id is invalid !!");
        
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}

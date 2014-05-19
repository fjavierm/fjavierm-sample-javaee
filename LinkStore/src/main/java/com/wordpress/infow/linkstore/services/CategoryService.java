package com.wordpress.infow.linkstore.services;

import com.wordpress.infow.linkstore.constants.Result;
import com.wordpress.infow.linkstore.entities.Categories;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

@Stateless
public class CategoryService {

    @PersistenceContext
    private EntityManager em;
    
    public Integer create(Categories category) {
        this.em.persist(category);
        
        return category.getId();
    }
    
    public List<Categories> findAll(Integer startPosition, Integer maxResult) {
        TypedQuery<Categories> typedQuery = this.em.createNamedQuery(Categories.FIND_ALL, Categories.class);
        
        if (startPosition != null) {
            typedQuery.setFirstResult(startPosition);
        }
        
        if (maxResult != null) {
            typedQuery.setMaxResults(maxResult);
        }
        
        return typedQuery.getResultList();
    }
    
    public Categories findById(@NotNull Integer id) {
        TypedQuery<Categories> typedQuery = this.em.createNamedQuery(Categories.FIND_BY_ID, Categories.class);
        typedQuery.setParameter("id", id);
        
        return typedQuery.getSingleResult();
    }
    
    public List<Categories> findByName(@NotNull String name, Integer startPosition, Integer maxResult) {
        TypedQuery<Categories> typedQuery = this.em.createNamedQuery(Categories.FIND_BY_NAME, Categories.class);
        typedQuery.setParameter("name", name);
        
        if (startPosition != null) {
            typedQuery.setFirstResult(startPosition);
        }
        
        if (maxResult != null) {
            typedQuery.setMaxResults(maxResult);
        }
        
        return typedQuery.getResultList();
    }
    
    public Categories update(@NotNull Categories category) {
        return this.em.merge(category);
    }
    
    public int remove(@NotNull Integer id) {
        Categories category = this.em.find(Categories.class, id);
        
        if (category == null) {
            return Result.FAIL;
        }
        
        this.em.remove(category);
        
        return Result.OK;
    }
}

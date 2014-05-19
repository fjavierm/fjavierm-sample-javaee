package com.wordpress.infow.linkstore.services;

import com.wordpress.infow.linkstore.constants.Result;
import com.wordpress.infow.linkstore.entities.Links;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

@Stateless
public class LinkService {
    
    @PersistenceContext
    private EntityManager em;
    
    public Integer create(Links link) {
        this.em.persist(link);
        
        return link.getId();
    }
    
    public List<Links> findAll(Integer startPosition, Integer maxResult) {
        TypedQuery<Links> typedQuery = this.em.createNamedQuery(Links.FIND_ALL, Links.class);
        
        if (startPosition != null) {
            typedQuery.setFirstResult(startPosition);
        }
        
        if (maxResult != null) {
            typedQuery.setMaxResults(maxResult);
        }
        
        return typedQuery.getResultList();
    }
    
    public Links findById(@NotNull Integer id) {
        TypedQuery<Links> typedQuery = this.em.createNamedQuery(Links.FIND_BY_ID, Links.class);
        typedQuery.setParameter("id", id);
        
        return typedQuery.getSingleResult();
    }
    
    public List<Links> findByName(@NotNull String name, Integer startPosition, Integer maxResult) {
        TypedQuery<Links> typedQuery = this.em.createNamedQuery(Links.FIND_BY_NAME, Links.class);
        typedQuery.setParameter("name", name);
        
        if (startPosition != null) {
            typedQuery.setFirstResult(startPosition);
        }
        
        if (maxResult != null) {
            typedQuery.setMaxResults(maxResult);
        }
        
        return typedQuery.getResultList();
    }
    
    public List<Links> findByURL(@NotNull String url, Integer startPosition, Integer maxResult) {
        TypedQuery<Links> typedQuery = this.em.createNamedQuery(Links.FIND_BY_URL, Links.class);
        typedQuery.setParameter("name", url);
        
        if (startPosition != null) {
            typedQuery.setFirstResult(startPosition);
        }
        
        if (maxResult != null) {
            typedQuery.setMaxResults(maxResult);
        }
        
        return typedQuery.getResultList();
    }
    
    public Links update(@NotNull Links link) {
        return this.em.merge(link);
    }
    
    public int remove(@NotNull Integer id) {
        Links link = this.em.find(Links.class, id);
        
        if (link == null) {
            return Result.FAIL;
        }
        
        this.em.remove(link);
        
        return Result.OK;
    }
}

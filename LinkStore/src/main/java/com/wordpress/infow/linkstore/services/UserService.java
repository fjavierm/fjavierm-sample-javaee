package com.wordpress.infow.linkstore.services;

import com.wordpress.infow.linkstore.constants.Result;
import com.wordpress.infow.linkstore.entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

@Stateless
public class UserService {

    @PersistenceContext
    private EntityManager em;
    
    public Integer create(@NotNull Users user) {
        this.em.persist(user);
        
        return user.getId();
    }
    
    public List<Users> findAll(Integer startPosition, Integer maxResult) {
        TypedQuery<Users> typedQuery = this.em.createNamedQuery(Users.FIND_ALL, Users.class);
        
        if (startPosition != null) {
            typedQuery.setFirstResult(startPosition);
        }
        
        if (maxResult != null) {
            typedQuery.setMaxResults(maxResult);
        }
        
        return typedQuery.getResultList();
    }
    
    public Users findById(@NotNull Integer id) {
        TypedQuery<Users> typedQuery = this.em.createNamedQuery(Users.FIND_BY_ID, Users.class);
        typedQuery.setParameter("id", id);
        
        return typedQuery.getSingleResult();
    }
    
    public Users findByName(@NotNull String name) {
        TypedQuery<Users> typedQuery = this.em.createNamedQuery(Users.FIND_BY_NAME, Users.class);
        typedQuery.setParameter("name", name);
        
        return typedQuery.getSingleResult();
    }
    
    public Users update(@NotNull Users user) {
        return this.em.merge(user);
    }
    
    public int remove(@NotNull Integer id) {
        Users user = this.em.find(Users.class, id);
        
        if (user == null) {
            return Result.FAIL;
        }
        
        this.em.remove(user);
        
        return Result.OK;
    }
}

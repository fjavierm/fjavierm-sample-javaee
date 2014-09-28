package com.wordpress.infow.car.renting.service;

import com.wordpress.infow.car.renting.entity.User;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class UserService implements Serializable {
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private OrderService orderService;
    
    public User create(User user) {
        return this.em.merge(user);
    }
    
    public User findById(long id) {
        return this.em.find(User.class, id);
    }
    
    public List<User> findAll() {
        TypedQuery<User> query = this.em.createNamedQuery(User.FIND_ALL, User.class);
        
        return query.getResultList();
    }
    
    public List<User> findByEmail(String email) {
        TypedQuery<User> query = this.em.createNamedQuery(User.FIND_BY_EMAIL, User.class);
        query.setParameter("email", email);
        
        return query.getResultList();
    }
    
    public List<User> findByUsername(String username) {
        TypedQuery<User> query = this.em.createNamedQuery(User.FIND_BY_EMAIL, User.class);
        query.setParameter("username", "%" + username + "%");
        
        return query.getResultList();
    }
    
    public void update(User user) {
        this.em.merge(user);
    }
    
    public void remove(long id) {
        User user = this.findById(id);
        
        if (!user.getOrders().isEmpty()) {
            user.getOrders().stream().forEach((o) -> {
                this.orderService.remove(o.getId());
            });
        }
        
        this.em.remove(user);
    }
}

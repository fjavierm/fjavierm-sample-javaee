package com.wordpress.infow.car.renting.service;

import com.wordpress.infow.car.renting.entity.Order;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OrderService implements Serializable {
    
    @PersistenceContext
    private EntityManager em;
    
    public Order create(Order order) {
        return this.em.merge(order);
    }
    
    public Order findById(long id) {
        return this.em.find(Order.class, id);
    }
    
    public void update(Order order) {
        this.em.merge(order);
    }
    
    public void remove(long id) {
        this.em.remove(this.findById(id));
    }
}

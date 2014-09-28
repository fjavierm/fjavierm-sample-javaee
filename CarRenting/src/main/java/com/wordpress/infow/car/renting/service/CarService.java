package com.wordpress.infow.car.renting.service;

import com.wordpress.infow.car.renting.entity.Car;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CarService implements Serializable {
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private OrderService orderService;
    
    public Car create(Car car) {
        return this.em.merge(car);
    }
    
    public Car findById(long id) {
        return this.em.find(Car.class, id);
    }
    
    public List<Car> findAll() {
        TypedQuery<Car> query = this.em.createNamedQuery(Car.FIND_ALL, Car.class);
        
        return query.getResultList();
    }
    
    public List<Car> findByBrand(String brand) {
        TypedQuery<Car> query = this.em.createNamedQuery(Car.FIND_BY_BRAND, Car.class);
        query.setParameter("brand", brand);
        
        return query.getResultList();
    }
    
    public List<Car> findByModel(String model) {
        TypedQuery<Car> query = this.em.createNamedQuery(Car.FIND_BY_MODEL, Car.class);
        query.setParameter("model", model);
        
        return query.getResultList();
    }
    
    public List<Car> findByPlate(String plate) {
        TypedQuery<Car> query = this.em.createNamedQuery(Car.FIND_BY_PLATE, Car.class);
        query.setParameter("plate", plate);
        
        return query.getResultList();
    }
    
    public void update(Car car) {
        this.em.merge(car);
    }
    
    public void remove(long id) {
        Car car = this.findById(id);
        
        if (!car.getOrders().isEmpty()) {
            car.getOrders().stream().forEach((o) -> {
                this.orderService.remove(o.getId());
            });
        }
        
        this.em.remove(car);
    }
}

package com.wordpress.infow.car.renting.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
    @NamedQuery(name = Car.FIND_ALL, query = "select c from car c"),
    @NamedQuery(name = Car.FIND_BY_BRAND, query = "select c from car c where brand = :brand"),
    @NamedQuery(name = Car.FIND_BY_MODEL, query = "select c from car c where model = :model"),
    @NamedQuery(name = Car.FIND_BY_PLATE, query = "select c from car c where plate = :plate")
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Car implements Serializable {
    
    public static final String FIND_ALL = "Car.findAll";
    public static final String FIND_BY_BRAND = "Car.findByBrand";
    public static final String FIND_BY_MODEL = "Car.findByModel";
    public static final String FIND_BY_PLATE = "Car.findByPlate";
    
    @Id
    @GeneratedValue
    private long id;
    
    private String brand;
    
    private String model;
    
    private String plate;
    
    @OneToMany(mappedBy = "car")
    private List<Order> orders;

    public Car() {
    }

    public Car(String brand, String model, String plate) {
        this.brand = brand;
        this.model = model;
        this.plate = plate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    
}

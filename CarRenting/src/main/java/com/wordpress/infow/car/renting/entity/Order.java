package com.wordpress.infow.car.renting.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
    
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Order implements Serializable {
    
    @Id
    @GeneratedValue
    private long id;
    
    private Double price;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User tenant;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Car car;

    public Order() {
    }

    public Order(Double price, Date startDate, Date endDate, User tenant, Car car) {
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tenant = tenant;
        this.car = car;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public User getTenant() {
        return tenant;
    }

    public void setTenant(User tenant) {
        this.tenant = tenant;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
    
}

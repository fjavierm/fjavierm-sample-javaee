package com.wordpress.infow.car.renting.entity;

import com.wordpress.infow.car.renting.validate.Email;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
    @NamedQuery(name = User.FIND_ALL, query = "select u from user u"),
    @NamedQuery(name = User.FIND_BY_EMAIL, query = "select u from user u where email = :email"),
    @NamedQuery(name = User.FIND_BY_USERNAME, query = "select u from user u where username like :username")
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {
    
    public static final String FIND_ALL = "User.findAll";
    public static final String FIND_BY_EMAIL = "User.findByEmail";
    public static final String FIND_BY_USERNAME = "User.findByUserName";
    
    @Id
    @GeneratedValue
    private long id;
    
    @NotNull
    @Size(min = 6, max = 30)
    private String username;
    
    @NotNull
    @Size(min = 8, max = 30)
    private String password;
    
    @NotNull
    @Email
    private String email;
    
    @OneToMany(mappedBy = "tenant")
    private List<Order> orders;

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    
}

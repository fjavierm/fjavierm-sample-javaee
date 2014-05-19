package com.wordpress.infow.linkstore.rest;

import com.wordpress.infow.linkstore.entities.Users;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(Users.class)
public class UsersBind extends ArrayList<Users> {

    public UsersBind() {
        super();
    }

    public UsersBind(Collection<? extends Users> c) {
        super(c);
    }

    @XmlElement(name = "user")
    public List<Users> getUsers() {
        return this;
    }

    public void setUsers(List<Users> users) {
        this.addAll(users);
    }
}

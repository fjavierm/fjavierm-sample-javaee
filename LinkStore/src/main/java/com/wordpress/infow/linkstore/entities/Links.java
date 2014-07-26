package com.wordpress.infow.linkstore.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "links")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = Links.FIND_ALL, query = "SELECT l FROM Links l"),
    @NamedQuery(name = Links.FIND_BY_ID, query = "SELECT l FROM Links l WHERE l.id = :id"),
    @NamedQuery(name = Links.FIND_BY_NAME, query = "SELECT l FROM Links l WHERE l.name = :name"),
    @NamedQuery(name = Links.FIND_BY_URL, query = "SELECT l FROM Links l WHERE l.url = :url")})
public class Links implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final String FIND_ALL = "Links.findAll";
    public static final String FIND_BY_ID = "Links.findById";
    public static final String FIND_BY_NAME = "Links.findByName";
    public static final String FIND_BY_URL = "Links.findByUrl";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "url")
    private String url;
    @JoinColumn(name = "category", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Categories category;

    public Links() {
    }

    public Links(Integer id) {
        this.id = id;
    }

    public Links(Integer id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Links)) {
            return false;
        }
        Links other = (Links) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.example.letsee.linkstore.Links[ id=" + id + " ]";
    }
    
}

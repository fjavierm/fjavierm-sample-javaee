package com.wordpress.infow.linkstore.rest;

import com.wordpress.infow.linkstore.entities.Categories;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(Categories.class)
public class CategoryBind extends ArrayList<Categories> {

    public CategoryBind() {
        super();
    }

    public CategoryBind(Collection<? extends Categories> c) {
        super(c);
    }

    @XmlElement(name = "category")
    public List<Categories> getCategories() {
        return this;
    }

    public void setCategories(List<Categories> categories) {
        this.addAll(categories);
    }
}

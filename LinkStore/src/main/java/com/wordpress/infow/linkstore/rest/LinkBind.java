package com.wordpress.infow.linkstore.rest;

import com.wordpress.infow.linkstore.entities.Links;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(Links.class)
public class LinkBind extends ArrayList<Links>{

    public LinkBind() {
        super();
    }

    public LinkBind(Collection<? extends Links> c) {
        super(c);
    }

    @XmlElement(name = "link")
    public List<Links> getLinks() {
        return this;
    }

    public void setLinks(List<Links> links) {
        this.addAll(links);
    }
}

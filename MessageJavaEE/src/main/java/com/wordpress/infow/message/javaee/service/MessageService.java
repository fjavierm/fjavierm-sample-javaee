/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wordpress.infow.message.javaee.service;

import com.wordpress.infow.message.javaee.entity.Message;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class MessageService {
    
    @PersistenceContext
    private EntityManager em;

    public Message create(Message message) {
        return this.em.merge(message);
    }
    
    public Message findById(Long id) {
        return this.em.find(Message.class, id);
    }
    
    public List<Message> findAll() {
        TypedQuery<Message> query = this.em.createNamedQuery(Message.FIND_ALL, Message.class);
        
        return query.getResultList();
    }
    
    public Message update(Message message) {
        return this.em.merge(message);
    }
    
    public void remove(Long id) {
        this.em.remove(this.findById(id));
    }
}

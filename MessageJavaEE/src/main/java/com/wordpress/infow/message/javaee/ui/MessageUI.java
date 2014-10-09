/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wordpress.infow.message.javaee.ui;

import com.wordpress.infow.message.javaee.entity.Message;
import com.wordpress.infow.message.javaee.service.MessageService;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class MessageUI implements Serializable {

    @Inject
    private MessageService messageService;
    
    @Inject
    private Message message;
    
    public MessageUI() {
    }

    public String save() {
        this.messageService.create(message);
        
        return "list";
    }
    
    public String goBack() {
        this.message.setMessage("");
        
        return "index";
    }
    
    public List<Message> getAll() {
        return this.messageService.findAll();
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
    
}

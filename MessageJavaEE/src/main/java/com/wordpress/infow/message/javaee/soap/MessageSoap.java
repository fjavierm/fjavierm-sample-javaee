package com.wordpress.infow.message.javaee.soap;

import com.wordpress.infow.message.javaee.entity.Message;
import com.wordpress.infow.message.javaee.service.MessageService;
import java.util.List;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class MessageSoap {
    
    @Inject
    private MessageService messageService;
    
    @WebMethod
    public List<Message> findAll() {
        return this.messageService.findAll();
    }
}

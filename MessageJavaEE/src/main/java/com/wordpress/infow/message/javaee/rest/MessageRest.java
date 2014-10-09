package com.wordpress.infow.message.javaee.rest;

import com.wordpress.infow.message.javaee.entity.Message;
import com.wordpress.infow.message.javaee.service.MessageService;
import java.net.URI;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/messages")
@Stateless
public class MessageRest {
    
    @Context
    private UriInfo uriInfo;
    
    @Inject
    private MessageService messageService;
    
    @GET
    public Response findAll() {
        List<Message> kks = this.messageService.findAll();
        
        GenericEntity<List<Message>> entity = new GenericEntity<List<Message>>(kks) {};
        
        return Response.ok(entity).build();
    }
    
    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") Long id) {
        Message msg = this.messageService.findById(id);
        
        return Response.ok(msg).build();
    }
    
    @POST
    public Response create(Message msg) {
        this.messageService.create(msg);
        
        URI uri = this.uriInfo.getAbsolutePathBuilder().path(String.valueOf(msg.getId())).build();
        
        return Response.created(uri).build();
    }
    
    @PUT
    public Response update(Message msg) {
        this.messageService.update(msg);
        
        URI uri = this.uriInfo.getAbsolutePathBuilder().path(String.valueOf(msg.getId())).build();
        
        return Response.ok(uri).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        this.messageService.remove(id);
        
        return Response.noContent().build();
    }
}

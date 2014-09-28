package com.wordpress.infow.car.renting.rest;

import com.wordpress.infow.car.renting.entity.User;
import com.wordpress.infow.car.renting.service.UserService;
import java.io.Serializable;
import java.net.URI;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserRest implements Serializable {
    
    @Context
    private UriInfo uriInfo;
    
    @Inject
    private UserService userService;
    
    @GET
    public Response findAll() {
        List<User> users = this.userService.findAll();
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        
        return Response.ok(entity).build();
    }
    
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") long id) {
        User user = this.userService.findById(id);
        
        return Response.ok(user).build();
    }
    
    @GET
    @Path("/{username}")
    public Response findByUsername(@PathParam("username") String usermane) {
        List<User> users = this.userService.findByUsername(usermane);
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        
        return Response.ok(entity).build();
    }
    
    @POST
    public Response create(User user) {
        this.userService.create(user);
        
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(user.getId())).build();
        
        return Response.created(uri).build();
    }
    
    @PUT
    public Response update(User user) {
        this.userService.update(user);
        
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(user.getId())).build();
        
        return Response.ok(uri).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") long id) {
        this.userService.remove(id);
        
        return Response.noContent().build();
    }
}

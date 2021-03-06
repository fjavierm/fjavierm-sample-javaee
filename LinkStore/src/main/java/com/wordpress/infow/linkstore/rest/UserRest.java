package com.wordpress.infow.linkstore.rest;

import com.wordpress.infow.linkstore.constants.Result;
import com.wordpress.infow.linkstore.entities.Users;
import com.wordpress.infow.linkstore.services.UserService;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserRest {

    @EJB
    private UserService userService;

    @POST
    public Response createUser(Users user) {
        if (user == null) {
            throw new BadRequestException();
        }

        this.userService.create(user);

        return Response.created(UriBuilder.fromResource(UserRest.class).path(String.valueOf(user.getId())).build()).build();
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public Response deleteById(@PathParam("id") Integer id) {
        int result = this.userService.remove(id);
        if (result == Result.FAIL) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.noContent().build();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    public Response findById(@PathParam("id") Integer id) {
        Users user = this.userService.findById(id);

        if (user == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        
        return Response.ok(user).build();
    }

    @GET
    public Response listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult) {
        List<Users> users = this.userService.findAll(startPosition, maxResult);

        if (users == null) {
            throw new WebApplicationException("No users recovered.");
        }
        
        GenericEntity entity = new GenericEntity<List<Users>>(users){};

        return Response.ok(entity).build();
    }

    @PUT
    @Path("/{id:[0-9][0-9]*}")
    public Response update(Users user) {
        this.userService.update(user);
        
        return Response.noContent().build();
    }
}

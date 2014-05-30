package com.wordpress.infow.linkstore.rest;

import com.wordpress.infow.linkstore.constants.Result;
import com.wordpress.infow.linkstore.entities.Links;
import com.wordpress.infow.linkstore.services.LinkService;
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

@Path("/link")
public class LinkRest {
    
    @EJB
    private LinkService linkService;

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createUser(Links link) {
        if (link == null) {
            throw new BadRequestException();
        }

        this.linkService.create(link);

        return Response.created(UriBuilder.fromResource(CategoryRest.class).path(String.valueOf(link.getId())).build()).build();
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public Response deleteById(@PathParam("id") Integer id) {
        int result = this.linkService.remove(id);
        if (result == Result.FAIL) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.noContent().build();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findById(@PathParam("id") Integer id) {
        Links link = this.linkService.findById(id);

        if (link == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        
        return Response.ok(link).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult) {
        List<Links> links = this.linkService.findAll(startPosition, maxResult);

        if (links == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        
        GenericEntity entity = new GenericEntity<List<Links>>(links){};

        return Response.ok(entity).build();
    }

    @PUT
    @Path("/{id:[0-9][0-9]*}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response update(Links link) {
        this.linkService.update(link);

        return Response.noContent().build();
    }
}

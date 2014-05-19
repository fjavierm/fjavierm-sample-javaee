package com.wordpress.infow.linkstore.rest;

import com.wordpress.infow.linkstore.constants.Result;
import com.wordpress.infow.linkstore.entities.Categories;
import com.wordpress.infow.linkstore.services.CategoryService;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

@Path("/category")
public class CategoryRest {

    @EJB
    private CategoryService categoryService;

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createUser(Categories category) {
        if (category == null) {
            throw new BadRequestException();
        }

        this.categoryService.create(category);

        return Response.created(UriBuilder.fromResource(CategoryRest.class).path(String.valueOf(category.getId())).build()).build();
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public Response deleteById(@PathParam("id") Integer id) {
        int result = this.categoryService.remove(id);
        if (result == Result.FAIL) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.noContent().build();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findById(@PathParam("id") Integer id) {
        Categories category = this.categoryService.findById(id);

        if (category == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(category).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult) {
        List<Categories> categories = this.categoryService.findAll(startPosition, maxResult);

        if (categories == null) {
            return Response.status(Status.NOT_FOUND).build();
        } else {
            CategoryBind categoryBind = new CategoryBind(categories);

            return Response.ok(categoryBind).build();
        }
    }

    @PUT
    @Path("/{id:[0-9][0-9]*}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response update(Categories category) {
        this.categoryService.update(category);

        return Response.noContent().build();
    }
}
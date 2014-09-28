package com.wordpress.infow.car.renting.rest;

import com.wordpress.infow.car.renting.entity.Car;
import com.wordpress.infow.car.renting.service.CarService;
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

@Path("/cars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarRest implements Serializable {
    
    @Context
    private UriInfo uriInfo;
    
    @Inject
    private CarService carService;
    
    @GET
    public Response findAll() {
        List<Car> cars = this.carService.findAll();
        GenericEntity<List<Car>> entity = new GenericEntity<List<Car>>(cars) {};
        
        return Response.ok(entity).build();
    }
    
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") long id) {
        Car car = this.carService.findById(id);
        
        return Response.ok(car).build();
    }
    
    @GET
    @Path("/{plate}")
    public Response findByPlate(@PathParam("plate") String plate) {
        List<Car> cars = this.carService.findByPlate(plate);
        GenericEntity<List<Car>> entity = new GenericEntity<List<Car>>(cars) {};
        
        return Response.ok(entity).build();
    }
    
    @POST
    public Response create(Car car) {
        this.carService.create(car);
        
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(car.getId())).build();
        
        return Response.created(uri).build();
    }
    
    @PUT
    public Response update(Car car) {
        this.carService.update(car);
        
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(car.getId())).build();
        
        return Response.ok(uri).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") long id) {
        this.carService.remove(id);
        
        return Response.noContent().build();
    }
}

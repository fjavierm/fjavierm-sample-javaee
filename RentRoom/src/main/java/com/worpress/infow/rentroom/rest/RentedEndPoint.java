package com.worpress.infow.rentroom.rest;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.worpress.infow.rentroom.model.Rented;
import com.worpress.infow.rentroom.services.RentedService;
import com.worpress.infow.rentroom.services.RoomService;
import com.worpress.infow.rentroom.services.UserService;

@Path ("/rents")
public class RentedEndPoint implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private RentedService rentedService;

	@EJB
	private UserService userService;

	@EJB
	private RoomService roomService;

	@Context
	private UriInfo uriInfo;

	@GET
	@Produces ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Rented> findAllRented() {
		return this.rentedService.findAllRented();
	}

	@GET
	@Path ("/{userId}")
	@Produces ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Rented> findRentedByUser(@PathParam ("userId") Integer userId) {
		return this.rentedService.findByUser(this.userService.findById(userId));
	}

	@POST
	@Consumes ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response createRented(JAXBElement<Rented> xmlRented) {
		Rented rented = this.rentedService.createRented(xmlRented.getValue());
		URI uri = this.uriInfo.getAbsolutePathBuilder().path(String.valueOf(rented.getId())).build();

		return Response.created(uri).build();
	}

	@PUT
	@Consumes ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response updateRented(JAXBElement<Rented> xmlRented) {
		Rented rented = this.rentedService.updateRented(xmlRented.getValue());
		URI uri = this.uriInfo.getAbsolutePathBuilder().path(String.valueOf(rented.getId())).build();

		return Response.ok(uri).build();
	}

	@DELETE
	@Path ("/{id}")
	public Response removeUser(@PathParam ("id") Integer id) {
		this.rentedService.removeRented(this.rentedService.findById(id));

		return Response.noContent().build();
	}
}

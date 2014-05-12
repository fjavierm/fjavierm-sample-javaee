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

import com.worpress.infow.rentroom.model.User;
import com.worpress.infow.rentroom.services.UserService;

@Path ("/user")
public class UserEndPoint implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserService userService;

	@Context
	private UriInfo uriInfo;

	@GET
	@Path ("/users")
	@Produces ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<User> findAllUsers() {
		return this.userService.findAllUsers();
	}

	@GET
	@Path ("/user/{login}")
	@Produces ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public User findUserByName(@PathParam ("login") String login) {
		return this.userService.findByLogin(login);
	}

	@POST
	@Path ("/user")
	@Consumes ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response createUser(JAXBElement<User> xmlUser) {
		User user = this.userService.createUser(xmlUser.getValue());
		URI uri = this.uriInfo.getAbsolutePathBuilder().path(String.valueOf(user.getId())).build();

		return Response.created(uri).build();
	}

	@PUT
	@Path ("/user")
	@Consumes ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response updateUser(JAXBElement<User> xmlUser) {
		User user = this.userService.updateUser(xmlUser.getValue());
		URI uri = this.uriInfo.getAbsolutePathBuilder().path(String.valueOf(user.getId())).build();

		return Response.ok(uri).build();
	}

	@DELETE
	@Path ("/user/{login}")
	public Response removeUser(@PathParam ("login") String login) {
		this.userService.removeUser(this.userService.findByLogin(login));

		return Response.noContent().build();
	}
}

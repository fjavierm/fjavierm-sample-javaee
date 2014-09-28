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

import com.worpress.infow.rentroom.model.Room;
import com.worpress.infow.rentroom.services.RoomService;

@Path ("/rooms")
public class RoomEndPoint implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private RoomService roomService;

	@Context
	private UriInfo uriInfo;

	@GET
	@Produces ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Room> findAllRoom() {
		return this.roomService.findAllRooms();
	}

	@GET
	@Path ("/{title}")
	@Produces ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Room> findRoomByTittle(@PathParam ("tittle") String tittle) {
		return this.roomService.findByTittle(tittle);
	}

	@POST
	@Consumes ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response createRoom(JAXBElement<Room> xmlRoom) {
		Room room = this.roomService.createRoom(xmlRoom.getValue());
		URI uri = this.uriInfo.getAbsolutePathBuilder().path(String.valueOf(room.getId())).build();

		return Response.created(uri).build();
	}

	@PUT
	@Consumes ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response updateRoom(JAXBElement<Room> xmlRoom) {
		Room room = this.roomService.updateRoom(xmlRoom.getValue());
		URI uri = this.uriInfo.getAbsolutePathBuilder().path(String.valueOf(room.getId())).build();

		return Response.ok(uri).build();
	}

	@DELETE
	@Path ("/{id}")
	public Response removeUser(@PathParam ("id") Integer id) {
		this.roomService.removeRoom(this.roomService.findById(id));

		return Response.noContent().build();
	}
}

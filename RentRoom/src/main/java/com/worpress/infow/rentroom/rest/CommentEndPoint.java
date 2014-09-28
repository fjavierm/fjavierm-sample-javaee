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

import com.worpress.infow.rentroom.model.Comment;
import com.worpress.infow.rentroom.services.CommentService;
import com.worpress.infow.rentroom.services.RoomService;
import com.worpress.infow.rentroom.services.UserService;

@Path ("/comments")
public class CommentEndPoint implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private CommentService commentService;

	@EJB
	private UserService userService;

	@EJB
	private RoomService roomService;

	@Context
	private UriInfo uriInfo;

	@GET
	@Produces ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Comment> findAllComments() {
		return this.commentService.findAllComments();
	}

	@GET
	@Path ("/{userId}")
	@Produces ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Comment> findCommentByUser(@PathParam ("userId") Integer userId) {
		return this.commentService.findByUser(this.userService.findById(userId));
	}

	@POST
	@Consumes ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response createComment(JAXBElement<Comment> xmlComment) {
		Comment comment = this.commentService.createComment(xmlComment.getValue());
		URI uri = this.uriInfo.getAbsolutePathBuilder().path(String.valueOf(comment.getId())).build();

		return Response.created(uri).build();
	}

	@PUT
	@Consumes ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response updateComment(JAXBElement<Comment> xmlComment) {
		Comment comment = this.commentService.updateComment(xmlComment.getValue());
		URI uri = this.uriInfo.getAbsolutePathBuilder().path(String.valueOf(comment.getId())).build();

		return Response.ok(uri).build();
	}

	@DELETE
	@Path ("/{id}")
	public Response removeUser(@PathParam ("id") Integer id) {
		this.commentService.removeComment(this.commentService.findById(id));

		return Response.noContent().build();
	}
}

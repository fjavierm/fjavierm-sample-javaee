package com.wordpress.infow.todoforge.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import com.wordpress.infow.todoforge.entities.User;

/**
 * 
 */
@Stateless
@Path ("/users")
public class UserEndpoint
{

	@PersistenceContext (unitName = "ToDoapp-forge-persistence-unit")
	private EntityManager em;

	@POST
	@Consumes ("application/xml")
	public Response create(User entity)
	{
		this.em.persist(entity);
		return Response.created(UriBuilder.fromResource(UserEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
	}

	@DELETE
	@Path ("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam ("id") Long id)
	{
		User entity = this.em.find(User.class, id);
		if (entity == null)
		{
			return Response.status(Status.NOT_FOUND).build();
		}
		this.em.remove(entity);
		return Response.noContent().build();
	}

	@GET
	@Path ("/{id:[0-9][0-9]*}")
	@Produces ("application/xml")
	public Response findById(@PathParam ("id") Long id)
	{
		TypedQuery<User> findByIdQuery = this.em.createQuery(
				"SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.tasks WHERE u.id = :entityId ORDER BY u.id", User.class);
		findByIdQuery.setParameter("entityId", id);
		User entity;
		try
		{
			entity = findByIdQuery.getSingleResult();
		} catch (NoResultException nre)
		{
			entity = null;
		}
		if (entity == null)
		{
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(entity).build();
	}

	@GET
	@Produces ("application/xml")
	public List<User> listAll(@QueryParam ("start") Integer startPosition, @QueryParam ("max") Integer maxResult)
	{
		TypedQuery<User> findAllQuery = this.em.createQuery("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.tasks ORDER BY u.id",
				User.class);
		if (startPosition != null)
		{
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null)
		{
			findAllQuery.setMaxResults(maxResult);
		}
		final List<User> results = findAllQuery.getResultList();
		return results;
	}

	@PUT
	@Path ("/{id:[0-9][0-9]*}")
	@Consumes ("application/xml")
	public Response update(User entity)
	{
		entity = this.em.merge(entity);
		return Response.noContent().build();
	}
}
package com.wordpress.infow.todoforge.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import com.wordpress.infow.todoforge.entities.Task;

/**
 * 
 */
@Stateless
@Path("/tasks")
public class TaskEndpoint
{
   @PersistenceContext(unitName = "ToDoapp-forge-persistence-unit")
   private EntityManager em;

   @POST
   @Consumes("application/xml")
   public Response create(Task entity)
   {
      em.persist(entity);
      return Response.created(UriBuilder.fromResource(TaskEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") Long id)
   {
      Task entity = em.find(Task.class, id);
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      em.remove(entity);
      return Response.noContent().build();
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/xml")
   public Response findById(@PathParam("id") Long id)
   {
      TypedQuery<Task> findByIdQuery = em.createQuery("SELECT DISTINCT t FROM Task t WHERE t.id = :entityId ORDER BY t.id", Task.class);
      findByIdQuery.setParameter("entityId", id);
      Task entity;
      try
      {
         entity = findByIdQuery.getSingleResult();
      }
      catch (NoResultException nre)
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
   @Produces("application/xml")
   public List<Task> listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
   {
      TypedQuery<Task> findAllQuery = em.createQuery("SELECT DISTINCT t FROM Task t ORDER BY t.id", Task.class);
      if (startPosition != null)
      {
         findAllQuery.setFirstResult(startPosition);
      }
      if (maxResult != null)
      {
         findAllQuery.setMaxResults(maxResult);
      }
      final List<Task> results = findAllQuery.getResultList();
      return results;
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes("application/xml")
   public Response update(Task entity)
   {
      entity = em.merge(entity);
      return Response.noContent().build();
   }
}
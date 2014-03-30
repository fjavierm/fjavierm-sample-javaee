package com.wordpress.infow.quotewall.rest;

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

import com.wordpress.infow.quotewall.model.Quote;
import com.wordpress.infow.quotewall.services.QuoteService;

@Path ("/quotes")
public class QuoteEndPoint implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private QuoteService quoteService;

	@Context
	private UriInfo uriInfo;

	@GET
	@Produces ({ MediaType.APPLICATION_JSON })
	public List<Quote> findAllQuotesSorted() {
		return this.quoteService.findAllQuotesSorted();
	}

	@GET
	@Path ("/{author}")
	@Produces ({ MediaType.APPLICATION_JSON })
	public List<Quote> findUserByAuthor(@PathParam ("author") String author) {
		return this.quoteService.findByAuthor(author);
	}

	@POST
	@Consumes ({ MediaType.APPLICATION_JSON })
	public Response createQuote(JAXBElement<Quote> xmlQuote) {
		Quote quote = this.quoteService.createQuote(xmlQuote.getValue());
		URI uri = this.uriInfo.getAbsolutePathBuilder().path(String.valueOf(quote.getId())).build();

		return Response.created(uri).build();
	}

	@PUT
	@Consumes ({ MediaType.APPLICATION_JSON })
	public Response updateQuote(JAXBElement<Quote> xmlQuote) {
		Quote quote = this.quoteService.updateQuote(xmlQuote.getValue());
		URI uri = this.uriInfo.getAbsolutePathBuilder().path(String.valueOf(quote.getId())).build();

		return Response.ok(uri).build();
	}

	@DELETE
	@Path ("/{id}")
	public Response removeQuote(@PathParam ("id") int id) {
		this.quoteService.removeQuote(this.quoteService.findQuote(id));

		return Response.noContent().build();
	}
}

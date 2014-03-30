package com.wordpress.infow.quotewallnosql.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.wordpress.infow.quotewallnosql.model.Quote;
import com.wordpress.infow.quotewallnosql.services.QuoteService;

@WebService
public class QuoteWS {

	@EJB
	private QuoteService quoteService;

	@WebMethod
	public List<Quote> findAllQuotesSorted() {
		return this.quoteService.findAllQuotesSorted();
	}

	@WebMethod
	public List<Quote> findUserByAuthor(String author) {
		return this.quoteService.findByAuthor(author);
	}

	@WebMethod
	public Quote createQuote(Quote quote) {
		return this.quoteService.createQuote(quote);
	}

	@WebMethod
	public Quote updateQuote(Quote quote) {
		return this.quoteService.updateQuote(quote);
	}

	@WebMethod
	public void removeQuote(int id) {
		this.quoteService.removeQuote(this.quoteService.findQuote(id));
	}
}

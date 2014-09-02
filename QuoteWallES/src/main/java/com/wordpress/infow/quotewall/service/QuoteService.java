package com.wordpress.infow.quotewall.service;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;

import com.wordpress.infow.quotewall.es.QuoteES;
import com.wordpress.infow.quotewall.es.QuoteResult;
import com.wordpress.infow.quotewall.model.Quote;

@Stateless
public class QuoteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	QuoteES es;

	public Quote createQuote(@NotNull Quote quote) {
		this.es.index(quote);

		return quote;
	}

	public Quote findQuote(@NotNull int quoteId) {
		return this.es.read(String.valueOf(quoteId));
	}

	public QuoteResult findAllQuotes(int start, int size) {
		return this.es.getAll(start, size);
	}

	public QuoteResult find(@NotNull String searchTerm, int start, int size) {
		return this.es.search(searchTerm, start, size);
	}

	public Quote updateQuote(@NotNull Quote quote) {
		this.es.update(quote);

		return quote;
	}

	public void removeQuote(@NotNull Quote quote) {
		this.es.delete(String.valueOf(quote.getId()));
	}
}

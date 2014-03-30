package com.wordpress.infow.quotewallnosql.services;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import com.wordpress.infow.quotewallnosql.model.Quote;

@Stateless
public class QuoteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	public Quote createQuote(@NotNull Quote quote) {
		this.em.persist(quote);

		return quote;
	}

	public Quote findQuote(@NotNull int quoteId) {
		return this.em.find(Quote.class, quoteId);
	}

	public List<Quote> findAllQuotes() {
		TypedQuery<Quote> typedQuery = this.em.createNamedQuery(Quote.FIND_ALL, Quote.class);

		return typedQuery.getResultList();
	}

	public List<Quote> findAllQuotesSorted() {
		TypedQuery<Quote> typedQuery = this.em.createNamedQuery(Quote.FIND_ALL_SORTED, Quote.class);

		return typedQuery.getResultList();
	}

	public List<Quote> findByAuthor(@NotNull String author) {
		TypedQuery<Quote> typedQuery = this.em.createNamedQuery(Quote.FIND_BY_AUTHOR, Quote.class);
		typedQuery.setParameter("author", "%" + author + "%");

		return typedQuery.getResultList();
	}

	public Quote updateQuote(@NotNull Quote quote) {
		this.em.merge(quote);

		return quote;
	}

	public void removeQuote(@NotNull Quote quote) {
		this.em.remove(quote);
	}
}

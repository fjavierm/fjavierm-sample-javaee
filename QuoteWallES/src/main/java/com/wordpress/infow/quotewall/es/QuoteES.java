package com.wordpress.infow.quotewall.es;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordpress.infow.quotewall.model.Quote;

@Stateless
public class QuoteES {

	private static final String INDEX_NAME = "quotes";
	private static final String INDEX_TYPE = "quote";

	public static final int DEFAULT_PAGINATION_START = 0;
	public static final int DEFAULT_PAGINATION_SIZE = 100;

	private ObjectMapper objectMapper;
	private Client client;

	public QuoteES() {
		this.client = new TransportClient().addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
		this.objectMapper = new ObjectMapper();
	}

	public boolean index(Quote quote) {
		boolean created = false;

		String json;
		try {
			json = this.objectMapper.writeValueAsString(quote);

			IndexResponse response = this.client
					.prepareIndex(QuoteES.INDEX_NAME, QuoteES.INDEX_TYPE, String.valueOf(quote.getId()))
					.setSource(json)
					.execute().actionGet();

			created = response.isCreated();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return created;
	}

	public Quote read(String id) {
		Quote quote = null;
		Client client = new TransportClient().addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
		GetResponse response = client
				.prepareGet(QuoteES.INDEX_NAME, QuoteES.INDEX_TYPE, id)
				.execute()
				.actionGet();

		if (response.getSourceAsBytes() != null) {
			try {
				quote = this.objectMapper.readValue(response.getSourceAsBytes(), Quote.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return quote;
	}

	public void update(Quote quote) {
		this.delete(String.valueOf(quote.getId()));

		this.index(quote);
	}

	public void delete(String id) {
		this.client.prepareDelete(QuoteES.INDEX_NAME, QuoteES.INDEX_TYPE, id).execute().actionGet();
	}

	public QuoteResult search(String searchTerm, int start, int size) {
		QuoteResult searchResult = null;
		List<Quote> quotes = null;
		long totalQuotes = 0;

		QueryBuilder queryBuilder = null;

		queryBuilder = QueryBuilders.matchQuery("_all", searchTerm);

		SearchRequestBuilder searchRequestBuilder = this.client.prepareSearch(QuoteES.INDEX_NAME);
		searchRequestBuilder.setTypes(QuoteES.INDEX_TYPE);
		searchRequestBuilder.setSearchType(SearchType.DEFAULT);
		searchRequestBuilder.setQuery(queryBuilder);

		if ((start > 0) && (size > 0)) { // Pagination
			searchRequestBuilder.setFrom(start).setSize(size);
		} else {
			searchRequestBuilder.setFrom(QuoteES.DEFAULT_PAGINATION_START).setSize(QuoteES.DEFAULT_PAGINATION_SIZE);
		}

		SearchResponse response = searchRequestBuilder.execute().actionGet();

		SearchHit[] results = response.getHits().getHits();

		if ((results != null) && (results.length > 0)) {
			searchResult = new QuoteResult();
			quotes = new ArrayList<>();
			totalQuotes = response.getHits().getTotalHits();

			for (SearchHit hit : results) {
				Quote quote = null;

				try {
					quote = this.objectMapper.readValue(hit.getSourceAsString(), Quote.class);
					quotes.add(quote);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			searchResult.setQuotes(quotes);
			searchResult.setTotalQuotes(totalQuotes);
		}

		return searchResult;
	}

	public QuoteResult getAll(int start, int size) {
		QuoteResult searchResult = null;
		List<Quote> quotes = null;
		long totalQuotes = 0;

		QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();

		SearchRequestBuilder searchRequestBuilder = this.client.prepareSearch(QuoteES.INDEX_NAME);
		searchRequestBuilder.setTypes(QuoteES.INDEX_TYPE);
		searchRequestBuilder.setSearchType(SearchType.DEFAULT);
		searchRequestBuilder.setQuery(queryBuilder);

		if ((start > 0) && (size > 0)) { // Pagination
			searchRequestBuilder.setFrom(start).setSize(size);
		} else {
			searchRequestBuilder.setFrom(QuoteES.DEFAULT_PAGINATION_START).setSize(QuoteES.DEFAULT_PAGINATION_SIZE);
		}

		SearchResponse response = searchRequestBuilder.execute().actionGet();

		SearchHit[] results = response.getHits().getHits();

		if ((results != null) && (results.length > 0)) {
			searchResult = new QuoteResult();
			quotes = new ArrayList<>();
			totalQuotes = response.getHits().getTotalHits();

			for (SearchHit hit : results) {
				Quote quote = null;

				try {
					quote = this.objectMapper.readValue(hit.getSourceAsString(), Quote.class);
					quotes.add(quote);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			searchResult.setQuotes(quotes);
			searchResult.setTotalQuotes(totalQuotes);
		}

		return searchResult;
	}
}

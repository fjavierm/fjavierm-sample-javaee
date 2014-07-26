package com.wordpress.infow.elasticsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.wordpress.infow.elasticsearch.model.Article;

public class ElasticSearch {

	public static final String INDEX_NAME = "myindex"; // Must be lowercase
	public static final String TYPE_NAME = "article";
	public static final String SERVER = "localhost";
	public static final int PORT = 9300;

	public static ObjectMapper objectMapper = new ObjectMapper();

	// Store the object in ElasticSearch
	public static void indexArticle(Article article) throws Exception {
		Client client = new TransportClient().addTransportAddress(new InetSocketTransportAddress(ElasticSearch.SERVER, ElasticSearch.PORT));
		String json = ElasticSearch.objectMapper.writeValueAsString(article);

		client.prepareIndex(ElasticSearch.INDEX_NAME, ElasticSearch.TYPE_NAME, article.getId()).setSource(json).execute().actionGet();

		client.close();
	}

	// Recover the element using the ID from ElasticSearch
	public static Article getArticleById(String id) throws Exception {
		Client client = new TransportClient().addTransportAddress(new InetSocketTransportAddress(ElasticSearch.SERVER, ElasticSearch.PORT));
		GetResponse response = client.prepareGet(ElasticSearch.INDEX_NAME, ElasticSearch.TYPE_NAME, id).execute().actionGet();

		Article article = null;

		if (response.getSourceAsBytes() != null) {
			article = ElasticSearch.objectMapper.readValue(response.getSourceAsBytes(), Article.class);
		}

		client.close();

		return article;
	}

	// Delete the element from the index
	public static void deleteArticle(String id) {
		Client client = new TransportClient().addTransportAddress(new InetSocketTransportAddress(ElasticSearch.SERVER, ElasticSearch.PORT));

		client.prepareDelete(ElasticSearch.INDEX_NAME, ElasticSearch.TYPE_NAME, id).execute().actionGet();

		client.close();
	}

	// Update the element in ElasticSearch
	public static void updateArticle(Article article) throws Exception {
		ElasticSearch.deleteArticle(article.getId());

		ElasticSearch.indexArticle(article);
	}

	public static List<Article> searchArticles(String searchTerm) throws Exception {
		return ElasticSearch.searchArticles(searchTerm, 0, 0);
	}

	public static List<Article> searchArticles(String searchTerm, int start, int size) throws Exception {
		List<Article> articles = new ArrayList<>();
		Client client = new TransportClient().addTransportAddress(new InetSocketTransportAddress(ElasticSearch.SERVER, ElasticSearch.PORT));

		QueryBuilder queryBuilder = QueryBuilders.matchQuery("_all", searchTerm);

		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(ElasticSearch.INDEX_NAME);
		searchRequestBuilder.setTypes(ElasticSearch.TYPE_NAME);
		searchRequestBuilder.setSearchType(SearchType.DEFAULT);
		searchRequestBuilder.setQuery(queryBuilder);

		if ((start > 0) && (size > 0)) {
			searchRequestBuilder.setFrom(start).setSize(size); // Pagination
		}

		SearchResponse response = searchRequestBuilder.execute().actionGet();

		SearchHit[] results = response.getHits().getHits();

		for (SearchHit hit : results) {
			Map<String, Object> result = hit.getSource();
			Article article = new Article();
			BeanUtils.populate(article, result);

			articles.add(article);
		}

		client.close();

		return articles;

	}
}

package com.wordpress.infow.elasticsearch;

import java.util.List;
import java.util.UUID;

import com.wordpress.infow.elasticsearch.model.Article;

public class Main {

	public static void main(String[] args) throws Exception {
		UUID uuid = UUID.randomUUID();

		Article article = new Article();
		article.setId(uuid.toString());
		article.setCategory("Games");
		article.setContent("Integer eget massa sed augue pharetra tincidunt. Donec sed velit.");
		article.setAuthor("John Doe");

		// Index it
		ElasticSearch.indexArticle(article);

		UUID uuid1 = UUID.randomUUID();

		Article article1 = new Article();
		article1.setId(uuid1.toString());
		article1.setCategory("Life");
		article1.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque volutpat.");
		article1.setAuthor("Will Smith");

		// Index it
		ElasticSearch.indexArticle(article1);

		UUID uuid2 = UUID.randomUUID();

		Article article2 = new Article();
		article2.setId(uuid2.toString());
		article2.setCategory("Style");
		article2.setContent("Sed a vestibulum odio. Quisque eleifend neque et erat cursus.");
		article2.setAuthor("Lara Salt");

		// Index it
		ElasticSearch.indexArticle(article2);

		UUID uuid3 = UUID.randomUUID();

		Article article3 = new Article();
		article3.setId(uuid3.toString());
		article3.setCategory("Politics");
		article3.setContent("Aenean aliquet ullamcorper tempus. Etiam lacus elit, suscipit ut nibh.");
		article3.setAuthor("Jane Smith");

		// Index
		ElasticSearch.indexArticle(article3);

		// Search
		List<Article> articles = ElasticSearch.searchArticles("pharetra");

		for (Article a : articles) {
			System.out.println(a.toString());
		}

		// Recover by ID
		Article articleById = ElasticSearch.getArticleById(uuid.toString());
		if (articleById != null) {
			System.out.println(articleById.toString());
		} else {
			System.out.println("Element not found.");
		}

		// Update
		article.setAuthor(article.getAuthor() + " UpdatedName");
		ElasticSearch.updateArticle(article);

		Article articleUpdated = ElasticSearch.getArticleById(uuid1.toString());
		System.out.println(articleUpdated.toString());

		// Delete
		ElasticSearch.deleteArticle(article.getId());

		if (ElasticSearch.getArticleById(article.getId()) == null) {
			System.out.println("Element removed successfuly.");
		} else {
			System.out.println("ERROR: The element has not been removed.");
		}
	}
}

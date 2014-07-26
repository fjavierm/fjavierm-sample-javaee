package com.wordpress.infow.elasticsearch.model;

public class Article {

	private String id;
	private String name;
	private String author;
	private String category;
	private String content;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Article [id=" + this.id + ", name=" + this.name + ", author=" + this.author + ", category=" + this.category + ", content="
				+ this.content + "]";
	}

}

package com.wordpress.infow.javamongodb.db;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoConnection {

	private final static String SERVER = "localhost";
	private final static String DB = "messageDB";
	private final static String COLLECTION = "messageCollection";

	private static MongoConnection connection = null;

	private MongoClient client;
	private DB db;
	private DBCollection collection;

	private MongoConnection() throws UnknownHostException {
		this.client = new MongoClient(MongoConnection.SERVER, 27017);
		this.db = this.client.getDB(MongoConnection.DB);
		this.collection = this.db.getCollection(MongoConnection.COLLECTION);
	}

	public static MongoConnection getInstance() {
		if (MongoConnection.connection == null) {
			try {
				MongoConnection.connection = new MongoConnection();
			} catch (UnknownHostException e) {
				System.err.println("ERROR: Impossible to connect.");
			}
		}

		return MongoConnection.connection;
	}

	public DBCollection getCollection() {
		return this.collection;
	}

	public void setCollection(DBCollection collection) {
		this.collection = collection;
	}
}

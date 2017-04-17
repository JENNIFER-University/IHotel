package edu.jennifer.ipayment.model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class Database {

	public static final String DATABASE_NAME 	= "acme_transactions";
	public static final String COLLECTION_NAME 	= "transactions";


	private static Database instance;
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;


	public static Database getInstance() {
		if(instance == null){
			instance = new Database();
		}
		return instance;
	}

	private Database(){
		mongoClient = new MongoClient("192.168.0.194");
		mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);

	}

	public MongoDatabase getMongoDatabase() {
		return mongoDatabase;
	}
}

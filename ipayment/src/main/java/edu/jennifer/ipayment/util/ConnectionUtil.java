package edu.jennifer.ipayment.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.jennifer.ipayment.model.Transaction;
import org.bson.Document;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import static com.mongodb.client.model.Filters.eq;

public class ConnectionUtil {

	private final String DBNAME = "ipayment_trans";
	private final String COLLECTION = "transactions";
	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> collection;
	private static ConnectionUtil instance;



	public static ConnectionUtil getInstance() {
		if(instance == null){
			instance = new ConnectionUtil();
		}
		return instance;
	}

	private ConnectionUtil(){
		mongoClient = new MongoClient("localhost",27017);
		database = mongoClient.getDatabase(DBNAME);
		collection = database.getCollection(COLLECTION);
	}

	public void saveTransaction(Transaction t){
		collection = database.getCollection(COLLECTION);
		Document data = Document.parse(t.toJson());
		collection.insertOne(data);
	}

	public Transaction get(String reservationId){
		Transaction t = null;
		Document d = collection.find(eq("reservation_id",reservationId)).first();
		return t;
	}



	
}

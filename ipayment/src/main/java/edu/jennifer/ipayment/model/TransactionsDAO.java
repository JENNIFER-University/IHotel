package edu.jennifer.ipayment.model;


import com.google.gson.Gson;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


/**
 * @author khalid
 * @version Created: 09/03/2017.
 */
public class TransactionsDAO {

    /**
     * Save a transaction to the database
     * @param transaction Transaction object
     */
    public void save(Transaction transaction){
        MongoDatabase database = Database.getInstance().getMongoDatabase();
        database.getCollection(Database.COLLECTION_NAME).insertOne(transaction.toMongoDocument());



    }

    /**
     * Find a transaction by reservation id
     * @param reservationId Resevation id
     * @return Transaction object or null
     */
    public Transaction findByReservationId(String reservationId){

        Transaction result = null;
        for(Document document : Database.getInstance().getMongoDatabase().getCollection("transactions").find()) {
            Gson g = new Gson();
            Transaction transaction = g.fromJson(document.toJson(), Transaction.class);
            if(transaction.getReservation_id().equals(reservationId)) {
                result = transaction;
                break;
            }
        }
        return result;
    }
}

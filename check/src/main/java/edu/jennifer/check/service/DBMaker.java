package edu.jennifer.check.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Helper to check the database
 * Created by khalid on 4/17/17.
 */
public class DBMaker {


    /**
     * Check the database exists
     * @return boolean true if exists, false other wise
     */
    private boolean dbExists(){
        Connection connection = ConnectionManager.getInstance().getConnection();
        String query = "SELECT * FROM cards";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            statement.close();
            resultSet.close();
            return true;
        }catch (SQLException sql){
            return false;

        }
    }

    /**
     * Create the cards table
     */
    private void createTables(){
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("CREATE TABLE CARDS ");
        queryBuilder.append("(");
        queryBuilder.append("CARD_NUMBER VARCHAR(255) NOT NULL,");
        queryBuilder.append("CARD_HOLDER VARCHAR(255),");
        queryBuilder.append("CARD_TYPE VARCHAR(255),");
        queryBuilder.append("ADDED VARCHAR(255) NOT NULL");
        queryBuilder.append(" )");
        Connection connection = ConnectionManager.getInstance().getConnection();
        try{
            Statement statement = connection.createStatement();
            boolean result = statement.executeUpdate(queryBuilder.toString()) == 0 ? true : false;
            if(result) {
                System.out.println("Database Created");
            }else{
                System.out.println("Failed to create the database");
            }

        }catch (SQLException sql){
            System.out.printf("\nError Creating the database reason is: %s", sql.getMessage());
        }

    }

    /**
     * Check if the database exists. If not create it
     */
    public void checkDatabase() {
        System.out.println("Checking Database ... ");
        if(!dbExists()){
            System.out.println("Creating tables ... ");
            createTables();;
        }else{
            System.out.println("All Good.");
        }
    }
}

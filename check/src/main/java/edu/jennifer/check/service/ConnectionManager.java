package edu.jennifer.check.service;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by khalid on 06/04/2017.
 */
public class ConnectionManager {


    private static ConnectionManager instance;

    private ConnectionManager(){}

    public static ConnectionManager getInstance() {
        if(instance == null) {
            instance = new ConnectionManager(); //Lazy ^^
        }
        return instance;
    }

    public Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/ihotel");
        }catch (Exception ex){
            return null;
        }
    }


    public void close(Connection connection) {
        try{
            if(connection != null)
                connection.close();
        }catch (Exception ex){

        }
    }
}

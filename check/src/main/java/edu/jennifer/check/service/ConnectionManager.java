package edu.jennifer.check.service;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by khalid on 06/04/2017.
 */
public class ConnectionManager {

    private final String DRIVER     = "org.apache.derby.jdbc.EmbeddedDriver";
    private final String JDBC_URL   = "jdbc:derby:cards_db;create=true";


    private static ConnectionManager instance;

    private ConnectionManager(){}

    public static ConnectionManager getInstance() {
        if(instance == null) {
            System.setProperty("derby.system.home", "db");
            instance = new ConnectionManager(); //Lazy ^^

        }
        return instance;
    }

    public Connection getConnection(){
        try{
            Class.forName(DRIVER);
            return DriverManager.getConnection(JDBC_URL);
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

package edu.jennifer.hotel.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by khalid on 31/03/2017.
 */
public class ConnectionUtil {

    public static final String DATASOURCE_NAME = "jdbc/iHotelDS";

    private static ConnectionUtil instance;
    private DataSource dataSource;

    Logger logger = LogManager.getRootLogger();
    private ConnectionUtil(){
        if(dataSource == null) {
            lookup();
        }
    }

    private void lookup() {
        try{
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            dataSource = (DataSource) envCtx.lookup(ConnectionUtil.DATASOURCE_NAME);
            logger.info("DataSource Lookup Success");
        }catch (NamingException ex){
            logger.error(String.format("DataSource Lookup Failed. Reason: %s",ex.getMessage()));
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public static ConnectionUtil getInstance(){
        if(instance == null) {
            instance = new ConnectionUtil(); //Lazy :)
        }
        return  instance;
    }

    public boolean tablesAreOk() {
        try{
            DatabaseMetaData md = getDataSource().getConnection().getMetaData();
            ResultSet rs = md.getTables(null, null, "%", null);
            int tablesCount = 0;
            while (rs.next()) {
                tablesCount++;
            }
            return tablesCount >= 7;
        }catch (SQLException sql){
            return false;
        }


    }

}

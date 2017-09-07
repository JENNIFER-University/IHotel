package edu.jennifer.ihotel.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Created by khalid on 31/03/2017.
 */
public class ConnectionUtil {

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
            dataSource = (DataSource) envCtx.lookup("jdbc/iHotelDS");
            logger.info("DataSource Lookup Success");
        }catch (NamingException ex){
            logger.error("DataSource Lookup Failed");
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




}

package edu.jennifer.ihotel.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Created by khalid on 31/03/2017.
 */
public class ConnectionUtil {

    private static ConnectionUtil instance;
    private DataSource dataSource;

    private ConnectionUtil(){
        if(dataSource == null) {
            lookup();
        }
    }

    private void lookup() {
        try{
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            dataSource = (DataSource) envCtx.lookup("jdbc/ihotelDS");
        }catch (NamingException ex){
            System.out.printf("DataSource Lookup Failed");
            System.exit(1);
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

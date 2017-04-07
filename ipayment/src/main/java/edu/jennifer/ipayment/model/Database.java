package edu.jennifer.ipayment.model;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

	private static Database instance;
	private DataSource dataSource = null;

	public static Database getInstance() {
		if(instance == null){
			instance = new Database();
		}
		return instance;
	}

	private Database(){
		try{
			Context context = new InitialContext();
			context = (Context)context.lookup("java:/comp/env");
			dataSource = (DataSource) context.lookup("jdbc/ipaymentDS");
		}catch (Exception ex){
			//dont handle me :)
		}
	}

	public Connection getConnection() throws Exception{
		return dataSource.getConnection();
	}

	public Connection getConnectionDM() throws Exception {
		System.out.println("Direct");
		Class.forName("jdbc.GhostDriver");
		Connection connection = DriverManager.getConnection("jdbc:ghost://127.0.0.1", "ghost", "ghost");
		return connection;

	}

	public void disconnect(Connection connection){
		if(connection != null){
			try{
				connection.close();
			}catch (Exception e){}
		}
	}


	
}

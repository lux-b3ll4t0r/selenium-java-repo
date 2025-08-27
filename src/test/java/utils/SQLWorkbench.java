package utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLWorkbench {

	
	public static Connection connect(DBConnection connection) {
				
		Connection connectionInstance = null;
		
			try {
				LogUtil.trace("Connecting to: " + connection.getUrl());
				connectionInstance = DriverManager.getConnection(
						connection.getUrl(), 
						connection.getUsername(), 
						connection.getPassword());
			}catch(SQLException e) {
				LogUtil.warn("Connection to: " + connection.getUrl() + " may have failed.", e);
			}
	
			return connectionInstance;

	}
	
	public static void closeConnection(Connection con) {
		
		if(con == null) {
			LogUtil.warn("Attempted to close a null connection");
			return;
		}
		
		try {
			DatabaseMetaData metaData = con.getMetaData();
			LogUtil.trace("Closing connection : " + metaData.getURL());
			con.close();	
		}catch(SQLException e) {
			LogUtil.warn("Close operation returned SQL Exception - Unsure if connection is closed.");
		}
	}
	
}

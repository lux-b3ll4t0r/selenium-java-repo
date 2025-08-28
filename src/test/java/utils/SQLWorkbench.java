package utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import constants.QueryConstants;

public class SQLWorkbench {

	
	public static Connection connectToLocalDb() {
		
		String url = ConfigManager.getDbLocalUrl() + ConfigManager.getLocalDbName();
		String user = System.getenv("DB_USER");
		String pass = System.getenv("DB_PASS");
		Connection con = null;
		
		try {
			LogUtil.trace("Connecting to: " + url);
			con = DriverManager.getConnection(url, user, pass);
		}catch(SQLException sqle) {
			LogUtil.error("Unable to connect to: " + url + " -> ", sqle);
		}
		return con;
	}
	
	public static void saveUser(Connection con, User user) {
		LogUtil.trace("Saving user to DB.");
		try {
			PreparedStatement ps = con.prepareStatement(QueryConstants.getInsertUserData());
			
			ps.setString(1, user.getTitle());
			ps.setString(2, user.getFullName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setInt(5, user.getDay());
			ps.setInt(6, user.getMonth());
			ps.setInt(7, user.getYear());
			ps.setString(8, user.getNewsLetter());
			ps.setString(9, user.getOptin());
			ps.setString(10, user.getFirstName());
			ps.setString(11, user.getLastName());
			ps.setString(12, user.getCompany());
			ps.setString(13, user.getAddress1());
			ps.setString(14, user.getAddress2());
			ps.setString(15, user.getState());
			ps.setString(16, user.getCity());
			ps.setInt(17, user.getZipCode());
			ps.setString(18, user.getMobileNumber());
			ps.executeUpdate();
			ps.close();
			LogUtil.trace("User saved to DB.");
		}catch(SQLException | NullPointerException e) {
			LogUtil.error("Failed to save new user: ", e);
		}
	}
	
	public static Connection connect(DBConnection connection) {
				
		Connection connectionInstance = null;
		
			try {
				LogUtil.trace("Connecting to: " + connection.getUrl());
				connectionInstance = DriverManager.getConnection(
						connection.getUrl(), 
						connection.getUsername(), 
						connection.getPassword());
			}catch(SQLException e) {
				LogUtil.error("Connection to: " + connection.getUrl() + " may have failed.", e);
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
			LogUtil.trace("Connection closed");
		}catch(SQLException e) {
			LogUtil.warn("Close operation returned SQL Exception - Unsure if connection is closed.");
		}
	}
	
}

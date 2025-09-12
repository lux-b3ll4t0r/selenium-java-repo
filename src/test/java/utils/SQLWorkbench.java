package utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import constants.db.QueryConstants;

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
	
	public static Map<String, Object> getUser(Connection con, String username, String password) {
		
		Map<String, Object> userData = new HashMap<>();
		
		try (PreparedStatement ps = con.prepareStatement(QueryConstants.getUser())){
			ps.setString(1, username);
			ps.setString(2, password);
			try (ResultSet rs = ps.executeQuery()){
			
				if(rs.next()) {
				
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnCount = rsmd.getColumnCount();
					
					for(int i = 1; i <= columnCount; i++) {
						userData.put(rsmd.getColumnLabel(i), rs.getObject(i));
					}
				}else {
					LogUtil.warn("No results returned: ");
				}
			}
		}catch(Exception e){
			LogUtil.error("User not retrieved: ", e);
		}
		
		return userData;
	}
	
	public static void saveUser(Connection con, User user) {
		LogUtil.trace("Saving user to DB: " + user.getEmail());
		try {
			PreparedStatement ps = con.prepareStatement(QueryConstants.insertUser());
			
			ps.setObject(1, user.getTitle());
			ps.setObject(2, user.getName());
			ps.setObject(3, user.getEmail());
			ps.setObject(4, user.getPassword());
			ps.setObject(5, user.getBirthDay());
			ps.setObject(6, user.getBirthMonth());
			ps.setObject(7, user.getBirthYear());
			ps.setObject(8, user.getNewsLetter());
			ps.setObject(9, user.getOptin());
			ps.setObject(10, user.getFirstName());
			ps.setObject(11, user.getLastName());
			ps.setObject(12, user.getCompany());
			ps.setObject(13, user.getAddress1());
			ps.setObject(14, user.getAddress2());
			ps.setObject(15, user.getCountry());
			ps.setObject(16, user.getState());
			ps.setObject(17, user.getCity());
			ps.setObject(18, user.getZipcode());
			ps.setObject(19, user.getMobileNumber());
			ps.setObject(20, LocalDateTime.now().toString());
			ps.executeUpdate();
			ps.close();
			LogUtil.trace(user.getEmail() + " saved to DB.");
		}catch(SQLException | NullPointerException e) {
			LogUtil.error("Failed to save new user: ", e);
		}
	}
	
	public static void deleteUser(Connection con, User user) {
		LogUtil.trace("Deleting user from DB: " + user.getEmail());
		try {
			PreparedStatement ps = con.prepareStatement(QueryConstants.deleteUser());
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ps.executeUpdate();
			ps.close();
			LogUtil.trace(user.getEmail() + " deleted from DB");
		}catch(SQLException | NullPointerException e) {
			LogUtil.error("Failed to delete user: ", e);
		}
	}
	
	public static void updateUser(Connection con, User user) {
		StringBuilder stmnt = new StringBuilder("UPDATE users SET ");
		Map<String, Object> updateForm = user.getAsMap();
		
		int count = 0;
		
		for(String column : updateForm.keySet()) {
			if(count > 0) {
				stmnt.append(", ");
			}
			
			stmnt.append(column).append(" = ?");
			count++;
		}
		
		stmnt.append("WHERE email = ?");
		
		try (PreparedStatement ps = con.prepareStatement(stmnt.toString())){
			int index = 1;
			
			for(Object value : updateForm.values()) {
				ps.setObject(index++, value);
			}
			
			ps.setString(index, user.getEmail());
			ps.executeUpdate();
			ps.close();
		}catch(Exception e) {
			LogUtil.error("User not updated: ", e);
		}	
	}
	
	public static String getUserMobileNumber(Connection con, String email) {
		
		String number = "";
		
		try(PreparedStatement ps = con.prepareStatement(QueryConstants.getMobile())){
			
			ps.setString(1, email);
			ResultSet set = ps.executeQuery();
			
			if(set.next()) { number = set.getString(1); }
			
		}catch(Exception e) {
			
		}
		
		return number;
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
			LogUtil.error("Close operation returned SQL Exception - Unsure if connection is closed.", e);
		}
	}
	
}

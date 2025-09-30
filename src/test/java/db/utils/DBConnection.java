package db.utils;

import common.utils.ConfigManager;

public class DBConnection {
	
	private String name;
	private String url;
	private String username;
	private String password;

	
	public DBConnection(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public DBConnection(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public DBConnection(String name, String url, String username, String password) {
		this.name = name;
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public static DBConnection setupLocalConnection(String dbName) {
		
		return new DBConnection(ConfigManager.getDbLocalUrl() + dbName, System.getenv("DB_USERNAME"), System.getenv("DB_PASSWORD"));
		
	}
	
	public static DBConnection setupConnection(String url, String password, String username) {
		return new DBConnection(url, password, username);
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
}

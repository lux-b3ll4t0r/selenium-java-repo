package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigManager {
	
	public static void main(String[] args) {
		
	
	}

	private static Properties props = new Properties();
	
	static {
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
			props.load(fis);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static String get(String key) {
		return props.getProperty(key);
	}
	
	public static boolean isParallel() {
		return Boolean.parseBoolean(get("parallel"));
	}
	
	public static String getBrowser() {
		return get("browser");
	}
	
	public static String getBaseUrl() {
		return get("baseUrl");
	}
	
	public static String getEmail() {
		return get("email");
	}
	
	public static String getPassword() {
		return get("password");
	}
	
	public static String getLoginUrl() {
		return get("loginUrl");
	}
	
	public static boolean isHeadless() {
		return Boolean.parseBoolean(get("headless"));
	}

}

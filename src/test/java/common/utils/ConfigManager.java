package common.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigManager {
	
	/*
	 * 	For larger structured projects, ConfigManager can return new instances of Properties
	 *  Method would then require that properties instances.
	 * 	New instances can then call specific config files based on necessity.
	 */
	
	
	private static Properties props = new Properties();
	
	static {
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
			props.load(fis);
			fis.close();
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
		
	public static boolean isHeadless() {
		return Boolean.parseBoolean(get("headless"));
	}
	
	public static String getProductsUrl() {
		return get("productsUrl");
	}
	
	public static String getCartUrl() {
		return get("cartUrl");
	}
	
	public static String getLoginUrl() {
		return get("loginUrl");
	}
	
	public static String getTestCasesUrl() {
		return get("testCasesUrl");
	}
	
	public static String getApiTestingUrl() {
		return get("apiTestingUrl");
	}
	
	public static String getVideoTutorialsUrl() {
		return get("videoTutorialsUrl");
	}
	
	public static String getContactUsUrl() {
		return get("contactUsUrl");
	}
	
	public static String getPicsPath() {
		return get("picsPath");
	}
	
	public static String getDbLocalUrl() {
		return get("dbLocalUrl");
	}
	
	public static String getLocalDbName() {
		return get("localDbName");
	}
	
	public static String getReportPath() {
		return get("reportPath");
	}
	
	public static String getFullReportPath() {
		return System.getProperty("user.dir") + get("reportPath");
	}
	
	public static int getWaitDuration() {
		return Integer.valueOf(get("waitDuration"));
				
	}
	
	public static String getProductsSchema() {
		return get("productsSchema");
	}
	
	public static String getBrandsSchema() {
		return get("brandsSchema");
	}
	
	public static String getLoginSchema() {
		return get("loginSchema");
	}
	
	public static String getSheet() {
		return get("excelSheet");
	}
	
	public static String getUIPass() {
		return System.getenv("UI_PASS");
	}
}

package com.orangehrm.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesReader {

	public static String browserType;
	public static String appURL;
	public static String userName;
	public static String password;

	public static void getProperties() {

		try {
			String projectPath = System.getProperty("user.dir");

			Properties prop = new Properties();
			InputStream input = new FileInputStream(projectPath + "/configFiles/config.properties");
			prop.load(input);
			browserType = prop.getProperty("browserType");
			appURL = prop.getProperty("appURL");
			userName = prop.getProperty("userName");
			password = prop.getProperty("password");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void setProperties(String key, String value) {

		try {
			String projectPath = System.getProperty("user.dir");

			Properties prop = new Properties();
			OutputStream outtput = new FileOutputStream(projectPath + "/configFiles/config.properties");
			prop.put(key, value);
			prop.store(outtput, null);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

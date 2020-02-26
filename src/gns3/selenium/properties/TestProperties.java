package gns3.selenium.properties;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import gns3.selenium.webdriver.Main;

public class TestProperties {
	
	public static void readFile() {
		Properties prop = new Properties();
		try {
			InputStream input = new FileInputStream("/Users/aalif/Documents/GitHub/selenium-testing/src/gns3/selenium/properties/config.properties");
			prop.load(input);
			Main.browser = prop.getProperty("browser");
			Main.testEnv = prop.getProperty("testEnv");
			Main.adminName = prop.getProperty("adminName");
			Main.adminPass = prop.getProperty("adminPass");
			Main.userName = prop.getProperty("userName");
			Main.userPass = prop.getProperty("userPass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

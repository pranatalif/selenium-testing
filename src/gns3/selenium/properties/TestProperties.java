package gns3.selenium.properties;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import gns3.selenium.webdriver.Main;

public class TestProperties {
	
	public static void readFile() {
		String projectPath = System.getProperty("user.dir");
		System.out.println(projectPath);
		
		try {
			Properties prop = new Properties();
			InputStream input = new FileInputStream(projectPath + "/config.properties");
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

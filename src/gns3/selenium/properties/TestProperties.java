package gns3.selenium.properties;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import gns3.selenium.webdriver.Main;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestProperties {
	public static Properties prop = null;
	
	public static void readFile(String path) {
		try {
			prop = new Properties();
			InputStream input = new FileInputStream(path + "/config.properties");
			prop.load(input);
			Main.browser = prop.getProperty("browser");
			Main.browserMode = prop.getProperty("browserMode");
			Main.testEnv = prop.getProperty("testEnv");
			Main.scenario = prop.getProperty("scenario");
			Main.adminName = prop.getProperty("adminName");
			Main.adminPass = prop.getProperty("adminPass");
			Main.userName = prop.getProperty("userName");
			Main.userPass = prop.getProperty("userPass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public static void setBrowser() {
		if(Main.browser.equals("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			if(Main.browserMode.equals("GUI")) {
				Main.driver = new FirefoxDriver();
			} else if(Main.browserMode.equals("Headless")) {
				Main.driver = new FirefoxDriver(options.setHeadless(true));
			}
		} else if(Main.browser.equals("Chrome")){
			WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.driver", path + "/driver/chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--no-sandbox");
			options.setExperimentalOption("useAutomationExtension", false);
			options.addArguments("--headless");
			if(Main.browserMode.equals("GUI")) {
				Main.driver = new ChromeDriver();
			} else if(Main.browserMode.equals("Headless")) {
				Main.driver = new ChromeDriver(options);
			}	
		}
	}
	
	public static void setBrowserMode() {
		
	}*/
}

package gns3.selenium.webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hub {
	WebDriver driver;
	
	public void invokeBrowser() {
		try {
			System.setProperty("webdriver.chrome.driver", "/Users/aalif/Documents/GitHub/selenium-testing/driver/chromedriver");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			
			driver.get("http://localhost:8080");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void testKeycloak() {
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hub hub = new Hub();
		hub.invokeBrowser();
	}

}

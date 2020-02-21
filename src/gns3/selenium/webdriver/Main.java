package gns3.selenium.webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
	WebDriver driver;
	
	public void invokeBrowser() {
		try {
			System.setProperty("webdriver.chrome.driver", "/Users/aalif/Documents/GitHub/selenium-testing/driver/chromedriver");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			
			driver.get("http://localhost:3000/index.html");
			//testKeycloakAdmin();
			testHLS();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void testKeycloakAdmin() {
		try {
			driver.findElement(By.xpath("//a[@href='http://localhost:8080/auth/admin/']")).click();
			driver.findElement(By.id("username")).sendKeys("admin");
			driver.findElement(By.id("password")).sendKeys("admin");
			Thread.sleep(1000);
			driver.findElement(By.id("kc-login")).click();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void testHLS() {
		try {
			//driver.findElement(By.xpath("//a[@href='http://localhost:8080/auth/admin/']")).click();
			driver.findElement(By.id("username")).sendKeys("aalif");
			driver.findElement(By.id("password")).sendKeys("aalif");
			Thread.sleep(1000);
			driver.findElement(By.id("kc-login")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("my_video_1_html5_api")).click();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main test = new Main();
		test.invokeBrowser();
	}

}

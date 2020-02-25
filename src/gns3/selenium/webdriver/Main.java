package gns3.selenium.webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import gns3.selenium.properties.TestProperties;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.lang.Thread;

public class Main {
	static WebDriver driver;
	public static String browser;
	public static String testEnv;

	//public static String setBrowser() {
	//	return browser;
	//}

	//public static String setTestEnv() {
		//return testEnv;
	//}

	public static void invokeBrowser() {
		//System.setProperty("webdriver.chrome.driver", "/Users/aalif/Documents/GitHub/selenium-testing/driver/chromedriver");
		try {
			if(browser.contains("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions options = new FirefoxOptions();
				options.setHeadless(true);
				driver = new FirefoxDriver(options);
				//driver = new ChromeDriver();
			} else if(browser.contains("Chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				driver = new ChromeDriver(options);
				//driver = new ChromeDriver();
			} else {
				System.out.println("Unrecognized browser");
				System.exit(0);
			}
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void testKeycloakAdmin() {
		try {
			driver.get("http://localhost:8080");
			System.out.println(driver.getTitle());
			driver.findElement(By.xpath("//a[@href='http://localhost:8080/auth/admin/']")).click();
			driver.findElement(By.id("username")).sendKeys("admin");
			driver.findElement(By.id("password")).sendKeys("admin");
			Thread.sleep(1000);
			driver.findElement(By.id("kc-login")).click();
			Thread.sleep(3000);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("test completed");
		exitTesting();
	}

	public static void testHLS() {
		try {
			driver.get("http://localhost:3000/index.html");
			System.out.println(driver.getCurrentUrl());
			driver.findElement(By.id("username")).sendKeys("aalif");
			driver.findElement(By.id("password")).sendKeys("aalif");
			Thread.sleep(1000);
			driver.findElement(By.id("kc-login")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("my_video_1_html5_api")).click();
			Thread.sleep(3000);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("test completed");
	}

	public static void exitTesting() {
		driver.close();
		driver.quit();
	}

	public static void main(String[] args) {
		TestProperties.readFile();
		System.out.println("[INFO] Testing in " + browser + " browser");
		invokeBrowser();
		if(testEnv.contains("Keycloak")) {
			testKeycloakAdmin();
		}else if(testEnv.contains("HLS")) {
			testHLS();
		}
		exitTesting();
	}

}

package gns3.selenium.webdriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import gns3.selenium.properties.TestProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.lang.Thread;
import gns3.selenium.webpages.*;

public class Main {
	public static WebDriver driver = null;
	public static String browser = null;
	public static String testEnv = null;
	public static String adminName = null;
	public static String adminPass = null;
	public static String userName = null;
	public static String userPass = null;

	public static void invokeBrowser() {
		//System.out.println(browser + ", " + userName + " and " + userPass);
		try {
			if(browser.contains("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions options = new FirefoxOptions();
				options.setHeadless(true);
				driver = new FirefoxDriver(options);
			} else if(browser.contains("Chrome")) {
				WebDriverManager.chromedriver().setup();
				//ChromeOptions options = new ChromeOptions();
				//options.addArguments("--headless");
				//driver = new ChromeDriver(options);
				driver = new ChromeDriver();
			} else if(browser.contains("HtmlUnit")){
				driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_60);
				
				
			} else if(browser.contains("Phantomjs")){
				WebDriverManager.phantomjs().setup();
				driver = new PhantomJSDriver();
				
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
	
	public static void testDummy() {
		try {
			driver.get("http://sites.google.com/view/alif-akbar-pranata");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testKeycloakAdmin() {
		try {
			driver.get("http://localhost:8080");
			KeycloakHomePage keycloak = new KeycloakHomePage(driver, adminName, adminPass);
			keycloak.clickAdminLink();
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testHLS() {
		try {
			driver.get("http://localhost:3000/index.html");
			KeycloakLoginPage keycloak = new KeycloakLoginPage(driver);
			//keycloak.loginSequence();
			keycloak.inputUsername(userName);
			keycloak.inputPassword(userPass);
			Thread.sleep(2000);
			keycloak.clickLogin();
			HLSHomePage hls = new HLSHomePage(driver);
			Thread.sleep(1000);
			hls.playVideo();
			Thread.sleep(3000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getWebInfo() {
		System.out.println("Web title: " + driver.getTitle());
		System.out.println("Web url: " + driver.getCurrentUrl());
	}
	
	public static void exitTesting() {
		System.out.println("test completed");
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
		}else if(testEnv.contains("Dummy")) {
			testDummy();
		}
		getWebInfo();
		exitTesting();
	}

}

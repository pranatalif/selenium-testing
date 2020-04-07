package gns3.selenium.webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.junit.Assert;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import gns3.selenium.properties.TestProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.Assert.*;

import java.lang.Thread;
import gns3.selenium.webpages.*;

public class Main {
	public static String projectPath;
	public static WebDriver driver = null;
	public static String browser = null;
	public static String browserMode = null;
	public static String testEnv = null;
	public static String scenario = null;
	public static String adminName = null;
	public static String adminPass = null;
	public static String userName = null;
	public static String userPass = null;
	public static String localIP = "localhost";


	public static void invokeBrowser(String path) {
		//System.out.println(browser + ", " + userName + " and " + userPass);
		try {
			if(browser.contains("Firefox")) {
				//WebDriverManager.firefoxdriver().setup(); //use this if run in local machine
				System.setProperty("webdriver.gecko.driver", path + "/driver/geckodriver");
				FirefoxOptions options = new FirefoxOptions();
				if(browserMode.equals("GUI")) {
					driver = new FirefoxDriver();
				} else if(browserMode.equals("Headless")) {
					driver = new FirefoxDriver(options.setHeadless(true));
				}
			} else if(browser.contains("Chrome")) {
				//WebDriverManager.chromedriver().setup(); //use this if run in local machine
				System.setProperty("webdriver.chrome.driver", path + "/driver/chromedriver");
				//System.out.println(path + "/driver/chromedriver");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--no-sandbox");
				options.setExperimentalOption("useAutomationExtension", false);
				options.addArguments("--headless");
				if(browserMode.equals("GUI")) {
					driver = new ChromeDriver();
				} else if(browserMode.equals("Headless")) {
					driver = new ChromeDriver(options);
				}	
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
			driver.get("http://example.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testKeycloakAdmin(String ip) {
		try {
			if(testEnv.equals("local")) {
				ip = localIP;
			} 
			driver.get("http://" + ip + ":8080");
			KeycloakHomePage keycloak = new KeycloakHomePage(driver, adminName, adminPass);
			keycloak.clickAdminLink(ip);
			//Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testHLS(String ip) {
		try {
			if(testEnv.equals("local")) {
				driver.get("http://" + localIP + ":3000");
			} else if(testEnv.equals("remote")) {
				driver.get("http://" + ip + ":3000");
			} else {
				System.out.println("Incorrect testEnv selection. Check you config file");
				System.exit(0);
			}
			KeycloakLoginPage keycloak = new KeycloakLoginPage(driver);
			keycloak.inputUsername(userName);
			keycloak.inputPassword(userPass);
			//Thread.sleep(2000);
			keycloak.clickLogin(); //s
			loginAssertion();
			//Thread.sleep(1000);
			HLSHomePage hls = new HLSHomePage(driver);
			HLSAssertion();
			hls.playVideo();
			//Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void testProxy(String ip) {
		try {
			if(testEnv.equals("local")) {
				driver.get("http://" + localIP + ":3000");
			} else if(testEnv.equals("remote")) {
				driver.get("http://" + ip + ":3000");
			} else {
				System.out.println("Incorrect testEnv selection. Check you config file");
				System.exit(0);
			}
			KeycloakLoginPage keycloak = new KeycloakLoginPage(driver);
			keycloak.inputUsername(userName);
			keycloak.inputPassword(userPass);
			//Thread.sleep(2000);
			keycloak.clickLogin();
			loginAssertion();
			//Thread.sleep(1000);
			HLSHomePage hls = new HLSHomePage(driver);
			HLSAssertion();
			hls.playVideo();
			//Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getWebInfo() {
		System.out.println("Web title: " + driver.getTitle());
		System.out.println("Web url: " + driver.getCurrentUrl());
	}
	
	
	public static void exitTesting() {
		System.out.println("Test completed!");
		driver.close();
		driver.quit();
	}

	public static void HLSAssertion() throws java.lang.AssertionError{
		try {
			String expectedUrl = "http://hls.gns3.fr:3000/";
	        String actualUrl = driver.getCurrentUrl();  
//	        if(expectedUrl.equalsIgnoreCase(actualUrl)){
//	            System.out.println("Test passed");
//	        }else{
//	            System.out.println("Test failed");
//	        }
//	        if(Assert.assertSame(actualUrl, expectedUrl)) {
//	        	
//	        }
	        Assert.assertEquals(actualUrl, expectedUrl);
		}
		catch(java.lang.AssertionError e){
			System.out.println("Test failed: Can not arrive to the HLS player.");
			e.printStackTrace();
			
			exitTesting();
			Assert.fail();
		}
	}
	
	public static void loginAssertion() {
		boolean errorMessage = driver.findElements(By.xpath("//span[@class='kc-feedback-text']")).isEmpty();
//		List<WebElement> ele = driver.findElements(By.xpath("//span[@class='kc-feedback-text']"));
//		System.out.println(ele);
		try {
			if (errorMessage == true){
				System.out.println("Test passed: Logged in.");	
			}
		} catch(Exception e) {
		    System.out.println("Test failed: Authentication login failed.");
		    e.printStackTrace();
			
			exitTesting();
			Assert.fail();
		} 
	}
	public static void main(String[] args) throws InterruptedException {
		projectPath = System.getProperty("user.dir");
		System.out.println("[INFO] Project path: " + projectPath);
		
		TestProperties.readFile(projectPath);
		System.out.println("[INFO] Testing in " + browser + " browser");
		invokeBrowser(projectPath);
		if(scenario.contains("Keycloak")) {
			String remoteIP = "192.168.122.20";
			testKeycloakAdmin(remoteIP);
		}else if(scenario.contains("HLS")) {
			String remoteIP = "192.168.122.11";
			testHLS(remoteIP);
		}else if(scenario.contains("Proxy")) {
			String remoteIP = "hls.gns3.fr";
			testProxy(remoteIP);
		}else if(scenario.contains("Dummy")) {
			testDummy();
		}
		
		getWebInfo();
		Thread.sleep(3000);
		exitTesting();
	}

}

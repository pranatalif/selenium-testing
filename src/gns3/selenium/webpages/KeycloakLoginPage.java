package gns3.selenium.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class KeycloakLoginPage {
	private static WebDriver driver = null;
	private static WebElement element = null;
	public static String username = null;
	public static String password = null;
	By txb_username = By.id("username");
	By txb_password = By.id("password");
	By btn_login = By.id("kc-login");
	
	public KeycloakLoginPage(WebDriver driver) {
		KeycloakLoginPage.driver = driver;
	}
	
	public KeycloakLoginPage(WebDriver driver, String username, String password) {
		KeycloakLoginPage.driver = driver;
		KeycloakLoginPage.username = username;
		KeycloakLoginPage.password = password;
	}
	
	//public static void loginSequence() throws InterruptedException {
	//	KeycloakLoginPage login = new KeycloakLoginPage(driver);
	//	login.inputUsername(username);
	//	login.inputPassword(password);
	//	Thread.sleep(2000);
	//	login.clickLogin();
	//}
	
	public void inputUsername(String username) {
		driver.findElement(txb_username).sendKeys(username);
	}
	
	public void inputPassword(String password) {
		driver.findElement(txb_password).sendKeys(password);
	}
	
	public void clickLogin() {
		driver.findElement(btn_login).sendKeys(Keys.RETURN);
	}
	
	
	public static WebElement findUsernameTextbox(WebDriver driver) {
		element = driver.findElement(By.id("username"));
		return element;
	}
	
	public static WebElement findPasswordTextbox(WebDriver driver) {
		element = driver.findElement(By.id("password"));
		return element;
	}
	
	public static WebElement findLoginButton(WebDriver driver) {
		element = driver.findElement(By.id("kc-login"));
		return element;
	}
}

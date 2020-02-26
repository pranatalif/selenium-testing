package gns3.selenium.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class KeycloakHomePage {
	private static WebDriver driver = null;
	private static WebElement element = null;
	public String username = null;
	public String password = null;
	By lnk_adminconsole = By.xpath("//a[@href='http://localhost:8080/auth/admin/']");
	
	public KeycloakHomePage(WebDriver driver, String username, String password) {
		KeycloakHomePage.driver = driver;
		this.username = username;
		this.password = password;
	}
	
	public void clickAdminLink() throws InterruptedException {
		driver.findElement(lnk_adminconsole).sendKeys(Keys.RETURN);
		loginSequence();
	}
	
	public void loginSequence() throws InterruptedException {
		KeycloakLoginPage login = new KeycloakLoginPage(driver);
		login.inputUsername(username);
		login.inputPassword(password);
		Thread.sleep(2000);
		login.clickLogin();
	}
	
	public static WebElement findAdminConsoleLink(WebDriver driver) {
		element = driver.findElement(By.xpath("//a[@href='http://localhost:8080/auth/admin/']"));
		return element;
	}
}

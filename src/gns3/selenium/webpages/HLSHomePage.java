package gns3.selenium.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HLSHomePage {
	private WebDriver driver = null;
	private static WebElement element = null;
	public String username = null;
	public String password = null;
	
	public HLSHomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public HLSHomePage(WebDriver driver, String username, String password) {
		this.driver = driver;
		this.username = username;
		this.password = password;
	}
	
	By videoPlayer = By.id("my_video_1_html5_api");
	
	public void playVideo() {
		driver.findElement(videoPlayer).click();
	}
	
	public static WebElement findVideoPlayer(WebDriver driver) {
		element = driver.findElement(By.id("my_video_1_html5_api"));
		return element;
	}
}

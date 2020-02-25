package gns3.selenium.grid;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Hub {
	static WebDriver driver;
	static String nodeUrl;
	
	public static void main(String[] args) {
		try {
			nodeUrl = "http://192.168.122.80:555/wd/hub";
			DesiredCapabilities capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setPlatform(Platform.MAC);
			driver = new RemoteWebDriver(new URL(nodeUrl), capability);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
			driver.get("http://192.168.122.20:3000/index.html");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

}

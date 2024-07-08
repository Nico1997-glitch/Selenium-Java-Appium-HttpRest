package browser_tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class ChromeTest {
	public static void main(String[] args) {

		// Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "sdk_gphone64_x86_64");
		caps.setCapability("udid", "emulator-5554"); // Give Device ID of your mobile phone
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "14.0");
		caps.setCapability("browserName", "Chrome");
		caps.setCapability("noReset", true);

		// Set ChromeDriver location
		System.setProperty("webdriver.chrome.driver", "C://Users//Supporto//OneDrive//chromedriverAndroid.exe");

		// Instantiate Appium Driver
		AppiumDriver driver = null;
		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			driver.get ("http://www.google.com");

		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
		
				
		try {
			Thread.sleep(3000);
			// Cerca il testo "Google"
			driver.findElement (By.name ("q")).sendKeys("Google");
			driver.findElement (By.id ("tsbb")).click();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Stampa i dettagli del primo risultato su Console
		System.out.println (driver.findElement (By.xpath ("//android.widget.TextView[@text=\"TUTTI\"]")).getText());
		
		
	}
}

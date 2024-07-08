package tests;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AppiumTest {

	public static void main(String[] args) {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setUdid("emulator-5554");

		options.setCapability("appium:appPackage", "com.google.android.apps.photos");
		options.setCapability("appium:appActivity", ".home.HomeActivity");
		options.setCapability("platformName", "Android");
		options.setCapability("appium:platformVersion", "VanillaIceCream");
		options.setCapability("appium:automationName", "uiautomator2");

		try {
			AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

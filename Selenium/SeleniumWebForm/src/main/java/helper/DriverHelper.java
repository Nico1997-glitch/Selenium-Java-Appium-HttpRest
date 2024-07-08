package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverHelper {
	private static WebDriver driver;
	
	public static WebDriver getDriver() {
		// Imposta il percorso del driver di Chrome correttamente
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Supporto\\Desktop\\Esercizi\\chromedriver.exe");
		// Inizializza il WebDriver
		driver = new ChromeDriver();
		return driver;
	}
	
	public static void chiudiDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}

package it.smartpaper.selenium.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverHelper {
	private static WebDriver driver;
	private static Properties prop;

	static {
		prop = new Properties();
		try {
			prop.load(new FileInputStream("src/main/resources/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static WebDriver getDriver() {
		// Imposta il percorso del driver di Chrome correttamente
		String driverPath = prop.getProperty("webdriver.chrome.driver");
		System.setProperty("webdriver.chrome.driver", driverPath);
		// Inizializza il WebDriver
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}

	public static void scrollWeb(WebDriver driver, WebElement scroll) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", scroll);
	}

	public static void waitForElementVisible(WebDriver driver, WebElement element, Duration timer) {
		// Instanzia un oggetto WebDriverWait passando il driver e il timer
		WebDriverWait wait = new WebDriverWait(driver, timer);
		// Attendi che l'elemento diventi visibile utilizzando
		// ExpectedConditions.visibilityOf
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void click(WebDriver driver, WebElement element, Duration timer) {
		// Richiama il metodo waitForElementVisible per assicurarsi che l'elemento sia
		// visibile prima di fare clic
		waitForElementVisible(driver, element, timer);
		// Fai clic sull'elemento
		element.click();
	}
	public static void clearAndSendKeys(WebDriver driver, WebElement element, Duration timer, String text) {
		// Assicura che l'elemento sia visibile prima di effettuare l'operazione
		waitForElementVisible(driver, element, timer);
		// Cancella il contenuto dell'elemento
		element.clear();
		// Scrive il testo nell'elemento
		element.sendKeys(text);
	}	
	public static void waitForPresenceOfElementLocated(WebDriver driver, By elementBy, Duration timer) {
        // Instanzia un oggetto WebDriverWait passando il driver e il timer
        WebDriverWait wait = new WebDriverWait(driver, timer);
        // Attendi che l'elemento sia presente utilizzando ExpectedConditions.presenceOfElementLocated
        wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
	}	
	
	public static void chiudiDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}

package it.smartpaper.selenium.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverManager {
	private static WebDriver driver;
	private static Properties prop;
	protected static final Logger logger = LogManager.getLogger();

	static {
		prop = new Properties();
		try {
			prop.load(new FileInputStream("src/main/resources/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static WebDriver getDriver() {
		// Inizializza il WebDriver e imposta il percorso del driver
		if (driver == null) {
			String driverPath = prop.getProperty("webdriver.chrome.driver");
			System.setProperty("webdriver.chrome.driver", driverPath);
			// Inizializza il WebDriver
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		return driver;
	}

	public static void openPage(String url) {
		driver.get(url);
	}

	public static void openPage() {
		String url = prop.getProperty("url");
		openPage(url);
	}
	
	public static String getUrl() {
		String url = driver.getCurrentUrl();
		return url;
	}

	public static void scrollWeb(By elementBy) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(elementBy));
	}

	public static WebElement findElement(By by) {
		return driver.findElement(by);
	}

	public static WebElement waitForElementVisible(By elementBy, Duration timer) {
		// Instanzia un oggetto WebDriverWait passando il driver e il timer
		WebDriverWait wait = new WebDriverWait(driver, timer);
		// Attendi che l'elemento diventi visibile utilizzando
		// ExpectedConditions.visibilityOf
		return wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
	}

	public static WebElement waitForElementVisibleAndClick(By elementBy, Duration timer) {
		// Instanzia un oggetto WebDriverWait passando il driver e il timer
		WebDriverWait wait = new WebDriverWait(driver, timer);
		// Attendi che l'elemento diventi visibile utilizzando
		// ExpectedConditions.visibilityOf
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
		// Clicca sull'elemento
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
		// Ritorna l'elemento cliccato
		return element;
	}

	public static boolean waitForElementDisappear(By elementBy, Duration timer) {
		WebDriverWait wait = new WebDriverWait(driver, timer);
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(elementBy));
	}

	public static void click(By elementBy, Duration timer) {
		// Richiama il metodo waitForElementVisible per assicurarsi che l'elemento sia
		// visibile prima di fare clic
		waitForElementVisible(elementBy, timer);
		// Fai clic sull'elemento
		driver.findElement(elementBy).click();
	}
	
	public static void click(By elementBy, Duration timer, boolean scroll) {
		if(scroll) {
			scrollWeb(elementBy);
		}
		click(elementBy, timer);
	}
	
	public static WebElement clearAndSendKeys(By elementBy, Duration timer, String text) {
		// Assicura che l'elemento sia visibile prima di effettuare l'operazione
		WebElement element = waitForElementVisible(elementBy, timer);
		// Cancella il contenuto dell'elemento
		element.clear();
		// Scrive il testo nell'elemento
		element.sendKeys(text);
		return element;

	}

	public static void waitForPresenceOfElementLocated(By elementBy, Duration timer) {
		// Instanzia un oggetto WebDriverWait passando il driver precedentemente
		// inizializzato e il timer
		WebDriverWait wait = new WebDriverWait(getDriver(), timer);
		// Attendi che l'elemento sia presente utilizzando
		// ExpectedConditions.presenceOfElementLocated
		wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
	}

	public static String saveTextFromElement(By elementBy) {
		WebElement element = driver.findElement(elementBy);
		return element.getText();
	}

	public static String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	public static String getPageSource() {
		return driver.getPageSource();
	}

	public static void selectDropdownByVisibleText(By elementBy, String visibleText, Duration timer) {
		// Assicura che l'elemento del dropdown sia visibile prima di effettuare
		// l'operazione
		WebElement dropdownElement = waitForElementVisible(elementBy, timer);
		// Utilizza la classe Select per interagire con il dropdown
		Select dropdown = new Select(dropdownElement);
		// Seleziona l'opzione dal dropdown utilizzando il testo visibile
		dropdown.selectByVisibleText(visibleText);
	}

	public static void selectDropdownByValue(By elementBy, String value, Duration timer) {
		// Assicura che l'elemento del dropdown sia visibile prima di effettuare
		// l'operazione
		WebElement dropdownElement = waitForElementVisible(elementBy, timer);
		// Utilizza la classe Select per interagire con il dropdown
		Select dropdown = new Select(dropdownElement);
		// Seleziona l'opzione dal dropdown utilizzando il valore dell'attributo "value"
		dropdown.selectByValue(value);
	}

	public static void selectDropdownByIndex(By elementBy, int index, Duration timer) {
		// Assicura che l'elemento del dropdown sia visibile prima di effettuare
		// l'operazione
		WebElement dropdownElement = waitForElementVisible(elementBy, timer);
		// Utilizza la classe Select per interagire con il dropdown
		Select dropdown = new Select(dropdownElement);
		// Seleziona l'opzione dal dropdown utilizzando l'indice
		dropdown.selectByIndex(index);
	}
	
	public static void AcceptAlert() {
		driver.switchTo().alert().accept();
	}

	public static String handleAlert(boolean accept, String expectedText) {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			logger.info("Alert text: " + alertText);

			// Verifica il testo dell'alert
			if (!alertText.equals(expectedText)) {
				logger.error("Il testo dell'alert non corrisponde al testo previsto. Previsto: " + expectedText
						+ ", trovato: " + alertText);
				throw new AssertionError("Il testo dell'alert non corrisponde al testo previsto.");

			}

			if (accept) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} catch (Exception e) {
			logger.error("No alert present!", e);
			return null;
		}
	}

	public static boolean isAlertPresent() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public static void sendKeysToAlert(String text) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(text);
		String alertText = alert.getText();
		alert.accept();
		if (alertText.equals(text)) {
			logger.info("Testo inserito correttamente nell'alert: " + text);
		} else {
			logger.info("Errore: il testo inserito nell'alert non corrisponde al testo atteso.");
		}
	}

	public static void moveSlider(WebElement slider, int percent) {
		Actions action = new Actions(driver);
		int width = slider.getSize().getWidth();
		action.clickAndHold(slider).moveByOffset((width - percent) / 100, 0).release().build().perform();
	}

	public static int getCurrentProgress(WebElement progressBar) {
		 String ariaValuenow = progressBar.getAttribute("aria-valuenow");
		 //espressione condizionale
	        return ariaValuenow != null ? Integer.parseInt(ariaValuenow) : 0;
	}

	public static void waitForProgressBarCompletion(WebElement progressBar) {
	    while (getCurrentProgress(progressBar) < 100) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}

	public static boolean isProgressBarCompleted(WebElement progressBar) {
		// Ottieni il valore attuale della Progress Bar
		int currentValue = getCurrentProgress(progressBar);

		// Controlla se la Progress Bar ha completato
		return currentValue >= 100;
	}

	public static void gestisciCaptcha(boolean captchaRisolvibileManualmente) {
        // Simuliamo il caso in cui il captcha non possa essere automatizzato
        //boolean captchaRisolvibileManualmente = true; // Cambiare a false per simulare un caso in cui il captcha non sia risolvibile manualmente

        if (captchaRisolvibileManualmente) {
            // Mostra un messaggio all'utente per risolvere manualmente il captcha
            JOptionPane.showMessageDialog(null, "Si prega di risolvere manualmente il captcha.\nClicca su OK quando hai risolto il captcha.");
        } else {
            // Se il captcha non può essere risolto manualmente, mostra un messaggio di errore
            JOptionPane.showMessageDialog(null, "Impossibile risolvere il captcha manualmente. Il test non può procedere.");
            
        }
    }
	public static void chiudiDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}

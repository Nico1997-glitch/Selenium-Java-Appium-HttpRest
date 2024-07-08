package it.smartpaper.PhoneDriverManager;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AppiumDriverManager {
	private static AppiumDriver driver;
	protected static final Logger logger = LogManager.getLogger();

//	                                      1. Metodi di configurazione del driver:

	/**
	 * Imposta il driver Appium con le capacità specificate.
	 *
	 * @param appiumUrl       URL del server Appium
	 * @param appPackage      Pacchetto dell'applicazione da testare
	 * @param appActivity     Activity principale dell'applicazione
	 * @param platformName    Nome della piattaforma (Android o iOS)
	 * @param platformVersion Versione del sistema operativo
	 * @param automationName  Nome del framework di automazione (es. uiautomator2)
	 * @return AndroidDriver configurato
	 * @throws MalformedURLException Se l'URL di Appium è malformato
	 */
	public static AppiumDriver setUp(String appiumUrl, String appPackage, String appActivity, String platformName,
			String platformVersion, String automationName) throws MalformedURLException {
		URL url = new URL(appiumUrl);
		UiAutomator2Options options = new UiAutomator2Options();
		options.setCapability("appium:appPackage", appPackage);
		options.setCapability("appium:appActivity", appActivity);
		options.setCapability("platformName", platformName);
		options.setCapability("appium:platformVersion", platformVersion);
		options.setCapability("appium:automationName", automationName);
		driver = new AppiumDriver(url, options);
		return driver;

		// (SINTASSI PER RICHIAMARLO NELLA CLASSE)
//		AppiumDriver driver = AppiumDriverManager.setUp("http://127.0.0.1:4723/wd/hub", "com.appiumpro.the_app",
//				"com.appiumpro.the_app.MainActivity", "Android", "14.0", "uiautomator2");
	}

	public static AppiumDriver getDriver() {
		return driver;
	}
	
	public static void tearDown() {
		try {
			driver.quit();
		} catch (Exception ignore) {
		}
	}
	

//                                        2. Metodi per l'attesa degli elementi: 
	
    /**
     * Trova un elemento nell'interfaccia utente.
     *
     * @param by Locator dell'elemento
     * @return WebElement trovato
     */
	public static WebElement findElement(By by) {
		return driver.findElement(by);
	}
	
    /**
     * Trova una lista di elementi nell'interfaccia utente.
     *
     * @param by Locator dell'elemento
     * @return WebElement trovato
     */
	
    public static List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }
    
	/**
	 * Trova un elemento nell'interfaccia utente e restituisce il suo testo o attributo "text" in base alla scelta.
	 *
	 * @param by           Locator dell'elemento
	 * @param useAttribute Booleano che indica se restituire il testo tramite l'attributo "text" dell'elemento
	 * @return Testo dell'elemento o attributo "text" dell'elemento in base alla scelta
	 */	
	public static String findElement(By by, boolean useAttribute) {
		WebElement element = driver.findElement(by);
        if (useAttribute) {
            return element.getAttribute("text");
        } else {
            return element.getText();
        }
	}
	
	
	/**
	 * Attende che un elemento sia visibile entro un determinato timeout.
	 *
	 * @param elementBy Locator dell'elemento
	 * @param timer     Timeout per l'attesa
	 * @return WebElement visibile
	 */
	public static WebElement waitForElementVisible(By elementBy, Duration timer) {
		WebDriverWait wait = new WebDriverWait(driver, timer);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
	}

	/**
	 * Attende che un elemento scompaia entro un determinato timeout.
	 *
	 * @param elementBy Locator dell'elemento
	 * @param timer     Timeout per l'attesa
	 * @return true se l'elemento scompare, altrimenti false
	 */
	public static boolean waitForElementDisappear(By elementBy, Duration timer) {
		WebDriverWait wait = new WebDriverWait(driver, timer);
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(elementBy));
	}
	
	

	// 							3. Metodi per l'interazione con gli elementi:

	/**
	 * Esegue il clic su un elemento dopo aver atteso che sia visibile.
	 *
	 * @param elementBy Locator dell'elemento
	 * @param timer     Timeout per l'attesa
	 * @param scroll    True se si desidera eseguire lo scroll per trovare
	 *                  l'elemento, altrimenti false
	 */
	public static void clickAndScroll(By elementBy, Duration timer, boolean scroll) {
		WebElement element = waitForElementVisible(elementBy, timer);
		if (scroll) {
			element = scrollToElement(elementBy, timer);
		} else {
			element = waitForElementVisible(elementBy, timer);
		}
		element.click();
	}
	
	/**
	 * Esegue il clic su un elemento dopo aver atteso che diventi cliccabile.
	 *
	 * @param locator Locator dell'elemento
	 */
	public static void click(By elementBy, Duration timer) {
		WebElement element = waitForElementVisible(elementBy, timer);
		element.click();
	}

	/**
	 * Pulisce il campo di testo e invia una stringa di testo.
	 *
	 * @param elementBy Locator dell'elemento
	 * @param timer     Timeout per l'attesa
	 * @param text      Testo da inviare
	 */
	public static void clearAndSendKeys(By elementBy, Duration timer, String text) {
		WebElement element = waitForElementVisible(elementBy, timer);
		element.clear();
		element.sendKeys(text);
	}
	
	/**
	 * Scrive il campo di testo e invia una stringa di testo.
	 *
	 * @param elementBy Locator dell'elemento
	 * @param timer     Timeout per l'attesa
	 * @param text      Testo da inviare
	 */
	public static void sendKeys(By elementBy, Duration timer, String text) {
		WebElement element = waitForElementVisible(elementBy, timer);
		element.sendKeys(text);
	}

	/**
	 * Restituisce il testo da un elemento.
	 *
	 * @param elementBy Locator dell'elemento
	 * @return Testo dell'elemento
	 */
	public static String saveTextFromElement(By elementBy) {
		WebElement element = driver.findElement(elementBy);
		return element.getText();
	}
	
	/**
     * Esegue un'operazione generica su un elemento specificato.
     *
     * @param locator   Locator dell'elemento
     * @param index     Indice dell'elemento desiderato
     * @param operation Nome dell'operazione da eseguire
     * @return WebElement su cui è stata eseguita l'operazione
     */
	public static WebElement performOperationOnElement(By locator, By secondLocator, int index, String operation) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(locator));
	    logger.info("Eseguo l'operazione '" + operation + "' sull'elemento");
	    clickableElement.click();
		wait.until(ExpectedConditions.elementToBeClickable(secondLocator));
		List<WebElement> textViewElements = driver.findElements(secondLocator);
		logger.info("Leggo il " + operation + " selezionato");
		if (textViewElements.size() > index) {
			WebElement selectedTextViewElement = textViewElements.get(index);
			String read = selectedTextViewElement.getText();
			selectedTextViewElement.click();
			logger.info("Cliccato sul " + operation + " di : " + read);
			return selectedTextViewElement;
		} else {
			logger.warn("Non ci sono abbastanza elementi per eseguire l'operazione desiderata.");
			return null;
		}
	}
	
	/**
	 * Legge il testo di un elemento specifico e verifica se corrisponde al testo
	 * atteso.
	 *
	 * @param elementLocator Locator dell'elemento da cliccare per leggere il testo
	 * @param textLocator    Locator dell'elemento contenente il testo da verificare
	 * @param expectedText   Testo atteso dell'elemento
	 * @param waitDuration   Durata massima dell'attesa
	 */
	public static void readAndVerifyElementText(By elementLocator, By textLocator, String expectedText,
			Duration timer) {
		WebDriverWait wait = new WebDriverWait(driver, timer);
		WebElement element = waitForElementVisible(elementLocator, timer);
		logger.info("Leggi il testo dell'elemento {}", elementLocator);
		element.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(textLocator));
		WebElement textElement = driver.findElement(textLocator);
		String actualText = textElement.getText();
		logger.info("Testo nell'elemento {}: {}", elementLocator, actualText);
		assertEquals(expectedText, actualText, "Il testo dell'elemento non corrisponde al valore atteso");
	}
	
											//4. Lettura e Scrittura su File
	
    /**
     * Legge i dati da un file CSV e restituisce una lista di righe, dove ogni riga è una lista di stringhe.
     *
     * @param filePath Percorso del file CSV da leggere
     * @return Una lista di righe del file CSV, dove ogni riga è una lista di stringhe
     * @throws IOException Se si verifica un errore durante la lettura del file
     */
	
    public static List<List<String>> readDataFromCSV(Path filePath) throws IOException {
        return Files.lines(filePath)
                    .map(line -> List.of(line.split(";")))
                    .collect(Collectors.toList());
    }
    
    /**
     * Scrive i dati su un file CSV utilizzando il percorso specificato.
     *
     * @param filePath Percorso del file CSV in cui scrivere i dati
     * @param data     I dati da scrivere nel file CSV, dove ogni elemento della lista rappresenta una riga del file
     * @throws IOException Se si verifica un errore durante la scrittura del file
     */
    
    public static void writeDataToCSV(Path filePath, List<List<String>> data) throws IOException {
        List<String> lines = data.stream()
                                 .map(row -> String.join(";", row))
                                 .collect(Collectors.toList());

        Files.write(filePath, lines);
    }
	

	// 										5. Medoti ausiliari(Privati)

	/**
	 * Esegue lo scroll fino all'elemento utilizzando JavascriptExecutor.
	 *
	 * @param elementBy Locator dell'elemento
	 * @param timer     Timeout per l'attesa
	 * @return Elemento dopo lo scroll
	 */
	private static WebElement scrollToElement(By elementBy, Duration timer) {
		WebElement element = waitForElementVisible(elementBy, timer);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		return element;
	}
}

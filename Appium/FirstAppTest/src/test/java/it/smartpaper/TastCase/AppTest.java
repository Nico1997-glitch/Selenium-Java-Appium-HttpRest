package it.smartpaper.TastCase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {
	private AndroidDriver driver;
	protected static final Logger logger = LogManager.getLogger();

	@BeforeEach
	public void setUp() throws MalformedURLException {
		URL appiumUrl = new URL("http://127.0.0.1:4723/wd/hub");
		UiAutomator2Options options = new UiAutomator2Options();
		options.setCapability("appium:appPackage", "com.appiumpro.the_app");
		options.setCapability("appium:appActivity", "com.appiumpro.the_app.MainActivity");
		options.setCapability("platformName", "Android");
		options.setCapability("appium:platformVersion", "14.0");
		options.setCapability("appium:automationName", "uiautomator2");

		driver = new AndroidDriver(appiumUrl, options);
		//appiumDriver;
		//AppiumDriverMenager() creare metodi prinicipali, setUP generico, leggere file da csv
	}

	@Test
	public void echoBoxTest() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		logger.info("Clicco su Echo Box");
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(//android.view.ViewGroup[@resource-id=\"RNE__LISTITEM__padView\"])[1]")));
		driver.findElement(By.xpath("(//android.view.ViewGroup[@resource-id=\"RNE__LISTITEM__padView\"])[1]")).click();
		WebElement textInput = driver.findElement(By.className("android.widget.EditText"));
		textInput.sendKeys("Milano");
		logger.info("Hai inserito nel campo : " + textInput);
		driver.findElement(By.xpath("//android.widget.TextView[@text=\"Save\"]")).click();
		WebElement aspetta = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Milano\"]"));
		String messaggioAtteso = aspetta.getAttribute("text");
		String expectedText = "Milano";
		assertEquals(messaggioAtteso, expectedText, "Milano");
		logger.info("Il testo dell'elemento corrisponde a quello atteso.");
		logger.info("Concludo Echo Box");
	}

	@Test
	public void loginScreenTest() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		logger.info("Clicco su Login Screen");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//android.view.ViewGroup[@content-desc=\"Login Screen\"]")));
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Login Screen\"]")).click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//android.widget.EditText[@content-desc=\"username\"]")));
		logger.info("Inserisco i dati per il login");
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"username\"]")).sendKeys("Marco");
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"password\"]")).sendKeys("Viaggio");
		logger.info("Clicco su Login");
		driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"loginBtn\"]")).click();
		logger.info("Aspetto il messaggio di errore");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//android.widget.TextView[@resource-id='android:id/message']")));
		WebElement leggiTesto = driver
				.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/message']"));
		String textAspect = leggiTesto.getText();
		assertTrue(textAspect, true);
		logger.info("Il testo sarà : " + textAspect);
		logger.info("Concludo Login Screen");
	}

	@Test
	public void clipboardDemoTest() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		logger.info("Clicco su Clipboard Demo");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//android.view.ViewGroup[@content-desc=\"Clipboard Demo\"]")));
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Clipboard Demo\"]")).click();
		WebElement leggiTesto = driver
				.findElement(By.xpath("//android.widget.TextView[@resource-id=\"noClipboardText\"]"));
		String text = leggiTesto.getText();
		logger.info("Il testo attuale sarà : " + text);
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"messageInput\"]")).sendKeys("Balu");
		driver.findElement(By.xpath("//android.widget.Button[@content-desc='setClipboardText']")).click();
		WebElement leggiNewTesto = driver
				.findElement(By.xpath("//android.widget.Button[@content-desc=\"refreshClipboardText\"]"));
		leggiNewTesto.click();
		String textAttuale = leggiNewTesto.getAttribute("text");
		logger.info("Il testo attuale sarà : " + textAttuale);
		assertNotEquals(textAttuale, text, "Il testo non è stato aggiornato");
		logger.info("Il è stato aggiornato correttamente");
		logger.info("Concludo Clipboard Demo");
	}

	@Test
	public void webViewDemoTest() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		logger.info("Clicco su WebView Demo");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//android.view.ViewGroup[@content-desc=\"Webview Demo\"]")));
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Webview Demo\"]")).click();
		logger.info("Clicco e inserisco l'url");
		driver.findElement(By.className("android.widget.EditText")).sendKeys("https://www.google.it");
		logger.info("Clicco su Go");
		driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"navigateBtn\"]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/alertTitle")));
		WebElement readText = driver.findElement(By.id("android:id/alertTitle"));
		logger.info("Verifico il testo");
		String textAtt =readText.getText();
		assertEquals(textAtt, "Sorry, you are not allowed to visit that url");
		logger.info("Il testo sarà : " + textAtt);
		logger.info("Clicco su OK");
		driver.findElement(By.id("android:id/button1")).click();
		logger.info("Clicco su Clear");
		driver.findElement(By.id("clearBtn")).click();
		logger.info("Concludo WebView Demo");
	}
	
	@Test
    public void photoDemoTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.info("Clicco su Photo Demo");
        wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//android.view.ViewGroup[@content-desc=\"Photo Demo\"]")));
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Photo Demo\"]")).click();

        readAndVerifyPhotoText(1, "This is a picture of: Imposing mountains and West Vancouver");
        readAndVerifyPhotoText(2, "This is a picture of: The Vancouver skyline at sunrise");
        readAndVerifyPhotoText(3, "This is a picture of: English bay with snowy mountains");

        logger.info("Concludo su Photo Demo");
    }

    private void readAndVerifyPhotoText(int photoIndex, String expectedPhotoText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.info("Leggi il testo della foto {}", photoIndex);
        driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[" + photoIndex + "]/android.widget.ImageView")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/message")));
        WebElement readTextElement = driver.findElement(By.id("android:id/message"));
        String photoText = readTextElement.getText();
        logger.info("Testo nella foto {}: {}", photoIndex, photoText);
        assertEquals(expectedPhotoText, photoText, "Il testo della foto non corrisponde al valore atteso");
        logger.info("Clicco su Ok");
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]")).click();
    }
    
	@Test
	public void geolocationDemoTest() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		logger.info("Clicco su Photo Demo");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//android.view.ViewGroup[@content-desc=\"Geolocation Demo\"]")));
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Geolocation Demo\"]")).click();
		logger.info("Clicco su Only this time");
		driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_one_time_button")).click();
		logger.info("Leggo i dati della geolocalizzazione");
		wait.until(ExpectedConditions.elementToBeClickable(By.className("android.widget.TextView")));
		List<WebElement> textViewElements = driver.findElements(By.className("android.widget.TextView"));
		int index = 0; // Inserisci un numero da 0 a 2
		if (textViewElements.size() > index) {
			WebElement fourthTextViewElement = textViewElements.get(index);
			String geolocationText = fourthTextViewElement.getText();
			logger.info("Testo richiesto: " + geolocationText);
		} else {
			logger.warn("Non ci sono abbastanza elementi per leggere quello desiderato.");
		}

	}
	
	@Test
	public void pickerDemoTest() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		logger.info("Clicco su Picker Demo");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//android.view.ViewGroup[@content-desc=\"Picker Demo\"]")));
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Picker Demo\"]")).click();
		logger.info("Faccio click sul box del mese");
		driver.findElement(By.className("android.widget.TextView")).click();
		logger.info("Faccio operazioni sul mese desiderato");
		clickOnElement("//android.widget.Spinner[@content-desc=\"monthPicker\"]", 5, "Mese");
		logger.info("Operazioni eseguite sul mese desiderato.");
		//per prendere tutti i numeri eseguire lo scroll
		clickOnElement("//android.widget.Spinner[@content-desc=\"dayPicker\"]", 8, "Giorno");
		logger.info("Operazioni eseguite sul giorno desiderato.");
		clickOnDesiredElement(By.xpath("//android.widget.Button[@content-desc=\"learnMore\"]"));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("android.widget.TextView")));
        WebElement selectedTextViewElement = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/message\"]"));
        String read = selectedTextViewElement.getText();
        logger.info("Il testo sarà : " + read);
        clickOnDesiredElement(By.className("android.widget.Button"));
        logger.info("Concludo su Picker Demo");
        
	}

	public void clickOnDesiredElement(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		logger.info("Attendo prima di cliccare");
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		logger.info("L'elemento è cliccabile. Sto eseguendo il click.");
		driver.findElement(locator).click();
	}

	 public WebElement clickOnElement(String locator, int index, String operation) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        By elementLocator = By.xpath(locator);
	        logger.info("Clicco sul box " + operation);
	        clickOnDesiredElement(elementLocator);
	        wait.until(ExpectedConditions.elementToBeClickable(By.className("android.widget.CheckedTextView")));
	        List<WebElement> textViewElements = driver.findElements(By.className("android.widget.CheckedTextView"));
	        logger.info("Leggo il " + operation + " selezionato");
	        if (textViewElements.size() > index) {
	            WebElement selectedTextViewElement = textViewElements.get(index);
	            String read = selectedTextViewElement.getText();
	            selectedTextViewElement.click();
	            logger.info("Cliccato sul " + operation + " di : " + read);
	            return selectedTextViewElement;
	        } else {
	            logger.warn("Non ci sono abbastanza elementi per cliccare su quello desiderato.");
	            return null;
	        }
	    }

	 
		@AfterEach
	public void tearDown() {
		try {
			driver.quit();
		} catch (Exception ignore) {
		}
	}
}

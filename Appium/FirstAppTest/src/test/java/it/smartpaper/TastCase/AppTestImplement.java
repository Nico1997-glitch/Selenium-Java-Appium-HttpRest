package it.smartpaper.TastCase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import it.smartpaper.PhoneDriverManager.AppiumDriverManager;

public class AppTestImplement {
	protected static final Logger logger = LogManager.getLogger();
	private static final Duration durata = Duration.ofSeconds(10);

	@BeforeEach
	public void setUp() throws MalformedURLException {

		AppiumDriverManager.setUp("http://127.0.0.1:4723/wd/hub", "com.appiumpro.the_app",
				"com.appiumpro.the_app.MainActivity", "Android", "14.0", "uiautomator2");
	}

	//@Test
	public void echoBoxTest() {
		logger.info("Clicco su Echo Box");
		AppiumDriverManager.click(
				By.xpath("(//android.view.ViewGroup[@resource-id=\"RNE__LISTITEM__padView\"])[1]"), durata);
		AppiumDriverManager.sendKeys(By.className("android.widget.EditText"), durata, "Milano");
		String textInput = AppiumDriverManager.saveTextFromElement(By.className("android.widget.EditText"));
		logger.info("Hai inserito nel campo : " + textInput);
		AppiumDriverManager.click(By.xpath("//android.widget.TextView[@text=\"Save\"]"),durata);
		String messaggioAtteso = AppiumDriverManager
				.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Milano\"]"), true);
		String expectedText = "Milano";
		assertEquals(messaggioAtteso, expectedText, "Milano");
		logger.info("Il testo dell'elemento corrisponde a quello atteso.");
		logger.info("Concludo Echo Box");
	}

	//@Test
	public void loginScreenTest() {
		logger.info("Clicco su Login Screen");
		AppiumDriverManager.click(By.xpath("//android.view.ViewGroup[@content-desc=\"Login Screen\"]"), durata);
		AppiumDriverManager.waitForElementVisible(By.xpath("//android.widget.EditText[@content-desc=\"username\"]"),
				durata);
		logger.info("Inserisco i dati per il login");
		AppiumDriverManager.sendKeys(By.xpath("//android.widget.EditText[@content-desc=\"username\"]"), durata,
				"Marco");
		AppiumDriverManager.sendKeys(By.xpath("//android.widget.EditText[@content-desc=\"password\"]"), durata,
				"Viaggio");
		logger.info("Clicco su Login");
		AppiumDriverManager.click(By.xpath("//android.widget.Button[@content-desc=\"loginBtn\"]"), durata);
		logger.info("Aspetto il messaggio di errore");
		AppiumDriverManager.waitForElementVisible(
				By.xpath("//android.widget.TextView[@resource-id='android:id/message']"), durata);
		String textAspect = AppiumDriverManager
				.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/message']"), false);
		assertTrue(textAspect, true);
		logger.info("Il testo sarà : " + textAspect);
		logger.info("Concludo Login Screen");
	}

	 //@Test
	public void clipboardDemoTest() {
		logger.info("Clicco su Clipboard Demo");
		AppiumDriverManager
				.click(By.xpath("//android.view.ViewGroup[@content-desc=\"Clipboard Demo\"]"), durata);
		String text = AppiumDriverManager.saveTextFromElement(By.xpath("//android.widget.TextView[@resource-id=\"noClipboardText\"]"));
		logger.info("Il testo attuale sarà : " + text);
		AppiumDriverManager.sendKeys(By.xpath("//android.widget.EditText[@content-desc=\"messageInput\"]"), durata, "Balu");
		AppiumDriverManager.click(By.xpath("//android.widget.Button[@content-desc='setClipboardText']"), durata);
		AppiumDriverManager.click(By.xpath("//android.widget.Button[@content-desc=\"refreshClipboardText\"]"), durata);
		String textAttuale = AppiumDriverManager.findElement(By.xpath("//android.widget.Button[@content-desc=\"refreshClipboardText\"]"), false);
		logger.info("Il testo attuale sarà : " + textAttuale);
		assertNotEquals(textAttuale, text, "Il testo non è stato aggiornato");
		logger.info("Il è stato aggiornato correttamente");
		logger.info("Concludo Clipboard Demo");
	}

	//@Test
	public void webViewDemoTest() {
		logger.info("Clicco su WebView Demo");
		AppiumDriverManager.click(By.xpath("//android.view.ViewGroup[@content-desc=\"Webview Demo\"]"), durata);
		logger.info("Clicco e inserisco l'url");
		AppiumDriverManager.sendKeys(By.className("android.widget.EditText"), durata, "https://www.google.it");
		logger.info("Clicco su Go");
		AppiumDriverManager.click(By.xpath("//android.widget.Button[@content-desc=\"navigateBtn\"]"), durata);
		AppiumDriverManager.waitForElementVisible(By.id("android:id/alertTitle"), durata);
		logger.info("Verifico il testo");
		String textAtt = AppiumDriverManager.saveTextFromElement(By.id("android:id/alertTitle"));
		assertEquals(textAtt, "Sorry, you are not allowed to visit that url");
		logger.info("Il testo sarà : " + textAtt);
		logger.info("Clicco su OK");
		AppiumDriverManager.click(By.id("android:id/button1"), durata);
		logger.info("Clicco su Clear");
//		WebElement clearButton = AppiumDriverManager.findElement(AppiumBy.accessibilityId("clearBtn"));
//		clearButton.click();
		AppiumDriverManager.click(AppiumBy.accessibilityId("clearBtn"), durata);
		logger.info("Concludo WebView Demo");
	}

	// @Test
	public void photoDemoTest() {
		logger.info("Clicco su Photo Demo");
		AppiumDriverManager.click(By.xpath("//android.view.ViewGroup[@content-desc=\"Photo Demo\"]"), durata);
		readAndVerifyPhotoText(1, "This is a picture of: Imposing mountains and West Vancouver");
		readAndVerifyPhotoText(2, "This is a picture of: The Vancouver skyline at sunrise");
		readAndVerifyPhotoText(3, "This is a picture of: English bay with snowy mountains");

		logger.info("Concludo su Photo Demo");
	}

	private void readAndVerifyPhotoText(int photoIndex, String expectedPhotoText) {
		logger.info("Leggi il testo della foto {}", photoIndex);
		AppiumDriverManager.click(
				By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[" + photoIndex
						+ "]/android.widget.ImageView"),durata);
		AppiumDriverManager.waitForElementVisible(By.id("android:id/message"), durata);
		String photoText = AppiumDriverManager.saveTextFromElement(By.id("android:id/message"));
		logger.info("Testo nella foto {}: {}", photoIndex, photoText);
		assertEquals(expectedPhotoText, photoText, "Il testo della foto non corrisponde al valore atteso");
		logger.info("Clicco su Ok");
		AppiumDriverManager
				.click(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]"), durata);
	}

	//@Test
	public void geolocationDemoTest() {
		logger.info("Clicco su Photo Demo");
		AppiumDriverManager.click(By.xpath("//android.view.ViewGroup[@content-desc=\"Geolocation Demo\"]"), durata);
		logger.info("Clicco su Only this time");
		AppiumDriverManager.click(By.id("com.android.permissioncontroller:id/permission_allow_one_time_button"), durata);
		logger.info("Leggo i dati della geolocalizzazione");
		AppiumDriverManager.waitForElementVisible(By.className("android.widget.TextView"), durata);
		List<WebElement> textViewElements = AppiumDriverManager.findElements(By.className("android.widget.TextView"));
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
		logger.info("Clicco su Picker Demo");
		AppiumDriverManager.click(By.xpath("//android.view.ViewGroup[@content-desc=\"Picker Demo\"]"),durata);
		logger.info("Faccio click sul box del mese");
		AppiumDriverManager.click(By.className("android.widget.TextView"), durata);
		logger.info("Faccio operazioni sul mese desiderato");
		AppiumDriverManager.performOperationOnElement(By.xpath("//android.widget.Spinner[@content-desc=\"dayPicker\"]"),
				By.className("android.widget.CheckedTextView"), 5, "Mese");
		logger.info("Operazioni eseguite sul mese desiderato.");
		// per prendere tutti i numeri eseguire lo scroll
		AppiumDriverManager.performOperationOnElement(By.xpath("//android.widget.Spinner[@content-desc=\"dayPicker\"]"),
				By.className("android.widget.CheckedTextView"), 8, "Giorno");
		logger.info("Operazioni eseguite sul giorno desiderato.");
		AppiumDriverManager.click(AppiumBy.accessibilityId("learnMore"), durata);
		AppiumDriverManager.waitForElementVisible(By.className("android.widget.TextView"), durata);
		String read = AppiumDriverManager
				.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/message\"]"), true);
		logger.info("Il testo sarà : " + read);
		AppiumDriverManager.click(By.className("android.widget.Button"), durata);
		logger.info("Concludo su Picker Demo");

	}

	@AfterEach
	public void closeApp() {
		AppiumDriverManager.tearDown();
	}
}

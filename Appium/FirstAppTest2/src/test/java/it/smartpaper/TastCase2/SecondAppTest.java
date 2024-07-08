package it.smartpaper.TastCase2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import it.smartpaper.PhoneDriverManager.AppiumDriverManager;

public class SecondAppTest {
	protected static final Logger logger = LogManager.getLogger();
	private static final Duration durata = Duration.ofSeconds(10);

	@BeforeEach
	public void setUp() throws MalformedURLException {

		AppiumDriverManager.setUp("http://127.0.0.1:4723/wd/hub", "com.saucelabs.mydemoapp.rn",
				"com.saucelabs.mydemoapp.rn.MainActivity", "Android", "14.0", "uiautomator2");
	}

	@Test
	public void loginTest() {
		AppiumDriverManager.click(
				AppiumBy.xpath("//android.view.ViewGroup[@content-desc='open menu']/android.widget.ImageView"), durata);
		logger.info("Clicca sull'opzione di accesso nel menu");
		AppiumDriverManager.click(AppiumBy.accessibilityId("menu item log in"), durata);
		logger.info("Inserisci l'indirizzo email nell'input del campo Username");
		AppiumDriverManager.sendKeys(AppiumBy.accessibilityId("Username input field"), durata, "alice@example.com");
		logger.info("Inserisci la password nell'input del campo Password");
		AppiumDriverManager.sendKeys(AppiumBy.accessibilityId("Password input field"), durata, "10203040");
		logger.info("Fai clic sul pulsante di accesso");
		AppiumDriverManager.click(AppiumBy.accessibilityId("Login button"), durata);
//		boolean verificaLogin = AppiumDriverManager.waitForElementDisappear(AppiumBy.accessibilityId("Login button"),
//				durata);
//			assertTrue(verificaLogin, "L'accesso non è avvenuto correttamente.");
//			logger.info("L'accesso è avvenuto correttamente.");
//
		logger.info("Verifico messaggio di errore");
		WebElement errorMessageElement = AppiumDriverManager
				.waitForElementVisible(AppiumBy.accessibilityId("Login button"), durata);

		if (errorMessageElement != null) {
			String expectedErrorMessage = "Il messaggio di errore non è quello atteso.";
			String actualErrorMessage = errorMessageElement.getAttribute("text");
			assertEquals(actualErrorMessage, expectedErrorMessage, "Il messaggio di errore non è quello atteso.");
			logger.info("Risultato corretto : " + actualErrorMessage);
		} else {
			logger.error("L'elemento del messaggio di errore non è stato trovato.");

		}
	}

	// @Test
	public void prodottiTest() {
		By carello = AppiumBy.accessibilityId("Add To Cart button");
		logger.info("Click sul  prodotto.");
		aggiungiAlCarrello(By.xpath("(//android.view.ViewGroup[@content-desc=\"store item\"])[2]"), carello);
		AppiumDriverManager.click(
				By.xpath("//android.view.ViewGroup[@content-desc='open menu']/android.widget.ImageView"), durata);
		AppiumDriverManager.click(By.xpath("//android.widget.TextView[@text=\"Catalog\"]"), durata);
		aggiungiAlCarrello(By.xpath("(//android.view.ViewGroup[@content-desc=\"store item\"])[1]"), carello);

		logger.info("Rimozione del prodotto dai preferiti.");
		logger.info("Cliccato sull'icona del carrello.");
		AppiumDriverManager.click(
				By.xpath("//android.view.ViewGroup[@content-desc='cart badge']/android.widget.ImageView"), durata);
		logger.info("Clicco sul pulsante 'Rimuovi Prodotto'.");
		AppiumDriverManager.click(By.xpath("(//android.widget.TextView[@text='Remove Item'])[1]"), durata);
		logger.info("Click sul pulsante 'Aggiungi'");
		AppiumDriverManager.click(AppiumBy.accessibilityId("counter plus button"), durata);
		logger.info("Verifica se l'elemento di rimozione è ancora presente nel carrello");
		boolean isPresent = !AppiumDriverManager.getDriver()
				.findElements(By.xpath("(//android.widget.TextView[@text='Remove Item'])[2]")).isEmpty();
		assertFalse("Prodotto non eliminato dal carrello.", isPresent);
		logger.info("Prodotto eliminato con successo dal carrello.");
	}

	public static void aggiungiAlCarrello(By locatorProdotto, By locatorPulsanteAggiungi) {
		// Esegui le azioni per aggiungere il prodotto al carrello
		AppiumDriverManager.click(locatorProdotto, durata);
		AppiumDriverManager.click(locatorPulsanteAggiungi, durata);
		logger.info("Verifica che il prodotto sia inserito nel carello.");
		WebElement elementoNelCarello = AppiumDriverManager.waitForElementVisible(
				By.xpath("//android.view.ViewGroup[@content-desc=\"cart badge\"]/android.widget.ImageView"), durata);
		assertTrue("La lista dei prodotti non è visibile.", elementoNelCarello.isDisplayed());
		logger.info("L'elemento è stato aggiunto correttamente");
	}

	// @Test
	public void acquistoCompleto() {
		AppiumDriverManager.click(
				AppiumBy.xpath("//android.view.ViewGroup[@content-desc='open menu']/android.widget.ImageView"), durata);
		AppiumDriverManager.click(AppiumBy.accessibilityId("menu item log in"), durata);
		AppiumDriverManager.sendKeys(AppiumBy.accessibilityId("Username input field"), durata, "bob@example.com");
		AppiumDriverManager.sendKeys(AppiumBy.accessibilityId("Password input field"), durata, "10203040");
		AppiumDriverManager.click(AppiumBy.accessibilityId("Login button"), durata);
		boolean isLoginSuccessful = AppiumDriverManager
				.waitForElementDisappear(AppiumBy.accessibilityId("Login button"), durata);
		if (isLoginSuccessful) {
			logger.info("Login avvenuto");
		} else {
			logger.info("Login fallito : Sorry, this user has been locked out.");
		}

		By carello = AppiumBy.accessibilityId("Add To Cart button");
		logger.info("Click sul  prodotto.");
		aggiungiAlCarrello(By.xpath("(//android.view.ViewGroup[@content-desc=\"store item\"])[4]"), carello);
		AppiumDriverManager.click(
				By.xpath("//android.view.ViewGroup[@content-desc='open menu']/android.widget.ImageView"), durata);
		AppiumDriverManager.click(By.xpath("//android.widget.TextView[@text=\"Catalog\"]"), durata);
		aggiungiAlCarrello(By.xpath("(//android.view.ViewGroup[@content-desc=\"store item\"])[3]"), carello);
		logger.info("Cliccato sull'icona del carrello.");
		AppiumDriverManager.click(
				By.xpath("//android.view.ViewGroup[@content-desc='cart badge']/android.widget.ImageView"), durata);
		AppiumDriverManager.click(AppiumBy.accessibilityId("Proceed To Checkout button"), durata);
		logger.info("Inserisco i dati per la spedizione");
		inserisciDati();
		AppiumDriverManager.click(AppiumBy.accessibilityId("To Payment button"), durata);

	}

	public static void inserisciDati() {
		AppiumDriverManager.sendKeys(AppiumBy.accessibilityId("Full Name* input field"), durata, "Marco");
		AppiumDriverManager.sendKeys(AppiumBy.accessibilityId("Address Line 1* input field"), durata, "Via Aurelia");
		AppiumDriverManager.sendKeys(AppiumBy.accessibilityId("City* input field"), durata, "Milano");
		AppiumDriverManager.sendKeys(AppiumBy.accessibilityId("State/Region input field"), durata, "Lombardia");
		AppiumDriverManager.sendKeys(AppiumBy.accessibilityId("Zip Code* input field"), durata, "66554");
		AppiumDriverManager.sendKeys(AppiumBy.accessibilityId("Country* input field"), durata, "Italy");
		logger.info("Verifica che tutti i campi siano stati compilati");
		WebElement fullNameField = AppiumDriverManager.findElement(AppiumBy.accessibilityId("Full Name* input field"));
		assertNotNull(fullNameField);

		WebElement addressField = AppiumDriverManager
				.findElement(AppiumBy.accessibilityId("Address Line 1* input field"));
		assertNotNull(addressField);

		WebElement cityField = AppiumDriverManager.findElement(AppiumBy.accessibilityId("City* input field"));
		assertNotNull(cityField);

		WebElement stateField = AppiumDriverManager.findElement(AppiumBy.accessibilityId("State/Region input field"));
		assertNotNull(stateField);

		WebElement zipCodeField = AppiumDriverManager.findElement(AppiumBy.accessibilityId("Zip Code* input field"));
		assertNotNull(zipCodeField);

		WebElement countryField = AppiumDriverManager.findElement(AppiumBy.accessibilityId("Country* input field"));
		assertNotNull(countryField);
	}

	@AfterEach
	public void closeApp() {
		AppiumDriverManager.tearDown();
	}
}

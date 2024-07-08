package it.smartpaper.ThirdApp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.MalformedURLException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import it.smartpaper.PhoneDriverManager.AppiumDriverManager;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ThirdAppTest {
	protected static final Logger logger = LogManager.getLogger();
	private static final Duration durata = Duration.ofSeconds(10);

	@BeforeEach
	public void setUp() throws MalformedURLException {

		AppiumDriverManager.setUp("http://127.0.0.1:4723", "com.swaglabsmobileapp",
				"com.swaglabsmobileapp.MainActivity", "Android", "14.0", "uiautomator2");
		

	}
	
	private static void login(String username) {
		logger.info("_______________________________");
		logger.info("Eseguo il login con l'username: {}", username);
		AppiumDriverManager.sendKeys(AppiumBy.accessibilityId("test-Username"), durata, username);
		logger.info("Inserito username.");
		AppiumDriverManager.sendKeys(AppiumBy.accessibilityId("test-Password"), durata, "secret_sauce");
		logger.info("Inserito password.");
		AppiumDriverManager.click(AppiumBy.accessibilityId("test-LOGIN"), durata);
		logger.info("CLiccato sul login button.");
	}
	
	@Test
	@Order(2)
	public void standardUserTest() {
		login("standard_user");
		boolean verificaLogin = AppiumDriverManager.waitForElementDisappear(AppiumBy.accessibilityId("test-LOGIN"),
				durata);
		assertTrue(verificaLogin, "L'accesso non è avvenuto correttamente.");
		logger.info("L'accesso è avvenuto correttamente.");
		logger.info("Click su elementi da aggiungere al carello");
		AppiumDriverManager.click(By.xpath("(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]"),
				durata);
		logger.info("Click su carello");
		AppiumDriverManager.click((By.xpath(
				"//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView")),
				durata);		
		logger.info("Click su checkout");
		AppiumDriverManager.click((AppiumBy.accessibilityId("test-CHECKOUT")), durata);
		inserisciDati("Matteo", "Rossi", "80900");
		AppiumDriverManager.click(AppiumBy.accessibilityId("test-CONTINUE"), durata);
		logger.info("Click su continue ");
		AppiumDriverManager.eseguiSwipeOScroll(551, 2300, 569, 1525, true);
		AppiumDriverManager.click(AppiumBy.accessibilityId("test-FINISH"), durata);
		logger.info("Click su Finish");
		logger.info("Verifico CHECKOUT");
		WebElement messageFinish = AppiumDriverManager.waitForElementVisible(
				By.xpath("//android.widget.TextView[@text=\"THANK YOU FOR YOU ORDER\"]"), durata);

		if (messageFinish != null) {
			String expectedMessage = "Il messaggio non è quello atteso.";
			String actualMessage = AppiumDriverManager.findElement(
					By.xpath("//android.widget.TextView[@text=\"THANK YOU FOR YOU ORDER\"]"), true);
			assertEquals(actualMessage, expectedMessage, "Il messaggio non è quello atteso.");
			logger.info("Risultato corretto : " + actualMessage);
		} else {
			logger.error("Il CHECKOUT: non è completo!");
		}

	}
	
	private static void inserisciDati(String firstName, String lastName, String zipCode) {
		AppiumDriverManager.sendKeys(AppiumBy.accessibilityId("test-First Name"), durata, firstName);
		AppiumDriverManager.sendKeys(AppiumBy.accessibilityId("test-Last Name"), durata, lastName);
		AppiumDriverManager.sendKeys(AppiumBy.accessibilityId("test-Zip/Postal Code"), durata, zipCode);

		String actualFirstName = AppiumDriverManager.findElement(AppiumBy.accessibilityId("test-First Name"), false);
		String actualLastName = AppiumDriverManager.findElement(AppiumBy.accessibilityId("test-Last Name"), false);
		String actualZipCode = AppiumDriverManager.findElement(AppiumBy.accessibilityId("test-Zip/Postal Code"), false);

		logger.info("Expected First Name: " + firstName + " - Actual First Name: " + actualFirstName);
		logger.info("Expected Last Name: " + lastName + " - Actual Last Name: " + actualLastName);
		logger.info("Expected Zip Code: " + zipCode + " - Actual Zip Code: " + actualZipCode);
	}

	@Test
	@Order(3)
	public void lockedLoserTest() {
		login("locked_out_user");
		logger.info("Verifico messaggio di errore");
		WebElement errorMessageElement = AppiumDriverManager
				.waitForElementVisible(AppiumBy.accessibilityId("test-Error message"), durata);

		if (errorMessageElement != null) {
			String expectedErrorMessage = "Il messaggio di errore non è quello atteso.";
			String actualErrorMessage = AppiumDriverManager.findElement(
					By.xpath("//android.widget.TextView[@text=\"Sorry, this user has been locked out.\"]"), true);
			assertEquals(actualErrorMessage, expectedErrorMessage, "Il messaggio di errore non è quello atteso.");
			logger.info("Risultato corretto : " + actualErrorMessage);
		} else {
			logger.error("Login effettuato correttamente.");

		}
	}


	@Test
	@Order(4)
	public void problemUserTest() {
		login("problem_user");
		logger.info("Click su add cart");
		AppiumDriverManager.click(By.xpath("(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]"), durata);
		logger.info("Avvio la verifica sul pulsante");
		boolean isEnabled =  AppiumDriverManager.isButtonClickable(By.xpath("(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[2]"));
	        logger.info("Stato del pulsante: " + isEnabled);
	        logger.info("Uso assertFalse per verificare che il pulsante non sia abilitato");
	        assertFalse("Il pulsante dovrebbe essere disabilitato", isEnabled);
    }


	@AfterEach
	public void chiudiApp() {
		AppiumDriverManager.tearDown();
	}

}

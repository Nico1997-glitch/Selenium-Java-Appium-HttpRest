package it.smartpaper.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import it.smartpaper.selenium.helper.DriverHelper;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VerificaUserTest {
	private static WebDriver driver;
	protected static final Logger logger = LogManager.getLogger();
	private static final Duration durata = Duration.ofSeconds(5);

	@BeforeEach
	public void setUp() {
		driver = DriverHelper.getDriver();
	}

	@Test
	@Order(1)
	public void standardUserTest() {
		logger.info("Avvio standardUserTest");
		logger.info("Inserisco UserName");
		WebElement userName = DriverHelper.findElement(driver, "id", "user-name");
		DriverHelper.clearAndSendKeys(driver, userName, durata, "standard_user");
		logger.info("Inserisco Password");
		WebElement password = DriverHelper.findElement(driver, "id", "password");
		DriverHelper.clearAndSendKeys(driver, password, durata, "secret_sauce");
		logger.info("Faccio il click su Login");
		WebElement login = DriverHelper.findElement(driver, "id", "login-button");
		DriverHelper.click(driver, login, durata);
		logger.info("Faccio il click su Add to cart");
		WebElement addCart = DriverHelper.findElement(driver, "id", "add-to-cart-sauce-labs-bike-light");
		DriverHelper.click(driver, addCart, durata);
		logger.info("Faccio il click sul carello");
		WebElement carello = DriverHelper.findElement(driver, "classname", "shopping_cart_link");
		DriverHelper.click(driver, carello, durata);
		WebElement cartItem = DriverHelper.findElement(driver, "cssselector", "#item_0_title_link > div");
		assertTrue(cartItem.isDisplayed());
		// Ottieni il testo dell'elemento nel carello
		String cartItemText = cartItem.getText();
		// Confronta il testo dell'elemento nel carello con una stringa di test
		// specifica
		String expectedCartItemText = "Sauce Labs Bike Light"; // Aggiungi il testo specifico da confrontare
		assertEquals(expectedCartItemText, cartItemText);
		if (cartItem.isDisplayed()) {
			logger.info("L'elemento nel carello è stato trovato");
		} else {
			logger.warn("L'elemento nel carello non è stato trovato");
		}
		expectedCartItemText = "Sauce Labs Bike Light"; // Aggiungi il testo specifico da confrontare
		if (cartItemText.equals(expectedCartItemText)) {
			logger.info("Il testo dell'elemento nel carello corrisponde");
		} else {
			logger.warn("Il testo dell'elemento nel carello non corrisponde");
		}
	}

	@Test
	@Order(2)
	public void lockedOutUserTest() {
		logger.info("Avvio lockedOutUserTest");
		logger.info("Inserisco UserName");
		WebElement userName = DriverHelper.findElement(driver, "id", "user-name");
		DriverHelper.clearAndSendKeys(driver, userName, durata, "locked_out_user");
		logger.info("Inserisco Password");
		WebElement password = DriverHelper.findElement(driver, "id", "password");
		DriverHelper.clearAndSendKeys(driver, password, durata, "secret_sauce");
		logger.info("Faccio il click su Login");
		WebElement login = DriverHelper.findElement(driver, "id", "login-button");
		DriverHelper.click(driver, login, durata);
		// Verifica il messaggio di errore atteso
		WebElement errorMessage = driver.findElement(By.xpath("//*[@id='login_button_container']/div/form/div[3]"));
		String actualErrorMessage = errorMessage.getText();
		String expectedErrorMessage = "Epic sadface: Sorry, this user has been locked out.";
		assertEquals(expectedErrorMessage, actualErrorMessage);
		if (expectedErrorMessage.equals(actualErrorMessage)) {
			logger.info("Il testo sarà : Epic sadface: Sorry, this user has been locked out.");
		} else {
			logger.info("Il testo non corrisponde.");
		}
	}

	@Test
	@Order(3)
	public void problemUserTest() {
		logger.info("Avvio lockedOutUserTest");
		logger.info("Inserisco UserName");
		WebElement userName = DriverHelper.findElement(driver, "id", "user-name");
		DriverHelper.clearAndSendKeys(driver, userName, durata, "problem_user");
		logger.info("Inserisco Password");
		WebElement password = DriverHelper.findElement(driver, "id", "password");
		DriverHelper.clearAndSendKeys(driver, password, durata, "secret_sauce");
		logger.info("Faccio il click su Login");
		WebElement login = DriverHelper.findElement(driver, "id", "login-button");
		DriverHelper.click(driver, login, durata);
		WebElement itemNameElement = DriverHelper.findElement(driver, "className", "inventory_item_name");
		assertTrue(itemNameElement.isDisplayed(), "L'elemento con la classe inventory_item_name non è visualizzato.");
		logger.info("Verifica se il testo prima e dopo il clic sul prodotto è lo stesso");
		String testoPrima = itemNameElement.getText();
		WebElement prima = DriverHelper.findElement(driver, "id", "item_4_title_link");
		DriverHelper.click(driver, prima, durata);
		WebElement dopo = DriverHelper.findElement(driver, "xpath",
				"//*[@id='inventory_item_container']/div/div/div[2]/div[1]");
		String testoDopo = dopo.getText();
		assertEquals("Sauce Labs Backpack", testoPrima, testoDopo);
		if (testoPrima.equals(testoDopo)) {
			logger.info("Il testo prima e dopo il clic sul prodotto corrisponde.");
		} else {
			logger.info("Il testo prima e dopo il clic sul prodotto non corrisponde.");
		}

	}

	@Test
	@Order(4)
	public void performanceGlitchUserTest() {
		logger.info("Avvio performance_glitch_user");
		logger.info("Inserisco UserName");
		WebElement userName = DriverHelper.findElement(driver, "id", "user-name");
		DriverHelper.clearAndSendKeys(driver, userName, durata, "performance_glitch_user");
		logger.info("Inserisco Password");
		WebElement password = DriverHelper.findElement(driver, "id", "password");
		DriverHelper.clearAndSendKeys(driver, password, durata, "secret_sauce");
		logger.info("Faccio il click su Login");
		WebElement login = DriverHelper.findElement(driver, "id", "login-button");
		DriverHelper.click(driver, login, durata);
		DriverHelper.click(driver, driver.findElement(By.className("shopping_cart_link")), durata);
		WebDriverWait wait = new WebDriverWait(driver, durata);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_link")));
			logger.info("Elemento carello trovato correttamente.");
		} catch (NoSuchElementException e) {
			logger.error("Impossibile trovare l'elemento 'shopping_cart_link'.");
		}
		// wait che aspetta quando carica la pagina
		// verifica se presenta un elemento, e poi riprende l'eccezione e verifichi con
		// equals che ti stampi quella

	}

	@Test
	@Order(5)
	public void errorUserTest() {
		logger.info("Avvio error_user");
		logger.info("Inserisco UserName");
		WebElement userName = DriverHelper.findElement(driver, "id", "user-name");
		DriverHelper.clearAndSendKeys(driver, userName, durata, "error_user");
		logger.info("Inserisco Password");
		WebElement password = DriverHelper.findElement(driver, "id", "password");
		DriverHelper.clearAndSendKeys(driver, password, durata, "secret_sauce");
		logger.info("Faccio il click su Login");
		WebElement login = DriverHelper.findElement(driver, "id", "login-button");
		DriverHelper.click(driver, login, durata);
		logger.info("Effettua il primo clic sul pulsante Add to Cart");
		WebElement addToCart = DriverHelper.findElement(driver, "id", "add-to-cart-sauce-labs-bike-light");
		DriverHelper.click(driver, addToCart, durata);
		WebElement removeAdd = DriverHelper.findElement(driver, "id", "remove-sauce-labs-bike-light");
		assertTrue(removeAdd.isDisplayed());
		logger.info("L'elemento non è stato rimosso dal carrello.");
	}

	@Test
	@Order(6)
	public void visualUserTest() {
		logger.info("Avvio visual_userTest");
		logger.info("Inserisco UserName");
		WebElement userName = DriverHelper.findElement(driver, "id", "user-name");
		DriverHelper.clearAndSendKeys(driver, userName, durata, "visual_user");
		logger.info("Inserisco Password");
		WebElement password = DriverHelper.findElement(driver, "id", "password");
		DriverHelper.clearAndSendKeys(driver, password, durata, "secret_sauce");
		logger.info("Faccio il click su Login");
		WebElement login = DriverHelper.findElement(driver, "id", "login-button");
		DriverHelper.click(driver, login, durata);
		logger.info("Faccio il click su Add to cart");
		DriverHelper.click(driver, driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")), durata);
		logger.info("Faccio il click sul carello");
		DriverHelper.click(driver, driver.findElement(By.className("shopping_cart_link")), durata);
		logger.info("Eseguo il click su Checkout e inserisco datiCliente");
		WebElement checkOut = DriverHelper.findElement(driver, "id", "checkout");
		DriverHelper.click(driver, checkOut, durata);
		WebElement firstName = DriverHelper.findElement(driver, "id", "first-name");
		DriverHelper.clearAndSendKeys(driver, firstName, durata, "Ciccio");
		WebElement lastName = DriverHelper.findElement(driver, "id", "last-name");
		DriverHelper.clearAndSendKeys(driver, lastName, durata, "Pasticcio");
		WebElement lastNameField = driver.findElement(By.id("last-name"));
		String insertedText = lastNameField.getAttribute("value");
		assertEquals("Pasticcio", "Pasticcio", insertedText);
		logger.info("Il testo inserito corrisponde a quello atteso.");

	}

	@AfterEach
	public void chiudiBrowser() {
		DriverHelper.chiudiDriver();
	}
}

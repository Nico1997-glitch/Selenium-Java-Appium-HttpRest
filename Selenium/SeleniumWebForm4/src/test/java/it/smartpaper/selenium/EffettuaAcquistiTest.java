package it.smartpaper.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import it.smartpaper.selenium.helper.DriverHelper;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EffettuaAcquistiTest {
	private static WebDriver driver;
	protected static final Logger logger = LogManager.getLogger();
	private static final Duration durata = Duration.ofSeconds(15);
	

	@BeforeAll
	public static void setUp() {
		driver = DriverHelper.getDriver();
		logger.info("effettuoAccessoTest()");
		logger.info("Inserisco UserName");
		WebElement userName = DriverHelper.findElement(driver, "id", "user-name");
		DriverHelper.clearAndSendKeys(driver, userName, durata, "standard_user");
		logger.info("Inserisco Password");
		WebElement password = DriverHelper.findElement(driver, "id", "password");
		DriverHelper.clearAndSendKeys(driver, password, durata, "secret_sauce");
		logger.info("Faccio il click su Login");
		WebElement login = DriverHelper.findElement(driver, "id", "login-button");
		DriverHelper.click(driver, login, durata);

	}

	@Test
	@Order(2)
	public void riempiCarelloTest() {
		logger.info("Faccio il click su Add to cart");
		WebElement addCart = DriverHelper.findElement(driver, "id", "add-to-cart-sauce-labs-bike-light");
		DriverHelper.click(driver, addCart, durata);
		logger.info("Faccio il click sul carello");
		WebElement carello = DriverHelper.findElement(driver, "classname", "shopping_cart_link");
		DriverHelper.click(driver, carello, durata);
		logger.info("Faccio il click sul continua shopping");
		DriverHelper.click(driver, driver.findElement(By.id("continue-shopping")), durata);
		logger.info("Esegue uno scrollWeb ed inserisce il nuovo elemento");
		WebElement addCart1 = DriverHelper.findElement(driver, "id", "add-to-cart-test.allthethings()-t-shirt-(red)");
		DriverHelper.scrollWeb(driver, addCart1);
		DriverHelper.click(driver, addCart1, durata);
		logger.info("Ritorna sul carello");
		carello = DriverHelper.findElement(driver, "classname", "shopping_cart_link");
		DriverHelper.scrollWeb(driver, carello);
		DriverHelper.click(driver, carello, durata);
		WebElement elemento = driver.findElement(By.cssSelector("#item_0_title_link > div"));
		assertTrue(elemento.isDisplayed());
		logger.info("sauce-labs-bike-light è inserito correttamente al carrello.");
		WebElement elemento1 = driver.findElement(By.cssSelector("#item_3_title_link > div"));
		assertTrue(elemento1.isDisplayed());
		logger.info("test.allthethings()-t-shirt-(red) è inserito correttamente al carrello.");

	}

	@Test
	@Order(3)
	public void inserisciDatiTest() {
		logger.info("Eseguo il click su Checkout e inserisco datiCliente");
		DriverHelper.click(driver, driver.findElement(By.id("checkout")), durata);
		DriverHelper.clearAndSendKeys(driver, driver.findElement(By.id("first-name")), durata, "Ciccio");
		DriverHelper.clearAndSendKeys(driver, driver.findElement(By.id("last-name")), durata, "Pasticcio");
		DriverHelper.clearAndSendKeys(driver, driver.findElement(By.id("postal-code")), durata, "85044");
		WebElement firstNameField = driver.findElement(By.id("first-name"));
		WebElement lastNameField = driver.findElement(By.id("last-name"));
		WebElement postalCodeField = driver.findElement(By.id("postal-code"));
		assertEquals("Ciccio", firstNameField.getAttribute("value"));
		assertEquals("Pasticcio", lastNameField.getAttribute("value"));
		assertEquals("85044", postalCodeField.getAttribute("value"));
		logger.info("I campi sono stati compilati correttamente.");
	}

	// @Test
	@Order(4)
	public void completaAcqTest() {
		logger.info("Eseguo il click su continue e finish");
		DriverHelper.click(driver, driver.findElement(By.id("continue")), durata);
		DriverHelper.click(driver, driver.findElement(By.id("finish")), durata);
		WebElement confirmationMessage = driver.findElement(By.xpath("//div[@class='complete-header']"));
		assertTrue(confirmationMessage.isDisplayed());
		logger.info("Pagina di conferma dell'ordine caricata correttamente.");
	}
	
	@AfterAll
	public static void chiudiBrowser() {
		DriverHelper.chiudiDriver();
	}
}

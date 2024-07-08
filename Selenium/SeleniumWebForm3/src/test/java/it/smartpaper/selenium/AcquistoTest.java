package it.smartpaper.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import it.smartpaper.selenium.helper.DriverHelper;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AcquistoTest {
	private static WebDriver driver;
	protected static final Logger logger = LogManager.getLogger();
	private static final Duration durata = Duration.ofSeconds(15);

//	@BeforeAll
//	public static void setUp() {
//		driver = DriverHelper.getDriver();
//	}

	@BeforeAll
	public static void openSeleniumTest() {
		driver = DriverHelper.getDriver();
		driver.get("https://magento.softwaretestingboard.com/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement accetta = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("fc-button-label")));
		accetta.click();
		WebElement login = driver.findElement(By.className("authorization-link"));
		login.click();
		driver.get("https://magento.softwaretestingboard.com/customer/account/login/referer/");
		WebElement emailInput = driver.findElement(By.id("email"));
		emailInput.sendKeys("nuov_email@example.com");
		WebElement passwordInput = driver.findElement(By.id("pass"));
		passwordInput.sendKeys("Milano97!");
		WebElement submitButton = driver
				.findElement(By.cssSelector("fieldset[class='fieldset login'] div[class='primary'] span"));
		submitButton.click();
	}

	@Test
	@Order(1)
	public void testSceltaTipo() {
//		WebElement sceltaRiga = driver.findElement(By.id("ui-id-5"));
//		sceltaRiga.click();
		DriverHelper.click(driver, driver.findElement(By.id("ui-id-5")), durata);
//		tramite by.className "product-image-photo" del prodotto;
		WebElement scroll = driver.findElement(By.className("product-item-info"));
		DriverHelper.scrollWeb(driver, scroll);
//		WebElement clicca = driver.findElement(By.className("product-item-info"));
//		clicca.click();
		DriverHelper.click(driver, driver.findElement(By.className("product-item-info")), durata);
//		click sull'elemento
	}

	@Test
	@Order(2)
	public void scegliDettagli() {
		// tramite by.id "option-label-size-143-item-168" seleziono la tagliaSeleziona;
		// click sull'elemento
//		WebElement tagliaSeleziona = driver.findElement(By.cssSelector("#option-label-size-143-item-168"));
//		tagliaSeleziona.click();
		DriverHelper.click(driver, driver.findElement(By.cssSelector("#option-label-size-143-item-168")), durata);
		// tramite by.id "option-label-color-93-item-53" seleziono il coloreSeleziona;
		// click sull'elemento
//		WebElement coloreSeleziona = driver.findElement(By.id("option-label-color-93-item-52"));
//		coloreSeleziona.click();
		DriverHelper.click(driver, driver.findElement(By.id("option-label-color-93-item-52")), durata);
		// tramite by.id "product-addtocart-button" seleziono il carelloAggiungi;
		// click sull'elemento
//		WebElement carelloAggiungi = driver.findElement(By.id("product-addtocart-button"));
//		carelloAggiungi.click();
		DriverHelper.click(driver, driver.findElement(By.id("product-addtocart-button")), durata);
	}

	@Test
	@Order(3)
	public void testVaiSpedizione() {
		WebElement scroll = driver.findElement(
				By.cssSelector("body > div.page-wrapper > header > div.header.content > div.minicart-wrapper > a\r\n"));
		DriverHelper.scrollWeb(driver, scroll);
		DriverHelper.click(driver,
				driver.findElement(By.cssSelector(
						"body > div.page-wrapper > header > div.header.content > div.minicart-wrapper > a\r\n")),
				durata);
		DriverHelper.click(driver, driver.findElement(By.id("top-cart-btn-checkout")), durata);

	}

	@Test
	@Order(4)
	public void aggiungiDatiSpedizione() {
		// tra un istruzione e un atra inserisco un wait implicito
		// click sullicona del carello tramite by.className "counter qty"
		// click sull'elemento
		// click su checkoutPage tramite by.id "top-cart-btn-checkout"
		// click sull'elemento
		// inserisco i dati di spedizione tramite by. in base all'elemnto da inserire
		// click sull'elemento
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement streetAdress = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("street[0]")));
		WebElement scroll = driver.findElement(By.name("street[0]"));
		DriverHelper.scrollWeb(driver, scroll);
//		WebElement streetAdress = driver.findElement(By.name("street[1]"));
		streetAdress.sendKeys("CasaMia");
//		WebElement cityLife = driver.findElement(By.name("city"));
//		cityLife.sendKeys("Roma");
		DriverHelper.clearAndSendKeys(driver, driver.findElement(By.name("city")), durata, "Roma");
		WebElement dropCountry = driver.findElement(By.name("region_id"));
		dropCountry.click();
		Select dropdownC = new Select(dropCountry);
		dropdownC.selectByValue("13");
		String selectedOption = dropdownC.getFirstSelectedOption().getText();
		assertEquals("Colorado", selectedOption);
//        WebElement postCap = driver.findElement(By.name("postcode"));
//        postCap.sendKeys("85000");
		DriverHelper.clearAndSendKeys(driver, driver.findElement(By.name("postcode")), durata, "85000");
		WebElement dropState = driver.findElement(By.name("country_id"));
		dropState.click();
		Select dropdownS = new Select(dropState);
		dropdownS.selectByValue("BM");
		String selectedState = dropdownS.getFirstSelectedOption().getText();
		assertEquals("Bermuda", selectedState);
//        WebElement telephoNumeber = driver.findElement(By.name("telephone"));
//        telephoNumeber.sendKeys("333444555666");
		DriverHelper.clearAndSendKeys(driver, driver.findElement(By.name("telephone")), durata, "333444555666");
	}

	@Test
	@Order(5)
	public void confermaPagamento() {
		// tra un istruzione e un atra inserisco un wait implicito
		// seleziono con un checkRardio la modalità di selezione di spedizione
		// byclass(col col-method)
		// WebElement checkRadio = driver.findElement(By.className("col col-method"));
		// WebElement checkRadio =
		// wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("input")));
		// List<WebElement> radioClick =
		//*[@id="checkout-shipping-method-load"]/table/tbody/tr/td[1]/input
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));// driver.findElements(By.xpath("//*[@id=\"checkout-shipping-method-load\"]/table/tbody/tr/td[1]/input"));
//		WebElement checkRadio = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='checkout-shipping-method-load']/table/tbody/tr/td[1]/input")));
//		checkRadio.click();
		DriverHelper.click(driver,
				driver.findElement(By.xpath("//*[@id='checkout-shipping-method-load']/table/tbody/tr/td[1]/input")),
				durata);
		// WebElement conferma = driver.findElement(By.className("actions-toolbar"));
		// WebElement conferma = driver.findElement(By.class("Next"));
		// List<WebElement> listConferma =
		// driver.findElements(By.xpath("//*[@id='shipping-method-buttons-container']/div/button/span"));
//		WebElement conferma = driver.findElement(By.xpath("//*[@id='shipping-method-buttons-container']/div/button/span"));
//		conferma.click();
		DriverHelper.click(driver,
				driver.findElement(By.xpath("//*[@id='shipping-method-buttons-container']/div/button/span")), durata);
//		 List<WebElement> listPlaceOrder =
//		 driver.findElements(By.xpath("//*[@id='checkout-payment-method-load']/div/div/div[2]/div[2]/div[4]/div/button/span"));
//		WebElement placeOrder = driver.findElement(By.xpath("//*[@id='checkout-payment-method-load']/div/div/div[2]/div[2]/div[4]/div/button/span"));
//		placeOrder.click();
		DriverHelper.click(driver,
				driver.findElement(By
						.xpath("//*[@id='checkout-payment-method-load']/div/div/div[2]/div[2]/div[4]/div/button/span")),
				durata);

	}
	// *[@id="checkout-payment-method-load"]/div/div/div[2]/div[2]/div[4]/div/button/span
}

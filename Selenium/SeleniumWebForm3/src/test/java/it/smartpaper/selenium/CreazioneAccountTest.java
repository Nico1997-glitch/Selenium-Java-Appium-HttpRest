package it.smartpaper.selenium;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import it.smartpaper.selenium.helper.DriverHelper;

public class CreazioneAccountTest {
	private static WebDriver driver;
	protected static final Logger logger = LogManager.getLogger();

	@BeforeAll
	public static void setUp() {
		driver = DriverHelper.getDriver();
	}

	@BeforeEach
	public void openSeleniumTest() {
		logger.info("Apro il sito web");
		driver.get("https://magento.softwaretestingboard.com/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		logger.info("Attendo e accetto il banner");
		WebElement accetta = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("fc-button-label")));
		accetta.click();
	}

	@Test
	public void AccountCreation() {
		driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
		WebElement scroll = driver.findElement(By.id("firstname"));
		DriverHelper.scrollWeb(driver, scroll);
		logger.info("Inserisco Nome");
		WebElement firstName = driver.findElement(By.id("firstname"));
		firstName.sendKeys("Nillo");
		logger.info("Inserisco cognome");
		WebElement lastName = driver.findElement(By.id("lastname"));
		lastName.sendKeys("Rossi");
		logger.info("Inserisco la mail");
		WebElement emailInput = driver.findElement(By.id("email_address"));
		emailInput.sendKeys("nuov_email@example.com");
		logger.info("Inserisco la password");
		WebElement passwordInput = driver.findElement(By.id("password"));
		passwordInput.sendKeys("Milano97!");
		logger.info("Inserisco password conferma");
		WebElement confirmPasswordInput = driver.findElement(By.id("password-confirmation"));
		confirmPasswordInput.sendKeys("Milano97!");
		scroll = driver.findElement(By.cssSelector("button[title='Create an Account'] span"));
		DriverHelper.scrollWeb(driver, scroll);
		WebElement submitButton = driver.findElement(By.cssSelector("button[title='Create an Account'] span"));

		// Invia il modulo
		submitButton.click();

		// Verifica il successo della registrazione
		WebElement successMessage = driver.findElement(By.className("messages"));
		assertEquals("Thank you for registering with Main Website Store.", successMessage.getText());
		logger.info("Processo andato a buonFine");

	}

}

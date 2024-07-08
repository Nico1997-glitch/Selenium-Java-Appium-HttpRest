package it.smartpaper.selenium;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
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
public class LoginTest {
	private static WebDriver driver;
	protected static final Logger logger = LogManager.getLogger();

	@BeforeAll
	public static void setUp() {
		driver = DriverHelper.getDriver();
		logger.info("Apro il sitoWeb");
		driver.get("https://magento.softwaretestingboard.com/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement accetta = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("fc-button-label")));
		logger.info("Click su accetta");
		accetta.click();
	}

	@BeforeEach
	public void openSeleniumTest() {
		logger.info("Eseguo il metodo openSeleniumTest");
	
	}

	@Test
	@Order(2)
	public void accountLogin() {
		logger.info("Avvio il metodo accontLogin");
		WebElement login = driver.findElement(By.className("authorization-link"));
		login.click();
		driver.get("https://magento.softwaretestingboard.com/customer/account/login/referer/");
		logger.info("Inserisco email");
		WebElement emailInput = driver.findElement(By.id("email"));
		emailInput.sendKeys("nuov_email@example.com");
		logger.info("Inserisco Password");
		WebElement passwordInput = driver.findElement(By.id("pass"));
		passwordInput.sendKeys("Milano97!");
		WebElement submitButton = driver.findElement(By.cssSelector("fieldset[class='fieldset login'] div[class='primary'] span"));
		submitButton.click();

	}
	
}

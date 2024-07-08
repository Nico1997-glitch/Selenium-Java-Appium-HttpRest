package it.smartpaper.Pta;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import it.smartpaper.selenium.helper.WebDriverManager;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PtaLoginTest {
	protected static final Logger logger = LogManager.getLogger();
	private static final Duration durata = Duration.ofSeconds(15);

	@BeforeEach
	public void setUp() {
		logger.info("Creo un nuovo driver");
		WebDriverManager.getDriver();
		logger.info("Driver creato correttamente");
		WebDriverManager.click(By.xpath("//*[@id='loop-container']/div/article/div[2]/div[1]/div[1]/p/a"), durata);
		logger.info("Click su Test Login Page eseguito correttamente");
	}

	// Test case 1: Positive LogIn test
	@Test
	@Order(1)
	public void testCase1() {
		logger.info("Inserisco l'username e password");
		WebDriverManager.clearAndSendKeys(By.id("username"), durata, "student");
		WebDriverManager.clearAndSendKeys(By.id("password"), durata, "Password123");
		logger.info("Click su Submit");
		WebDriverManager.click(By.id("submit"), durata);
		String currentURL = WebDriverManager.getCurrentURL();
		assertTrue(currentURL.contains("practicetestautomation.com/logged-in-successfully/"),
				"L'URL non contiene la stringa 'practicetestautomation.com/logged-in-successfully/'");
		logger.info("Verificato che l'URL contenga il testo previsto");
		String pageSource = WebDriverManager.getPageSource();
		assertTrue(pageSource.contains("Congratulations") || pageSource.contains("successfully logged in"),
				"La pagina non contiene il testo previsto");
		logger.info("Verificato che la pagina contenga il testo previsto");
		assertTrue(WebDriverManager.findElement(By.linkText("Log out")).isDisplayed(), "Il pulsante Logout non è visualizzato");
		logger.info("Verificato che il pulsante Logout sia visualizzato");

	}

	// Test case 2: Negative username test
	@Test
	@Order(2)
	public void testCase2() {
		logger.info("Inserisco l'username e password");
		WebDriverManager.clearAndSendKeys(By.id("username"), durata, "incorrectUser");
		WebDriverManager.clearAndSendKeys(By.id("password"), durata, "Password123");
		logger.info("Click su Submit");
		WebDriverManager.click(By.id("submit"), durata);
		String error = WebDriverManager.saveTextFromElement(By.id("error"));
        assertFalse(error.equals("Your username is invalid!"), "Il messaggio di errore è diverso da quello atteso");
        logger.info("Il messaggio di errore è stato visualizzato correttamente.");
        
	}
	
	
	//Test case 3: Negative password test
	@Test
	@Order(3)
	public void testCase3(){
		logger.info("Inserisco l'username e password");
		WebDriverManager.clearAndSendKeys(By.id("username"), durata, "student");
		WebDriverManager.clearAndSendKeys(By.id("password"), durata, "incorrectPassword ");
		logger.info("Click su Submit");
		WebDriverManager.click(By.id("submit"), durata);
		String errorMessage = WebDriverManager.saveTextFromElement(By.id("error"));
        assertFalse(errorMessage.equals("Your password is invalid!"), "Il messaggio di errore è diverso da quello atteso");
        logger.info("Il messaggio di errore è stato visualizzato correttamente.");
		
	}
	
	@AfterEach
	public void chiudiBrowser() {
		WebDriverManager.chiudiDriver();
	}
}

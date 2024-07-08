package it.smartpaper.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import it.smartpaper.selenium.helper.DriverHelper;

public class AssertionTest {
	
	private static WebDriver driver;
	protected static final Logger logger = LogManager.getLogger();
	private static final Duration durata = Duration.ofSeconds(15);
	
	@BeforeEach
	public void setUp() {
		driver = DriverHelper.getDriver();
	}
	
    @Test
    public void testLoginPage() {
        logger.info("Trova gli elementi della pagina di login");
        logger.info("Inserisco i Dati Utente");
        WebElement usernameInput = DriverHelper.findElement(driver, "id", "user-name"); 
        DriverHelper.clearAndSendKeys(driver, usernameInput, durata, "standard_user");		
        WebElement passwordInput = DriverHelper.findElement(driver, "id", "password");
        DriverHelper.clearAndSendKeys(driver, passwordInput, durata, "secret_sauce");
        WebElement loginButton = DriverHelper.findElement(driver, "id", "login-button");
 

        // Verifica che gli elementi siano presenti
        assertNotNull(usernameInput);
        assertNotNull(passwordInput);
        assertNotNull(loginButton);
        logger.info("Dati Login inseriti correttamente.");

        loginButton.click();
        logger.info("Effettuato il login.");

        WebElement productsTitle = driver.findElement(By.className("title"));
        assertEquals("Products", productsTitle.getText());
        logger.info("L'utente è loggato correttamente.");

        // Verifica che il titolo della pagina corrisponda
        assertEquals("Swag Labs", driver.getTitle());

        // Verifica che l'utente sia effettivamente loggato
        assertTrue(driver.getCurrentUrl().contains("inventory.html"));

        // Log di successo
        logger.info("Login effettuato con successo.");
    }
    
	@AfterEach
	public void chiudiBrowser() {
		DriverHelper.chiudiDriver();
	}
}

package it.smartpaper.WebForm5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import it.smartpaper.selenium.helper.WebDriverManager;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AlertsTest {
	protected static final Logger logger = LogManager.getLogger();
	private static final Duration durata = Duration.ofSeconds(10);
	
	@BeforeAll
	public static void setUp() {
		WebDriverManager.getDriver();
		logger.info("Creo un nuovo driver");
		WebDriverManager.openPage("https://demoqa.com/alerts");
	}
	
	
	@Test
	@Order(1)
	public void alertButtonTest() {
		logger.info("Avvio alertButtonTest");
		WebDriverManager.waitForElementVisibleAndClick(By.id("alertButton"), durata);
		String alertText = WebDriverManager.handleAlert(true, "You clicked a button");
		// Verifica che il testo dell'alert sia corretto
        assertEquals(alertText, "You clicked a button");
	}
	
	@Test
	@Order(2)
	public void timerAlertButtonTest() {
		logger.info("Avvio timerAlertButtonTest");
		WebDriverManager.waitForElementVisibleAndClick(By.id("timerAlertButton"), durata);
        boolean isAlertPresent =  WebDriverManager.isAlertPresent();
        if (isAlertPresent) {
            logger.info("Alert presente.");
        } else {
            logger.info("Alert non presente.");
        }
        assertTrue(isAlertPresent, "L'alert non è stato trovato.");
        String alertText = WebDriverManager.handleAlert(true, "This alert appeared after 5 seconds");
        assertEquals(alertText, "This alert appeared after 5 seconds");
    }
	
	//Vedere
	@Test
	@Order(3)
	public void confirmButtonTest() {
		logger.info("Avvio confirmButtonTest");
		WebDriverManager.waitForElementVisibleAndClick(By.id("confirmButton"), durata);
		WebDriverManager.handleAlert(true, "Do you confirm action?");
		WebElement succes = WebDriverManager.findElement(By.id("confirmResult"));
		String leggiTesto = succes.getText();
		logger.info("Testo recuperato " + leggiTesto);
		assertEquals(leggiTesto, "You selected Ok");
		logger.info("Test eseguito correttamente.");
		
		}
	
	@Test
	@Order(4)
	public void promtButtonTest() {
		logger.info("Avvio promtButtonTest");
		WebDriverManager.waitForElementVisibleAndClick(By.id("promtButton"), durata);
		WebDriverManager.sendKeysToAlert("Nicola");
	}

	//@AfterAll
	public static void chiudiBrowser() {
		WebDriverManager.chiudiDriver();
	}
}

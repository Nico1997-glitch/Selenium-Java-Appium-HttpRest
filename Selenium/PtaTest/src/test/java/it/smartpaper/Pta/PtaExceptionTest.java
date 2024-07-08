package it.smartpaper.Pta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import it.smartpaper.selenium.helper.WebDriverManager;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PtaExceptionTest {
	protected static final Logger logger = LogManager.getLogger();
	private static final Duration durata = Duration.ofSeconds(10);

	@BeforeEach
	public void setUp() {
		logger.info("Creo un nuovo driver");
		WebDriverManager.getDriver();
		WebDriverManager.openPage();
		logger.info("Driver creato correttamente");
		WebDriverManager.click(By.xpath("//*[@id='loop-container']/div/article/div[2]/div[2]/div[1]/p/a"), durata);
		logger.info("Click su Test Exceptions Page eseguito correttamente");
	}

	@Test
	@Order(1)
	public void NoSuchElementExceptionTest() {
		try {
			logger.info("Fare clic su ADD");
			WebDriverManager.click(By.id("add_btn"), durata);
			// Verificare che il campo di input della riga 2 sia visualizzato
			logger.info("Attendere che la seconda riga 2 venga visualizzata");
			WebElement row2Input = WebDriverManager.waitForElementVisible(By.xpath("//*[@id='row2']/input"), durata);
			assertNotNull(row2Input, "Il campo di input della riga 2 non è stato trovato");
			logger.info("Seconda riga caricata correttamente");
		} catch (NoSuchElementException e) {
			logger.error("Elemento non trovato: " + e.getMessage());
			fail("Test fallito: Elemento non trovato");
		} catch (TimeoutException e) {
			logger.error("Timeout durante l'attesa: " + e.getMessage());
			fail("Test fallito: Timeout durante l'attesa della visualizzazione della seconda riga");
		}
	}

	@Test
	@Order(2)
	public void ElementNotInteractableException() {
		try {
			WebDriverManager.click(By.id("add_btn"), durata);
			logger.info("Inserimento testo nella seconda riga");
			WebDriverManager.clearAndSendKeys(By.cssSelector("div[id='row2'] input[type='text']"), durata,
					"Testo di prova");
			logger.info("Eseguo il click per salvare");
			// WebDriverManager.click(By.name("Salva"), durata);
			WebDriverManager.click(By.xpath("//div[@id='row2']//button[@id='save_btn']"), durata);
			// Verifica il testo salvato
			String savedText = WebDriverManager.saveTextFromElement(By.id("confirmation"));
			assertEquals("Row 2 was saved", savedText, "Testo salvato non corrisponde");
			logger.info("Testo nella seconda riga salvato correttamente");
		} catch (NoSuchElementException e) {
			logger.error("Elemento non trovato: " + e.getMessage());
			fail("Test fallito: Elemento non trovato");
		} catch (ElementNotInteractableException e) {
			logger.error("Elemento non interagibile: " + e.getMessage());
			fail("Test fallito: Elemento non interagibile");
		}
	}

	@Test
	@Order(3)
	public void InvalidElementStateException() {
		try {
			logger.info("Clicca sul pulsante input");
			WebDriverManager.click(By.xpath("//div[@id='row1']//button[@id='edit_btn']"), durata);
			// Cancella il campo di immissione
			logger.info("Cancella il campo di immissione e digito il nuovo testo");
			WebElement inputField = WebDriverManager.clearAndSendKeys(By.xpath("//*[@id=\"row1\"]/input"), durata, "Pollo");
			WebDriverManager.click(By.xpath("//div[@id='row1']//button[@id='save_btn']"), durata);
			// Verifica che il testo sia cambiato
			logger.info("Verifica che il testo sia cambiato");
			assertEquals("Pollo", inputField.getAttribute("value"), "Il testo non corrisponde");
			logger.info("Test completato con successo");
		} catch (InvalidElementStateException e) {
			logger.error("Errore durante l'interazione con il campo di input: " + e.getMessage());
			fail("Test fallito: Impossibile interagire con il campo di input");
		} catch (NoSuchElementException e) {
			logger.error("Elemento non trovato: " + e.getMessage());
			fail("Test fallito: Elemento non trovato");
		}
	}

	@Test
	@Order(4)
	public void testInstructionElementRemoval() {
		try {

			logger.info("Trova l'elemento del testo delle istruzioni");
			WebDriverManager.waitForElementVisible(By.id("instructions"), durata);
			logger.info("Premi il pulsante Aggiungi");
			WebDriverManager.click(By.id("add_btn"), durata);
			logger.info("Attendi che l'elemento delle istruzioni scompaia");
			WebDriverManager.waitForElementDisappear(By.id("instructions"), durata);
			logger.info("Verifica che l'elemento delle istruzioni non sia più visualizzato");
			assertThrows(NoSuchElementException.class, () -> {
				WebDriverManager.findElement(By.id("instructions"));
			});

			logger.info("Test completato con successo");
		} catch (StaleElementReferenceException e) {
			logger.error("Elemento di riferimento obsoleto: " + e.getMessage());
			fail("Test fallito: Elemento di riferimento obsoleto");
		} catch (NoSuchElementException e) {
			logger.error("Elemento non trovato: " + e.getMessage());
			fail("Test fallito: Elemento non trovato");
		}
	}

	@Test
	@Order(5)
	public void testSecondInputFieldVisibility() {
		try {
			logger.info("Fare clic sul pulsante Aggiungi");
			WebDriverManager.click(By.id("add_btn"), durata);
			logger.info("Attendere che il secondo campo di input venga visualizzato");
			WebElement secondInput = WebDriverManager.waitForElementVisible(By.xpath("//*[@id='row2']/input"), durata);
			logger.info("Verificare che il secondo campo di input sia visualizzato");
			assertTrue(secondInput.isDisplayed(), "Il secondo campo di input non è visualizzato");
			logger.info("Test completato con successo");
		} catch (TimeoutException e) {
			logger.error("Timeout durante l'attesa: " + e.getMessage());
			fail("Test fallito: Timeout durante l'attesa della visualizzazione del secondo campo di input");
		} catch (NoSuchElementException e) {
			logger.error("Elemento non trovato: " + e.getMessage());
			fail("Test fallito: Elemento non trovato");
		}
	}

	@AfterEach
	public void chiudiBrowser() {
		logger.info("________________________________");
		WebDriverManager.chiudiDriver();
	}
}

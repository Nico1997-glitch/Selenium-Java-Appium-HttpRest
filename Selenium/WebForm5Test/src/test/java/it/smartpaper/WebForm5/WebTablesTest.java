package it.smartpaper.WebForm5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import it.smartpaper.selenium.helper.WebDriverManager;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WebTablesTest {
	protected static final Logger logger = LogManager.getLogger();
	private static final Duration durata = Duration.ofSeconds(10);

	//@BeforeEach
	@BeforeAll
	public static void setUp() {
		logger.info("Creo un nuovo driver");
		WebDriverManager.getDriver();
		WebDriverManager.openPage("https://demoqa.com/webtables");
	}

	@Test
	@Order(1)
	public void contaRigheTest() {
		WebElement tableElement = WebDriverManager.findElement(By.className("rt-tbody"));
		// Estrai tutte le righe dalla tabella
		List<WebElement> righe = tableElement.findElements(By.className("rt-tr-group"));
		// Conta le righe piene
		int righeCount = 0;
		for (WebElement riga : righe) {
			if (!riga.getText().trim().isEmpty()) {
				righeCount++;
			}
		}
		logger.info("Numero di righe piene nella tabella: " + righeCount);
	}

//	@Test
//	@Order(2)
//	public void leggiTableColonne() {
//		// Trova la tabella
//		WebElement table = WebDriverManager.findElement(By.className("rt-table"));
//		List<WebElement> righe = table.findElements(By.className("rt-tbody"));
//		for (WebElement riga : righe) {
//				List<WebElement> celle = riga.findElements(By.className("rt-tr -odd"));
//				if (righe.isEmpty()) {
//				for (WebElement cella : celle) {
//					logger.info("Contenuto della cella: " + cella.getText());
//				}
//			}
//		}
//	}
//	
	@Test
	@Order(2)
	public void leggiTableColonna() {
		// Trova la tabella
		WebElement table = WebDriverManager.findElement(By.className("rt-table"));
		List<WebElement> righe = table.findElements(By.className("rt-tr-group"));
		WebElement rigaDesiderata = righe.get(0);
	    List<WebElement> celle = rigaDesiderata.findElements(By.xpath(".//div[@class='rt-td']"));
	    // Stampa il contenuto delle celle della riga
	    for (WebElement cella : celle) {
	    		logger.info("Contenuto della cella: " + cella.getText());
	    		assertEquals("Vega", cella.getText());
	    }
	}


	@Test
	@Order(3)
	public void testInserimentoDatiCorretto() {
		// Esegui l'inserimento dei dati e verifica
		inserisciDaiSubmitEVerifica();
	}

	private void inserisciDaiSubmitEVerifica() {
		WebDriverManager.waitForElementVisibleAndClick(By.id("addNewRecordButton"), durata);
		logger.info("Inserisco Dati da Inserire");
		WebDriverManager.clearAndSendKeys(By.id("firstName"), durata, "Francesco");
		WebDriverManager.clearAndSendKeys(By.id("lastName"), durata, "Nero");
		WebDriverManager.clearAndSendKeys(By.id("userEmail"), durata, "NeroF@cicciolino.it");
		WebDriverManager.clearAndSendKeys(By.id("age"), durata, "23");
		WebDriverManager.clearAndSendKeys(By.id("salary"), durata, "13000");
		WebDriverManager.clearAndSendKeys(By.id("department"), durata, "Informatica");
		// String firstNameCompilato =
		// WebDriverManager.findElement(By.id("firstName")).getText();
		// Verifica che i campi siano stati riempiti correttamente
		// assertFieldTextEquals(firsNameCompilato, "Francesco");
		//WebElement table = WebDriverManager.findElement(By.className("rt-table"));
		//WebElement rigaDesiderata = table.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]"));
		assertFieldTextEquals(By.id("firstName"), "Francesco");
		assertFieldTextEquals(By.id("lastName"), "Nero");
		assertFieldTextEquals(By.id("userEmail"), "NeroF@cicciolino.it");
		assertFieldTextEquals(By.id("age"), "23");
		assertFieldTextEquals(By.id("salary"), "13000");
		assertFieldTextEquals(By.id("department"), "Informatica");
		logger.info("Click su Submit");
		WebDriverManager.waitForElementVisibleAndClick(By.id("submit"), durata);
		logger.info("Click su Submit Effettuato");
		contaRigheTest();
	}

	private void assertFieldTextEquals(By elemenBy, String expectedText) {
		WebElement field = WebDriverManager.findElement(elemenBy);
		String actualText = field.getAttribute("value");
		if (actualText.isEmpty()) {
			actualText = field.getText();
		}
		assertEquals(expectedText, actualText, "Il campo " + elemenBy.toString() + " non contiene il testo previsto.");
	}
	
	@Test
	@Order(4)
	public void testEditAndCleanRiga() {
		logger.info("Inserisco Dati da Modificare");
		WebDriverManager.waitForElementVisibleAndClick(By.className("mr-2"), durata);
		WebDriverManager.clearAndSendKeys(By.id("userEmail"), durata, "fattiMiei@liberato.it");
		WebDriverManager.clearAndSendKeys(By.id("age"), durata, "18");
		logger.info("Click su Submit");
		WebDriverManager.waitForElementVisibleAndClick(By.id("submit"), durata);
		logger.info("Click su Submit Effettuato");
		String testoEmailModificato = WebDriverManager.findElement(By.id("userEmail")).getText();
		assertEquals("fattiMiei@liberato.it", testoEmailModificato);
		logger.info("La riga è stata modificata correttamente");
	}
	
	@Test
	@Order(5)
    public void isRowDeleted() {
		WebDriverManager.waitForElementVisibleAndClick(By.id("delete-record-2"), durata);
        // Verifica se la riga è stata eliminata controllando se esiste ancora l'elemento che la rappresenta
        try {
            WebDriverManager.findElement(By.id("record-2"));
            logger.info("La riga esiste ancora");
        } catch (org.openqa.selenium.NoSuchElementException e) {
        	logger.info("La riga è stata eliminata correttamente");
        }
    }
	
	@Test
	@Order(6)
	public void testSearchElement() {
		boolean elementoTrovato = searchElement("Francesco Nero");

		// Verifica se l'elemento è stato trovato
		assertTrue(elementoTrovato, "L'elemento specifico non è stato trovato nella tabella.");
	}

	private boolean searchElement(String elementoRicerca) {
		// Trova la tabella
		WebElement table = WebDriverManager.findElement(By.className("rt-table"));
		// Ottieni tutte le righe della tabella
		List<WebElement> righe = table.findElements(By.className("rt-tr-group"));

		// Itera attraverso tutte le righe
		for (WebElement riga : righe) {
			// Ottieni le celle nella riga
			List<WebElement> celle = riga.findElements(By.className("rt-td"));
			// Itera attraverso tutte le celle
			for (WebElement cella : celle) {
				// Controlla se il testo della cella corrisponde all'elemento di ricerca
				if (cella.getText().equals(elementoRicerca)) {
					logger.info("Elemento non trovato: " + elementoRicerca);
					return false;
				}
			}
		}
		logger.info("Elemento trovato: " + elementoRicerca);
		return true;
	}
	
	@Test
	@Order(7)
	public void testInserimentoDatiCorrettoEControlloElemento() {
	    // Esegui l'inserimento dei dati e verifica
	    inserisciDaiSubmitEVerifica();
	    // Ricerca un elemento dopo l'inserimento
	    cercaElementoDopoInserimento();
	}

	private void cercaElementoDopoInserimento() {
	    logger.info("Cerca elemento dopo l'inserimento");
	    // Esegui la tua logica per trovare l'elemento dopo l'inserimento
	    boolean elementoTrovato = searchElement("Francesco Nero");
	    if (elementoTrovato) {
	        logger.info("Elemento trovato dopo l'inserimento.");
	    } else {
	        logger.warn("L'elemento non è stato trovato dopo l'inserimento.");
	    }
	}
	
	//@AfterEach
	@AfterAll
	public static void chiudiBrowser() {
		WebDriverManager.chiudiDriver();
	}
}





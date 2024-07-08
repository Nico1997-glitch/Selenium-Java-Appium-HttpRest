package it.smartpaper.PtaImplementationTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import it.smartpaper.DriverManagerHelper.DriverManagerHelper;
import it.smartpaper.PageFactory.HomePage;
import it.smartpaper.PageFactory.LoginPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PtaImplementationTest {
	protected static final Logger logger = LogManager.getLogger();

	@BeforeEach
	public void setUp() {
		logger.info("Creo un nuovo driver");
		DriverManagerHelper.getDriver();
		logger.info("Driver creato correttamente");
	}

	public static HomePage inserisciUser(String user, String password) {
		DriverManagerHelper.openPage("https://practicetestautomation.com/practice-test-login/");		
		logger.info("Effettuo il login");
		LoginPage loginPage = new LoginPage("https://practicetestautomation.com/practice-test-login/");
		logger.info("Inserisco l'username e password");
		HomePage homePage = loginPage.loginWithCredentials(user, password);
		return homePage;
	}

	// Test case 1: Positive LogIn test
	@Test
	@Order(1)
	public void testCase1() {
		HomePage homePage = inserisciUser("student", "Password123");
		logger.info("Verifico che la pagina contenga il testo previsto");
		String pageSource = homePage.readText();
		assertTrue(pageSource.contains("Logged In Successfully"), "La pagina non contiene il testo previsto");
		logger.info("La pagina contiene il testo previsto");
		boolean logOutDisplay = homePage.verifyBotton();
		assertTrue(logOutDisplay, "Il pulsante Logout non è visualizzato");
		logger.info("Il pulsante Logout è visualizzato");
	}

	// Test case 2: Negative username test
	@Test
	@Order(2)
	public void testCase2() {
		HomePage homePage = inserisciUser("incorrectUser", "Password123");
		String error = homePage.readError();
		boolean verifyText = homePage.verifyCondition();
		assertFalse(verifyText, "L'username è corretto");
		logger.info("L'errore sarà : " + error);

	}

	// Test case 3: Negative password test
	@Test
	@Order(3)
	public void testCase3() {
		HomePage homePage = inserisciUser("student", "incorrectPassword");
		String errorMessage = homePage.readError();
		boolean verifyText = homePage.verifyCondition();
		assertFalse(verifyText, "La password è corretta");
		logger.info("L'errore sarà : " + errorMessage);

	}

	@AfterEach
	public void chiudiBrowser() {
		DriverManagerHelper.chiudiDriver();
	}

}

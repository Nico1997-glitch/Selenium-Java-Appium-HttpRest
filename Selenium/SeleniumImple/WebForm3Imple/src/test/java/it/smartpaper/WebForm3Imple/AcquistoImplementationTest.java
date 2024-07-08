package it.smartpaper.WebForm3Imple;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import it.smartpaper.DriverManagerHelper.DriverManagerHelper;
import it.smartpaper.PageFactory.AccountPage;
import it.smartpaper.PageFactory.CheckoutPage;
import it.smartpaper.PageFactory.HomePage;
import it.smartpaper.PageFactory.LoginPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AcquistoImplementationTest {

	protected static final Logger logger = LogManager.getLogger();

	@BeforeAll
	public static void setUp() {
		logger.info("________________________________________");
		logger.info("Creo un nuovo driver");
		DriverManagerHelper.getDriver();
		logger.info("Driver creato correttamente");
	}

	@Test
	@Order(1)
	public void AccountCreation() {
		AccountPage accountPage = new AccountPage("https://magento.softwaretestingboard.com/customer/account/create/");
		logger.info("Inserisco dati per la creazione dell'account");
		accountPage.creationAccount("Mario", "Rossi", "tri@domain.com", "Rossi23!", "Rossi23!");
		accountPage.clickOnSubmit();
		String expectMessages = accountPage.readMessages();
		assertEquals("Thank you for registering with Main Website Store.", expectMessages);
		logger.info("Complimenti : " + expectMessages);
	}

	@Test
	@Order(2)
	public void accountLogin() {
		LoginPage loginPage = new LoginPage("https://magento.softwaretestingboard.com/");
		logger.info("Inserisco l'username e password");
		HomePage login = loginPage.loginWithCredentials("tri@domain.com", "Rossi23!");
		logger.info("Seleziono i prodotti desiderati");
		login.clickAndWaitElemnt();
		logger.info("Clicco per andare al checkOut");
	}

	@Test
	@Order(3)
	public void addShipping() {
		CheckoutPage checkOut = new CheckoutPage("https://magento.softwaretestingboard.com/checkout/");
		logger.info("Inserisco i dati per la spedizione");
		checkOut.operationShipping("CasaMia", "Roma", "85000", "333444555666");
		logger.info("Verifico la città scelta");
		String città = checkOut.selectDropdownRegion("13");
		assertEquals("Colorado", città);
		logger.info("La città selezionata : " + città);
		logger.info("Verifico la regione scelta");
		String regione = checkOut.selectDropdownCountry("BM");
		assertEquals("Bermuda", regione);
		logger.info("La Regione selezionata : " + regione);
		logger.info("Concludo l'acquisto");
		checkOut.click();
	}
	
	@AfterAll
	public static void closeWeb() {
		DriverManagerHelper.chiudiDriver();
		logger.info("Grazie è arrivederci il proramma funziona");
	}	
}

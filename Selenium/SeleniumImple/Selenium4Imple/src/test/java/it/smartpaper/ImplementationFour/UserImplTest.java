package it.smartpaper.ImplementationFour;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import it.smartpaper.DriverManagerHelper.DriverManagerHelper;
import it.smartpaper.PageFactory.CheckoutPage;
import it.smartpaper.PageFactory.HomePage;
import it.smartpaper.PageFactory.LoginPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserImplTest {

	protected static final Logger logger = LogManager.getLogger();

	@BeforeEach
	public void setUp() {
		logger.info("________________________________________");
		logger.info("Creo un nuovo driver");
		DriverManagerHelper.getDriver();
		logger.info("Driver creato correttamente");
	}

	public static HomePage inserisciUser(String user) {
		logger.info("Effettuo il login per: " + user);
		LoginPage loginPage = new LoginPage("https://www.saucedemo.com/");
		logger.info("Inserisco l'username e password");
		HomePage homePage = loginPage.loginWithCredentials(user, "secret_sauce");
		return homePage;
	}

	@Test
	@Order(1)
	public void standardUserTest() {
		HomePage homePage = inserisciUser("standard_user");
		logger.info("Faccio il click su Add to cart");
		homePage.clickAndWait(1);
		logger.info("Faccio il click sul carello");
		homePage.clickAndWait(2);
		boolean verfica = homePage.verifyBottonCart();
		if (verfica == true) {
			logger.info("L'elemento nel carello è stato trovato");
		} else {
			logger.warn("L'elemento nel carello non è stato trovato");
		}
		logger.info("Ottengo il testo dell'elemento nel carello");
		String cartItemText = homePage.getTextCart();
		logger.info("Confronto il testo dell'elemento nel carello");
		assertEquals(cartItemText, "Sauce Labs Bike Light", "Nel carello non esiste nulla con il nome voluto");
		logger.info("Nel carello c'è : " + cartItemText);
	}

	@Test
	@Order(2)
	public void lockedOutUserTest() {
		HomePage homePage = inserisciUser("locked_out_user");
		logger.info("Verifica il messaggio di errore atteso");
		String actualErrorMessage = homePage.getErrorText();
		String expectedErrorMessage = "Epic sadface: Sorry, this user has been locked out.";
		assertEquals(expectedErrorMessage, actualErrorMessage);
		if (expectedErrorMessage.equals(actualErrorMessage)) {
			logger.info("Il testo sarà : " + actualErrorMessage);
		} else {
			logger.info("Il testo non corrisponde.");
		}
	}

	@Test
	@Order(3)
	public void problemUserTest() {
		HomePage homePage = inserisciUser("problem_user");
		boolean viewElement = homePage.verifyBottonCart();
		assertTrue(viewElement, "L'elemento non è visualizzato.");
		logger.info("Verifica se il testo prima e dopo il clic sul prodotto è lo stesso");
		String testoPrima = homePage.getTextCart();
		homePage.clickAndWait(4);
		String testoDopo = homePage.getDetailText();
		assertEquals("Sauce Labs Backpack", testoPrima, testoDopo);
		if (testoPrima.equals(testoDopo)) {
			logger.info(
					"Il testo prima: " + testoPrima + "e dopo: " + testoDopo + " ..il clic sul prodotto corrisponde.");
		} else {
			logger.info("Il testo prima: " + testoPrima + "e dopo:  " + testoDopo
					+ " ..il clic sul prodotto non corrisponde.");
		}
	}

	@Test
	@Order(4)
	public void performanceGlitchUserTest() {
		HomePage homePage = inserisciUser("performance_glitch_user");
		homePage.clickAndWait(3);
		try {
			homePage.getshoppingCart();
			logger.info("Elemento carello trovato correttamente");
		} catch (NoSuchElementException e) {
			logger.error("Impossibile trovare l'elemento 'shopping_cart_link'.");
		}
	}

	@Test
	@Order(5)
	public void errorUserTest() {
		HomePage homePage = inserisciUser("error_user");
		logger.info("Effettua il primo clic sul pulsante Add to Cart");
		homePage.clickAndWait(1);
		boolean removeAdd = homePage.verifyBottonDelete();
		assertTrue(removeAdd, "L'elemento è stato rimosso dal carello");
		logger.info("L'elemento non è stato rimosso dal carrello.");
	}

	@Test
	@Order(6)
	public void visualUserTest() {
		HomePage homePage = inserisciUser("visual_user");
		logger.info("Faccio il click su Add to cart");
		homePage.clickAndWait(1);
		logger.info("Faccio il click sul carello");
		homePage.clickAndWait(2);
		logger.info("Eseguo il click su Checkout e inserisco datiCliente");
		CheckoutPage checkOut = new CheckoutPage();
		checkOut.validCheckout("Ciccio", "Pasticcio", "98909");
		String name = checkOut.getAttributeName();
		String surname = checkOut.getAttributeSurname();
		logger.info("Nome inserito: " + name + " Cap: " + checkOut.getAttributeCap());
		assertEquals(surname, "Pasticcio");
		logger.info("Il testo inserito corrisponde a : " + surname);
		checkOut.clickContinua();
		logger.info("Il test è andato a buon fine ");
	}

	@AfterEach
	public void chiudiBrowser() {
		DriverManagerHelper.chiudiDriver();
	}
}

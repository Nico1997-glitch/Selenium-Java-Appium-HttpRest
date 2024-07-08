package it.smartpaper.BookStoreImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import it.smartpaper.DriverManagerHelper.DriverManagerHelper;
import it.smartpaper.PageFactory.BookDetailPage;
import it.smartpaper.PageFactory.BooksPage;
import it.smartpaper.PageFactory.LoginPage;
import it.smartpaper.PageFactory.ProfilePage;
import it.smartpaper.PageFactory.RegisterPage;

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

	//@Test
	@Order(1)
	public void testaccountLoginOk() {
		LoginPage loginPage = new LoginPage("https:\\demoqa.com\\login");
		logger.info("Inserisco dati per la creazione dell'account");
		loginPage.loginWithCredentials("AmiciNoi", "Basilicata97!");
		String leggiUser = loginPage.readUser();
		logger.info("Nome utente : " + leggiUser);
		loginPage.clickSubmit();
		boolean isLoginButtonInvisible = loginPage.viewSubmit();
		assertTrue(isLoginButtonInvisible, "Il bottone 'Login' è ancora visibile sulla pagina.");
		logger.info("Il bottone 'Login' è ancora invisibile sulla pagina.");
	}

	//@Test
	@Order(2)
	public void testaccountLoginKo() {
		LoginPage loginPage = new LoginPage("https:\\demoqa.com\\login");
		logger.info("Inserisco dati per il login dell'account");
		loginPage.loginWithCredentials("AmiciNi", "Basilicata97!");
		String leggiUser = loginPage.readUser();
		logger.info("Nome utente : " + leggiUser);
		loginPage.clickSubmit();
		boolean isLoginButtonInvisible = loginPage.viewSubmit();
		assertTrue(isLoginButtonInvisible, "Il bottone 'Login' è invisibile sulla pagina.");
		logger.info("Il bottone 'Login' è ancora visibile sulla pagina.");
		String actualErrorMessage = loginPage.errorMassage();
		assertEquals(actualErrorMessage, "Invalid username or password!",
				"Il messaggio di errore non corrisponde a quello atteso.");
		logger.info("Il testo corrisponde a :" + actualErrorMessage);
		boolean loginView = loginPage.viewSubmit();
		assertTrue(loginView, "Il bottone 'Login' non è più visibile sulla pagina.");
		logger.info("Il bottone 'Login' è visibile e il bottone 'Log out' non è visibile sulla pagina.");
	}

	private RegisterPage registerUserFromProfilePage() {
		BooksPage booksPage = new BooksPage("https:\\demoqa.com\\books");
		String urlIniziale = DriverManagerHelper.getCurrentURL();
		ProfilePage profilePage = booksPage.clickProfileLink();
		logger.info("Click su 'Profile'.");
		String urlActual = DriverManagerHelper.getCurrentURL();
		assertNotEquals(urlActual, urlIniziale, "L' url è rimasto lo stesso ");
		logger.info("URL verificato dopo il click su 'Profile': " + urlActual);
		profilePage.clickRegisterLink();
		logger.info("Click su 'Register'.");
		return new RegisterPage() ;
	}

	//@Test
	@Order(3)
	public void RegisterKOTest() {
		logger.info("Inizio test RegisterKOTest.");
		RegisterPage registerPage = registerUserFromProfilePage();
		registerPage.registerUser("Milan", "Noi", "Camaleonte", "123");
		DriverManagerHelper.gestisciCaptcha(true);
		registerPage.clickSubmit();
		logger.info("Dati inseriti nel form di registrazione.");
		String error = registerPage.getPasswordMessage();
		assertNotNull(error, "Password il messaggio non è presente");
		logger.info("Messaggio di errore ricevuto: " + error);
		assertTrue(registerPage.isRegisterButtonDisplayed(), "Il bottone per registrarsi non è visibile");
		logger.info("Il bottone per registrarsi è visibile.");
		logger.info("Test RegisterKOTest completato.");
	}

	@Test
	@Order(4)
	public void RegisterOKTest() {
		logger.info("Inizio test RegisterOKTest.");
		registerUserFromProfilePage();
		BooksPage booksPage = new BooksPage("https:\\demoqa.com\\books");
		String urlIniziale = DriverManagerHelper.getCurrentURL();
		ProfilePage profilePage = booksPage.clickProfileLink();
		logger.info("Click su 'Profile'.");
		String urlActual = DriverManagerHelper.getCurrentURL();
		assertNotEquals(urlActual, urlIniziale, "L' url è rimasto lo stesso ");
		logger.info("URL verificato dopo il click su 'Profile': " + urlActual);
		RegisterPage registerPage = profilePage.clickRegisterLink();
		logger.info("Click su 'Register'.");
		registerPage.registerUser("Milano", "Noios", "Camaleon", "Basilicata23!");
		logger.info("Dati inseriti nel form di registrazione.");
		DriverManagerHelper.gestisciCaptcha(true);
		LoginPage loginPage = new LoginPage("https://demoqa.com/login");
		logger.info("Redirect to profile page after registration.");
		loginPage.loginWithCredentials("Camaleon", "Basilicata23!");
		BookDetailPage bookDetailPage = loginPage.clickSubmit();
		logger.info("Login eseguito con i dati dell'utente registrato.");
		String actualUser = bookDetailPage.getUserName();
		assertEquals(actualUser, "Camaleon", "L'utente registrato non è corretto");
		logger.info("Username verificato: " + actualUser);
		assertTrue(bookDetailPage.isLogOutButtonDisplayed(), "Il bottone 'LogOut' non è visibile sulla pagina.");
		logger.info("Il bottone 'LogOut' è visibile sulla pagina.");
		bookDetailPage.clickBookStoreLink();
		logger.info("Click su 'Book Store'.");
		bookDetailPage.clickSpecificBookLink();
		logger.info("Click sul libro 'You Don't Know JS'.");
		String expectedUrl = "https://demoqa.com/books?book=9781491904244";
		String actualUrl = DriverManagerHelper.getCurrentURL();
		assertEquals(actualUrl, expectedUrl, "L'URL della pagina aperta non è quello atteso");
		logger.info("URL verificato: " + actualUrl);
		logger.info("Test RegisterOKTest completato.");
	}

	@AfterAll
	public static void chiudiSito() {
		DriverManagerHelper.chiudiDriver();
	}
}
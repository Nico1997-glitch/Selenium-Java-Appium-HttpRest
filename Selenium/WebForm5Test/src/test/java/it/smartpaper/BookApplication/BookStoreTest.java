package it.smartpaper.BookApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.openqa.selenium.WebElement;

import it.smartpaper.selenium.helper.WebDriverManager;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookStoreTest {
	protected static final Logger logger = LogManager.getLogger();
	private static final Duration durata = Duration.ofSeconds(20);

	@BeforeEach
	public void setUp() {
		WebDriverManager.getDriver();
		logger.info("Creo un nuovo driver");
		WebDriverManager.openPage("https://demoqa.com/login");
	}

	@Test
	@Order(1)
	public void testaccountLoginOk() {
		logger.info("Avvio il metodo testaccountLoginOk");
		logger.info("Inserisco Username");
		WebElement userNameTextElement = WebDriverManager.clearAndSendKeys(By.id("userName"), durata, "AmiciNoi");
		logger.info("Inserisco Password");
		WebDriverManager.clearAndSendKeys(By.id("password"), durata, "Basilicata97!");
		// Verifico che il testo sia presente sulla pagina
		String actualText = userNameTextElement.getAttribute("value");
		logger.info("User Name: " + actualText);
		WebDriverManager.findElement(By.id("login")).click();
		boolean isLoginButtonInvisible = WebDriverManager.waitForElementDisappear(By.id("login"), durata);
		assertTrue(isLoginButtonInvisible, "Il bottone 'Login' è ancora visibile sulla pagina.");
		logger.info("Il bottone 'Login' è ancora invisibile sulla pagina.");
		// Verifica che il bottone "Log out" sia visibile
		WebElement logoutButton = WebDriverManager.waitForElementVisible(By.id("submit"), durata);
		assertTrue(logoutButton.isDisplayed(), "Il bottone 'Log out' non è visibile sulla pagina.");
		logger.info("Il bottone 'Login' non è visibile e il bottone 'Log out' è visibile sulla pagina.");
	}

	@Test
	@Order(2)
	public void testaccountLoginError() {
		logger.info("Avvio il metodo testaccountLoginError");
		logger.info("Inserisco Username");
		WebElement userNameTextElement = WebDriverManager.clearAndSendKeys(By.id("userName"), durata, "AmiciNi");
		logger.info("Inserisco Password");
		WebDriverManager.clearAndSendKeys(By.id("password"), durata, "Basilicat97!");
		// Verifico che il testo sia presente sulla pagina
		String actualText = userNameTextElement.getAttribute("value");
		logger.info("User Name: " + actualText);
		WebDriverManager.findElement(By.id("login")).click();
		boolean isLoginButtonInvisible = WebDriverManager.waitForElementDisappear(By.id("login"), durata);
		assertTrue(isLoginButtonInvisible, "Il bottone 'Login' è ancora visibile sulla pagina.");
		logger.info("Il bottone 'Login' è ancora invisibile sulla pagina.");
		// Verifica che il messaggio di errore sia presente sulla pagina
		WebElement errorMessageElement = WebDriverManager.waitForElementVisible(By.id("name"), durata);
		String actualErrorMessage = errorMessageElement.getText();
		assertEquals(actualErrorMessage, "Invalid username or password!",
				"Il messaggio di errore non corrisponde a quello atteso.");
		logger.info("Il testo corrisponde a : Invalid username or password!");
		// Verifica che il bottone "Log out" non sia visibile
		WebElement loginButton = WebDriverManager.waitForElementVisible(By.id("login"), durata);
		assertTrue(loginButton.isDisplayed(), "Il bottone 'Login' non è più visibile sulla pagina.");
		logger.info("Il bottone 'Login' è visibile e il bottone 'Log out' non è visibile sulla pagina.");
	}

	@Test
	@Order(3)
	public void RegisterKOTest() {
		logger.info("Apri la pagina");
		WebDriverManager.openPage("https://demoqa.com/books");
		String urlIniziale = WebDriverManager.getCurrentURL();
//		WebElement profile = WebDriverManager
//				.waitForElementVisible(By.xpath("//li[@id='item-3']/[contains(text(), 'Profile')]"), durata);
//		profile.click();
//		WebElement profile = WebDriverManager
//				.waitForElementVisible(By.xpath("//li[@id='item-3']/span[text()='Profile')]"), durata);
//		profile.click();
		boolean scrollToProfile = true;
		WebDriverManager.click(By.xpath("//li[@id='item-3']/span[text()='Profile']"), durata, scrollToProfile);
		logger.info("Verifico che l'url cambia dopo il click");
		String urlActual = WebDriverManager.getCurrentURL();
		assertNotEquals(urlActual, urlIniziale, "L' url è rimasto lo stesso ");
		logger.info("L'url è stato modificato correttamente");
		logger.info("Eseguo il click su Register");
		boolean scrollToLogin = true;
		WebDriverManager.click(By.xpath("//*[@id='notLoggin-label']/a[2]"), durata, scrollToLogin);
		WebDriverManager.clearAndSendKeys(By.id("firstname"), durata, "Milan");
		WebDriverManager.clearAndSendKeys(By.id("lastname"), durata, "Noi");
		WebDriverManager.clearAndSendKeys(By.id("userName"), durata, "Camalente");
		WebDriverManager.clearAndSendKeys(By.id("password"), durata, "123");
		logger.info("Dati inseriti correttamente");
		logger.info("In attesa della risoluzione manuale del captcha.");
		boolean gestisciCaptcha = true;
		WebDriverManager.gestisciCaptcha(gestisciCaptcha);
		boolean scrollToRegister = true;
		WebDriverManager.click(By.id("register"), durata, scrollToRegister);
		String newUrl = WebDriverManager.getCurrentURL();
		String expectedUrl = "https://demoqa.com/register";
		logger.info("Il link Attuale è {}", newUrl);
		assertEquals(newUrl, expectedUrl, "L'URL del link dovrebbe essere quello desiderato");
		logger.info("L'url è rimasto lo stesso");
		WebElement passwordMessage = WebDriverManager.waitForElementVisible(By.id("name"), durata);
		String textPassword = passwordMessage.getText();
		assertNotNull(textPassword, "Password il messaggio non è presente");
		logger.info("Password il messaggio è presente.");
		// Verifica del bottone "Register"
		WebElement registerButton = WebDriverManager.findElement(By.id("register"));
		assertTrue(registerButton.isDisplayed(), "Il bottone per registrarsi non è visibile");
		logger.info("Il bottone per registrarsi è visibile");
	}

	@Test
	@Order(4)
	public void RegisterOKTest() {
		logger.info("Apri la pagina");
		WebDriverManager.openPage("https://demoqa.com/books");
		String urlIniziale = WebDriverManager.getCurrentURL();
		boolean scrollToProfile = true;
		WebDriverManager.click(By.xpath("//li[@id='item-3' and .//span[text()='Profile']]\r\n"),
				durata, scrollToProfile);
		logger.info("Verifico che l'url cambia dopo il click");
		String urlActual = WebDriverManager.getCurrentURL();
		assertNotEquals(urlActual, urlIniziale, "L' url è rimasto lo stesso ");
		logger.info("L'url è stato modificato correttamente");
		logger.info("Eseguo il click su Register");
		WebDriverManager.findElement(By.xpath("//*[@id='notLoggin-label']/a[2]")).click();
		String expectedUrl = "https://demoqa.com/register";
		String actualUrl = WebDriverManager.getCurrentURL();
		assertEquals(actualUrl, expectedUrl, "L'URL della pagina aperta non è quello atteso");
		logger.info("Il link della pagina aperta è lo stesso");
		WebDriverManager.clearAndSendKeys(By.id("firstname"), durata, "Milao");
		WebDriverManager.clearAndSendKeys(By.id("lastname"), durata, "Noos");
		WebDriverManager.clearAndSendKeys(By.id("userName"), durata, "France");
		WebDriverManager.clearAndSendKeys(By.id("password"), durata, "Basilicata23!");
		logger.info("Dati inseriti correttamente");
		logger.info("In attesa della risoluzione manuale del captcha.");
		boolean gestisciCaptcha = true;
		WebDriverManager.gestisciCaptcha(gestisciCaptcha);
		boolean scrollToRegister = true;
		WebDriverManager.click(By.id("register"), durata, scrollToRegister);
		WebDriverManager.AcceptAlert();
		WebDriverManager.click(By.id("gotologin"), durata);
		logger.info("Inserisco Username");
		WebDriverManager.clearAndSendKeys(By.id("userName"), durata, "France");
		logger.info("Inserisco Password");
		WebDriverManager.clearAndSendKeys(By.id("password"), durata, "Basilicata23!");
		boolean scrollToLogin = true;
		WebDriverManager.click(By.id("login"), durata, scrollToLogin);
		WebElement userActual = WebDriverManager.waitForElementVisible(By.id("userName-value"), durata);
		String actualUser = userActual.getText();
		logger.info("User Name: " + actualUser);
		WebElement logOutButton = WebDriverManager.waitForElementVisible(By.id("submit"), durata);
		assertTrue(logOutButton.isDisplayed(), "Il bottone 'LogOut' non è visibile sulla pagina.");
		logger.info("Il bottone 'LogOut' è visibile sulla pagina.");
		boolean scrollBookStore = true;
		WebDriverManager.click(By.xpath("//li[@id='item-2' and .//span[text()='Book Store']]\r\n"), durata,
				scrollBookStore);
		boolean scrollBook = true;
		WebDriverManager.click(By.id("see-book-You Don't Know JS"), durata, scrollBook);
		logger.info("Controllo che l'URL della pagina aperta sia https://demoqa.com/books?book=9781491904244");
		String expectedUrl1 = "https://demoqa.com/books?book=9781491904244";
		String actualUrl1 = WebDriverManager.getCurrentURL();
		assertEquals(actualUrl1, expectedUrl1, "L'URL della pagina aperta non è quello atteso");
		logger.info("L'URL della pagina aperta è quello atteso");
		logger.info("Apro la pagina dei libri");
		WebDriverManager.openPage("https://demoqa.com/books");
		WebDriverManager.click(By.id("submit"), durata);

	}

	@AfterEach
	public void chiudiBrowser() {
		WebDriverManager.chiudiDriver();
	}
}

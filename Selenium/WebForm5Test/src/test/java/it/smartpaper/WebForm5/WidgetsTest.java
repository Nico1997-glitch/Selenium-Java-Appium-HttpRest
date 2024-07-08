package it.smartpaper.WebForm5;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
public class WidgetsTest {
	protected static final Logger logger = LogManager.getLogger();
	private static final Duration durata = Duration.ofSeconds(10);

	@BeforeAll
	public static void setUp() {
		WebDriverManager.getDriver();
		logger.info("Creo un nuovo driver");
		WebDriverManager.openPage("https://demoqa.com/date-picker");
	}

	@Test
	@Order(1)
	public void datePicker() {
		WebDriverManager.openPage("https://demoqa.com/date-picker");
		WebElement dateDisplayedElement = WebDriverManager.findElement(By.id("datePickerMonthYearInput"));
//		WebElement dataRichiesta = WebDriverManager.findElement(
//				By.xpath("//*[@id=\"datePickerMonthYear\"]/div[2]/div[2]/div/div/div[2]/div[2]/div[4]/div[7]"));
//		dataRichiesta.click();
//		String newDate = dataRichiesta.getText();
		String newDate = "05/25/2024";
		WebDriverManager.clearAndSendKeys(By.id("datePickerMonthYearInput"), durata, newDate);
		dateDisplayedElement = WebDriverManager.findElement(By.id("datePickerMonthYearInput"));
		String actualDate = dateDisplayedElement.getText();
		// Verifica che la nuova data sia stata inserita correttamente
		assertNotEquals(actualDate, newDate);
	}

	@Test
	@Order(2)
	public void testSlider() {
		WebDriverManager.openPage("https://demoqa.com/slider");
		WebElement slider = WebDriverManager.findElement(By.id("sliderContainer"));
		// Muove il slider alla posizione desiderata
		WebDriverManager.moveSlider(slider, 20); // Muove il slider al 50%
		logger.info("Slider spostato al 20%");

	}

	@Test
	@Order(3)
	public void testProgressBar() {
		WebDriverManager.openPage("https://demoqa.com/progress-bar");
		WebDriverManager.findElement(By.id("startStopButton")).click();
		WebElement progress = WebDriverManager.findElement(By.id("progressBar"));
		logger.info("Valore iniziale della Progress Bar: 0");
		WebDriverManager.waitForProgressBarCompletion(progress);
		WebElement reset = WebDriverManager.waitForElementVisible(By.xpath("//*[@id='resetButton']"), durata);
		logger.info("Progress Bar completata con successo!");
		reset.click();
		logger.info("Test eseguito");
	}
	
	
	//@AfterAll
	public static void chiudiBrowser() {
		WebDriverManager.chiudiDriver();
	}
}

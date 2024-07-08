package it.smartpaper.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import helper.DriverHelper;

public class WebForm2_ComplessivoOrdinatoTest {
	private static WebDriver driver;

	@BeforeAll
	public static void setUp() {
		driver = DriverHelper.getDriver() ;	
	}
	
	@BeforeEach
	public void openSeleniumTest() {
		driver.get("https://artoftesting.com/samplesiteforselenium");
	}
	
	@Test
	@Order(1)
	public void premiLinkTest() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement accetta = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("css-47sehv")));
		accetta.click();
		WebElement scroll = driver.findElement(By.linkText("This is a link"));
		DriverHelper.scrollWeb(driver, scroll);
		WebElement element = driver.findElement(By.linkText("This is a link"));
        assertEquals("This is a link", element.getText());
        // Verifica l'URL dell'elemento trovato
        assertEquals("http://www.artoftesting.com/sampleSiteForSelenium.html", element.getAttribute("href"));
        element.click();
        // Verifica che il titolo della pagina sia cambiato dopo il clic
        assertEquals("Sample Webpage for Selenium Automation Practice | ArtOfTesting", driver.getTitle());
    }
	
	@Test
	@Order(2)
	public void testTextBox() {
		WebElement scroll = driver.findElement(By.id("fname"));
		DriverHelper.scrollWeb(driver, scroll);
		 String textToInput = "Inserire del testo";
		 driver.findElement(By.id("fname")).sendKeys(textToInput);

	}
	
	@Test
	@Order(3)
	public void testClickSubimit() {
		WebElement scroll = driver.findElement(By.id("idOfButton"));
		DriverHelper.scrollWeb(driver, scroll);
		WebElement elementSubmit = driver.findElement(By.id("idOfButton"));
        elementSubmit.click();
    }
	
	@Test
	@Order(4)
	public void testDoppioClick() {
		Actions dClick = new Actions(driver);
		WebElement scroll = driver.findElement(By.id("dblClkBtn"));
		DriverHelper.scrollWeb(driver, scroll);
		WebElement element = driver.findElement(By.id("dblClkBtn"));
		dClick.doubleClick(element).perform();
		driver.switchTo().alert().accept();

	}
	
    @Test
    @Order(5)
    public void testRadioBotton() {
    	WebElement elementToClick = driver.findElement(By.id("male"));
        assertFalse(elementToClick.isSelected());
        elementToClick.click();
        assertTrue(elementToClick.isSelected());
    }
    
   
	
	@AfterAll
	public static void chiudiBrowser() {
		DriverHelper.chiudiDriver();
	}

}

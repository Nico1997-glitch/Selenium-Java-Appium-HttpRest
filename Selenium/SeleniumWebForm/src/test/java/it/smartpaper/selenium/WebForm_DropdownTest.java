package it.smartpaper.selenium;



import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import helper.DriverHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeAll;

public class WebForm_DropdownTest {

	private static WebDriver driver;

	@BeforeAll
	public static void setUp() {
		driver = DriverHelper.getDriver() ;		
	}

	@Test
	public void DropdownIndexTest() {
		driver.get("https://www.selenium.dev/selenium/web/web-form.html");
		// Seleziona l'elemento dropdown tramite l'indice
		WebElement dropDownIndex = driver.findElement(By.name("my-select"));
		Select dropdown = new Select(dropDownIndex);
		dropdown.selectByIndex(2);
		// Ottiene il testo dell'opzione selezionata
		String selectedOption = dropdown.getFirstSelectedOption().getText();
		// Verifica che l'opzione selezionata sia corretta
		assertEquals("Two", selectedOption);
	}

	@Test
	public void DropdownValueTest() {
	       // Seleziona l'elemento dropdown tramite il valore
        WebElement dropDownValue = driver.findElement(By.name("my-select"));
        dropDownValue.click();
        Select dropdown = new Select(dropDownValue);
        dropdown.selectByValue("3");
        String selectedOption = dropdown.getFirstSelectedOption().getText();
        assertEquals("Three", selectedOption);
	}

	@AfterAll
	public static void chiudiBrowser() {
		DriverHelper.chiudiDriver();
	}
}

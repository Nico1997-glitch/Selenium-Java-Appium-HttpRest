package it.smartpaper.selenium;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import helper.DriverHelper;

public class WebForm_checkBoxTest {
	private static WebDriver driver;

	@BeforeAll
	public static void setUp() {
		driver = DriverHelper.getDriver() ;		
	}
	
	@Test
	public void testClickOnButtons() {
		driver.get("https://www.selenium.dev/selenium/web/web-form.html");
	    WebElement checkbox1 = driver.findElement(By.id("my-check-1"));
	    WebElement checkbox2 = driver.findElement(By.id("my-check-2"));
	    
	    checkbox2.click();
	    
	    assertTrue(checkbox1.isSelected(), "La casella di controllo con id 'my-check-1' non è stata selezionata");
	    assertTrue(checkbox2.isSelected(), "La casella di controllo con id 'my-check-2' non è stata selezionata");
	}

	@AfterAll
	public static void chiudiBrowser() {
		DriverHelper.chiudiDriver();
	}
}

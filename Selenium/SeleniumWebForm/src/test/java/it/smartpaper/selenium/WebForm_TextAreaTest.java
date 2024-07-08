package it.smartpaper.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import helper.DriverHelper;

public class WebForm_TextAreaTest {
	
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		driver = DriverHelper.getDriver() ;		
	}

	@Test
	public void testAreaInput() {
		driver.get("https://www.selenium.dev/selenium/web/web-form.html");
		String textToInput = "Ciao sto provando a fare alcuni Test";
		driver.findElement(By.name("my-textarea")).sendKeys(textToInput);
		String inputValue = driver.findElement(By.name("my-textarea")).getAttribute("value");
		//String inputValue = driver.findElement(By.id("my-text-id")).getText();
	    assertEquals(textToInput, inputValue);
	}
	
	@AfterAll
	public static void chiudiBrowser() {
		DriverHelper.chiudiDriver();
	}
}

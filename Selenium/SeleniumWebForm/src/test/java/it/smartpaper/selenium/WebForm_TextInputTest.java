package it.smartpaper.selenium;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import helper.DriverHelper;

public class WebForm_TextInputTest {
	private static WebDriver driver;

	@BeforeAll
	public static void setUp() {
		driver = DriverHelper.getDriver() ;		
	}

	@Test
	public void testTextInput() {
		driver.get("https://www.selenium.dev/selenium/web/web-form.html");
		String textToInput = "Inserire del testo";
		driver.findElement(By.id("my-text-id")).sendKeys(textToInput);
		String inputValue = driver.findElement(By.id("my-text-id")).getAttribute("value");
		//String inputValue = driver.findElement(By.id("my-text-id")).getText();
	    assertEquals(textToInput, inputValue);
	}
	
	@AfterAll
	public static void chiudiBrowser() {
		DriverHelper.chiudiDriver();
	}
}

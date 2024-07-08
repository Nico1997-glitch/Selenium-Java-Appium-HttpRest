package it.smartpaper.selenium;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import helper.DriverHelper;

public class WebForm_checkedRadioTest {

    private static WebDriver driver;

	@BeforeAll
	public static void setUp() {
		driver = DriverHelper.getDriver() ;		
	}

    @Test
    public void testElementClick() {
    	driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        WebElement elementToClick = driver.findElement(By.id("my-radio-2"));
        
        // Verifica che l'elemento non sia stato premuto prima del click
        assertFalse(elementToClick.isSelected());
        
        // Esegue il click sull'elemento
        elementToClick.click();
        
        // Verifica che l'elemento sia stato premuto dopo il click
        assertTrue(elementToClick.isSelected());
    }
	@AfterAll
	public static void chiudiBrowser() {
		DriverHelper.chiudiDriver();
	}
}

package it.smartpaper.selenium;


import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import helper.DriverHelper;

public class WebForm_ReadonlyInputTest {
	private static WebDriver driver;

	@BeforeAll
	public static void setUp() {
		driver = DriverHelper.getDriver() ;		
	}

	@Test
    public void testReadonlyInput() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        assertTrue(driver.findElement(By.name("my-readonly")).getAttribute("readonly").equals("true"));
    }
	
	@AfterAll
	public static void chiudiBrowser() {
		DriverHelper.chiudiDriver();
	}
}

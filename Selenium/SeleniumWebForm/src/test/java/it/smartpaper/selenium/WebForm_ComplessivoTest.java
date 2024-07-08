package it.smartpaper.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import helper.DriverHelper;

public class WebForm_ComplessivoTest {
	private static WebDriver driver;

	@BeforeAll
	public static void setUp() {
		driver = DriverHelper.getDriver() ;		
	}
	@BeforeEach
	public void openSeleniumTest() {
		driver.get("https://www.selenium.dev/selenium/web/web-form.html");
	}
//	//TextInputTest
	@Test
	public void testTextInput() {
		String textToInput = "Inserire del testo";
		driver.findElement(By.id("my-text-id")).sendKeys(textToInput);
		String inputValue = driver.findElement(By.id("my-text-id")).getAttribute("value");
		//String inputValue = driver.findElement(By.id("my-text-id")).getText();
	    assertEquals(textToInput, inputValue);
	}
//	
//	//AreaInputTest
	@Test
	public void testAreaInput() {
		String textToInput = "Ciao sto provando a fare alcuni Test";
		driver.findElement(By.name("my-textarea")).sendKeys(textToInput);
		String inputValue = driver.findElement(By.name("my-textarea")).getAttribute("value");
		//String inputValue = driver.findElement(By.id("my-text-id")).getText();
	    assertEquals(textToInput, inputValue);
	}
//	
//	
//	//ReandonlyInputTest
	@Test
    public void testReadonlyInput() {
        assertTrue(driver.findElement(By.name("my-readonly")).getAttribute("readonly").equals("true"));
    }
//	
//	//DropDownTest for Index and Value
	@Test
	public void DropdownIndexTest() {
		// Seleziona l'elemento dropdown tramite l'indice
		WebElement dropDownIndex = driver.findElement(By.name("my-select"));
		Select dropdown = new Select(dropDownIndex);
		dropdown.selectByIndex(2);
		// Ottiene il testo dell'opzione selezionata
		String selectedOption = dropdown.getFirstSelectedOption().getText();
		// Verifica che l'opzione selezionata sia corretta
		assertEquals("Two", selectedOption);
	}
//
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
//	
//	//DisabledInputTest
	@Test
    public void testDisabledInput() {
        assertFalse(driver.findElement(By.name("my-disabled")).isEnabled());
    }
	
//	//CheckBoxTest
    @Test
    public void testElementClick() {
        WebElement elementToClick = driver.findElement(By.id("my-radio-2"));
        
        // Verifica che l'elemento non sia stato premuto prima del click
        assertFalse(elementToClick.isSelected());
        
        // Esegue il click sull'elemento
        elementToClick.click();
        
        // Verifica che l'elemento sia stato premuto dopo il click
        assertTrue(elementToClick.isSelected());
    }
//    
//    //RadioBottonTest
	@Test
	public void testClickOnButtons() {
	    WebElement checkbox1 = driver.findElement(By.id("my-check-1"));
	    WebElement checkbox2 = driver.findElement(By.id("my-check-2"));
	    
	    checkbox2.click();
	    
	    assertTrue(checkbox1.isSelected(), "La casella di controllo con id 'my-check-1' non è stata selezionata");
	    assertTrue(checkbox2.isSelected(), "La casella di controllo con id 'my-check-2' non è stata selezionata");
	}
	
//	//ClickSubmitTest
	@Test
	public void clickSubmitTest() {
		WebElement elementSubmit = driver.findElement(By.tagName("button"));
        elementSubmit.click();
    }


	@AfterAll
	public static void chiudiBrowser() {
		DriverHelper.chiudiDriver();
	}

}




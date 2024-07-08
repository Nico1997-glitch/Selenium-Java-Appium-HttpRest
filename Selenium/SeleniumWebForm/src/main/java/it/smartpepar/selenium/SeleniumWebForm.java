package it.smartpepar.selenium;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class SeleniumWebForm {
	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.setProperty("chromedriver.exe", "C:\\Users\\Supporto\\Desktop\\Esercizi");

		driver.get("https://www.selenium.dev/selenium/web/web-form.html");

		WebElement scriviName = driver.findElement(By.id("my-text-id"));
		scriviName.sendKeys("Inserire del testo");

		WebElement textArea = driver.findElement(By.name("my-textarea"));
		textArea.sendKeys("Ciao sto provando a fare alcuni Test");

		WebElement DropDownIndex = driver.findElement(By.name("my-select"));
		Select dropdown = new Select(DropDownIndex);
		dropdown.selectByIndex(2);
		
		
		//driver.quit();

	}
}

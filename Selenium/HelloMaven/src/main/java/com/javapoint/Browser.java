package com.javapoint;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser {
	public static void main(String[] args) {

		// Creating a driver object referencing WebDriver interface
		WebDriver driver;

		// Setting the webdriver.chrome.driver property to its executable's location
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Supporto\\eclipse-workspace\\HelloMaven\\chromedriver.exe");

		// Instantiating driver object
		driver = new ChromeDriver();

		// Using get() method to open a webpage
		driver.get("https://www.google.it/?hl=it");

		// Naviga su Facebook
		driver.get("https://www.amazon.it/");

		// Trova il campo di ricerca
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));

		// Inserisci il termine di ricerca
		searchBox.sendKeys("PenDrive");


		// Attendi che la pagina del prodotto venga caricata
		try {
			Thread.sleep(3000); // Attendi per 3 secondi
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Trova il pulsante "Aggiungi al carrello" e fai clic su di esso
		WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
		addToCartButton.click();

		// Attendi che il prodotto venga aggiunto al carrello
		try {
			Thread.sleep(3000); // Attendi per 3 secondi
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Attendi per qualche secondo prima di chiudere il browser
		try {
			Thread.sleep(5000); // Attendi per 5 secondi
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

package it.smartpaper.PageFactory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import it.smartpaper.DriverManagerHelper.DriverManagerHelper;

public class HomePage {
	@FindBy(id = "add-to-cart-sauce-labs-bike-light")
	private WebElement addCart;
	@FindBy(className = "shopping_cart_badge")
	private WebElement carello;
	@FindBy(className = "inventory_item_name")
	private WebElement cartItem;
	@FindBy(xpath = "//*[@id='inventory_item_container']/div/div/div[2]/div[1]")
	private WebElement itemDetailTitle;
	@FindBy(xpath = "//*[@id='login_button_container']/div/form/div[3]")
	private WebElement errorMessage;
	@FindBy(className = "shopping_cart_link")
	private WebElement shoppingCart;
	@FindBy(id = "remove-sauce-labs-bike-light")
	private WebElement removeSauce;
	private WebDriverWait wait;

	public HomePage(String url) {
		WebDriver driver = DriverManagerHelper.getDriver();
		driver.get(url);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	public HomePage() {
		WebDriver driver = DriverManagerHelper.getDriver();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	public void clickAndWait(int i) {
		if (i == 1) {
			wait.until(ExpectedConditions.elementToBeClickable(addCart));
			addCart.click();
		} else if (i == 2) {
			wait.until(ExpectedConditions.elementToBeClickable(carello));
			carello.click();
		} else if (i == 3) {
			wait.until(ExpectedConditions.elementToBeClickable(shoppingCart));
			shoppingCart.click();
		}else if(i == 4) {
			wait.until(ExpectedConditions.elementToBeClickable(shoppingCart));
			cartItem.click();
		}
	}

	public boolean verifyBottonCart() {
		return cartItem.isDisplayed();
	}
	
	public boolean verifyBottonDelete() {
		return removeSauce.isDisplayed();
	}

	public String getTextCart() {
		return cartItem.getText();
	}

	public String getDetailText() {
		return itemDetailTitle.getText();
	}

	public String getErrorText() {
		return errorMessage.getText();
	}

	public String getshoppingCart() {
		wait.until(ExpectedConditions.visibilityOf(shoppingCart));
		return shoppingCart.getText();
	}


}

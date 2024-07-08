package it.smartpaper.PageFactory;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import it.smartpaper.DriverManagerHelper.DriverManagerHelper;

public class HomePage {
	@FindBy(className = "product-image-photo")
	private WebElement product;
	@FindBy(css = "#option-label-size-143-item-168")
	private WebElement taglia;
	@FindBy(id = "option-label-color-93-item-56")
	private WebElement color;
	@FindBy(id = "product-addtocart-button")
	private WebElement addtocart;
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
	
	public void scrollAndClick(WebElement find) {
		WebDriver driver = DriverManagerHelper.getDriver();
		wait.until(ExpectedConditions.elementToBeClickable(find));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", find);
		find.click();
	}

	public void clickAndWaitElemnt() {
		scrollAndClick(product);
		scrollAndClick(taglia);
		scrollAndClick(color);
		scrollAndClick(addtocart);
	}

}

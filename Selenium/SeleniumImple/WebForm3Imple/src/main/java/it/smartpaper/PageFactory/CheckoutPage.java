package it.smartpaper.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import it.smartpaper.DriverManagerHelper.DriverManagerHelper;

public class CheckoutPage {
	@FindBy(name = "street[0]")
	private WebElement adress;
	@FindBy(name = "city")
	private WebElement city;
	@FindBy(name = "region_id")
	private WebElement region;
	@FindBy(name = "postcode")
	private WebElement postal;
	@FindBy(name = "country_id")
	private WebElement country;
	@FindBy(name = "telephone")
	private WebElement telephone;
	@FindBy(xpath = "//*[@id='checkout-shipping-method-load']/table/tbody/tr/td[1]/input")
	private WebElement checkRadio;
	@FindBy(xpath = "//*[@id='shipping-method-buttons-container']/div/button/span")
	private WebElement conferma;
	@FindBy(xpath = "//*[@id='checkout-payment-method-load']/div/div/div[2]/div[2]/div[4]/div/button/span")
	private WebElement placeOrder;
	private HomePage utility;

	public CheckoutPage(String url) {
		WebDriver driver = DriverManagerHelper.getDriver();
		driver.get(url);
		this.utility = new HomePage();
		PageFactory.initElements(driver, this);
	}

	public void operationShipping(String adres, String citi, String poste, String telefono) {
		utility.scrollAndClick(adress);
		adress.sendKeys(adres);
		utility.scrollAndClick(city);
		city.sendKeys(citi);
		utility.scrollAndClick(postal);
		postal.sendKeys(poste);
		utility.scrollAndClick(telephone);
		telephone.sendKeys(telefono);
	}
	
	public String selectDropdownRegion(String value) {
		utility.scrollAndClick(region);
		Select dropdown = new Select(region);
		dropdown.selectByValue(value);
		country.click();
		return dropdown.getFirstSelectedOption().getText();
	}

	public String selectDropdownCountry(String value) {
		utility.scrollAndClick(country);
		Select dropdown = new Select(country);
		dropdown.selectByValue(value);
		country.click();
		return dropdown.getFirstSelectedOption().getText();
	}

	public void click() {
		utility.scrollAndClick(checkRadio);
		utility.scrollAndClick(conferma);
		utility.scrollAndClick(placeOrder);
	}

}

package it.smartpaper.PageFactory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import it.smartpaper.DriverManagerHelper.DriverManagerHelper;

public class CheckoutPage {
	@FindBy(id = "checkout")
    private WebElement checkout;
	@FindBy(id = "first-name")
	private WebElement name;
	@FindBy(id = "last-name")
	private WebElement surname;
	@FindBy(id = "postal-code")
	private WebElement postal;
	@FindBy(id = "continue")
	private WebElement continua;
	private WebDriverWait wait;
	
	public CheckoutPage() {
		WebDriver driver = DriverManagerHelper.getDriver();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	public String getAttributeName() {
		return name.getAttribute("value");
	}
	
	public String getAttributeSurname() {
		return surname.getAttribute("value");
	}
	
	public String getAttributeCap() {
		return postal.getAttribute("value");
	}

    public void clickSubmit() {
    	checkout.click();
    }
    
    public void clickContinua() {
    	continua.click();
    }
    
    public void validCheckout(String nome, String sur, String cap) {
    	checkout.click();
    	name.sendKeys(nome);
    	surname.sendKeys(sur);
    	postal.sendKeys(cap);
    }
    
    
}

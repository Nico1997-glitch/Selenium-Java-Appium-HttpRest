package it.smartpaper.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import it.smartpaper.DriverManagerHelper.DriverManagerHelper;

public class HomePage {
	@FindBy(className = "post-title")
	private WebElement lable;
	@FindBy (id = "error")
	private WebElement error;
	@FindBy(linkText = "Log out")
	private WebElement logOut;


	public HomePage() {
    	WebDriver driver = DriverManagerHelper.getDriver();
        PageFactory.initElements(driver, this);
	}
	
	public String readText() {
		return lable.getText();
	}
	
	public String readError() {
		return error.getText();
	}
	
	public boolean verifyCondition() {
		String text = readError();
		return error.equals(text);
	}

	public boolean verifyBotton() {
		return logOut.isDisplayed();
	}
}

package it.smartpaper.PageFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import it.smartpaper.DriverManagerHelper.DriverManagerHelper;

public class ProfilePage {
  
    @FindBy(xpath = "//*[@id='notLoggin-label']/a[2]")
    WebElement registerLink;

    public ProfilePage() {
		WebDriver driver = DriverManagerHelper.getDriver();
		PageFactory.initElements(driver, this);
    }
    public void scrollToElement(WebElement element) {
    	WebDriver driver = DriverManagerHelper.getDriver();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public RegisterPage clickRegisterLink() {
    	scrollToElement(registerLink);
        registerLink.click();
        return new RegisterPage();
    }
}

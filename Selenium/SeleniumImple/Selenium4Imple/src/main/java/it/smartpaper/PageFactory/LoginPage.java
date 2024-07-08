package it.smartpaper.PageFactory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import it.smartpaper.DriverManagerHelper.DriverManagerHelper;

public class LoginPage {

    @FindBy(id = "user-name")
    private WebElement userName;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(id = "login-button")
    private WebElement login;
    private WebDriverWait wait;
    
	public LoginPage(String url) {
		WebDriver driver = DriverManagerHelper.getDriver();
		driver.get(url);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
    }
	
    public void typeUserName(String text) {
        userName.sendKeys(text);
    }
    public void typePassword(String text) {
        password.sendKeys(text);
    }

    public HomePage clickSubmit() {
    	login.click();
        return new HomePage();
    }
    public HomePage loginWithCredentials(String userName, String pwd) {
        typeUserName(userName);
        typePassword(pwd);
        return clickSubmit();
    }
}

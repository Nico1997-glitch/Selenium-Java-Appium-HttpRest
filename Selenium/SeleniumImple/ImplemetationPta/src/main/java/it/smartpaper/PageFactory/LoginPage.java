package it.smartpaper.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import it.smartpaper.DriverManagerHelper.DriverManagerHelper;

public class LoginPage {

    @FindBy(id = "username")
    private WebElement userName;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(id = "submit")
    private WebElement submit;

	public LoginPage(String url) {
    	WebDriver driver = DriverManagerHelper.getDriver();
    	driver.get(url);
        PageFactory.initElements(driver, this);
    }
	
    public void typeUserName(String text) {
        userName.sendKeys(text);
    }
    public void typePassword(String text) {
        password.sendKeys(text);
    }

    public HomePage clickSubmit() {
        submit.click();
        return new HomePage();
    }
    public HomePage loginWithCredentials(String userName, String pwd) {
        typeUserName(userName);
        typePassword(pwd);
        return clickSubmit();
    }
}

package it.smartpaper.PageFactory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import it.smartpaper.DriverManagerHelper.DriverManagerHelper;

public class LoginPage {

    @FindBy(id = "userName")
    private WebElement emailUser;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(id = "login")
    private WebElement submit;
    @FindBy(id = "name")
    private WebElement error;
    private WebDriverWait wait;
    
	public LoginPage(String url) {
		WebDriver driver = DriverManagerHelper.getDriver();
		driver.get(url);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

	
    public void typeUserName(String text) {
        emailUser.sendKeys(text);
    }
    public void typePassword(String text) {
        password.sendKeys(text);
    }
    
    public String readUser() {
    	return emailUser.getAttribute("value");
    }

    public BookDetailPage clickSubmit() {
    	submit.click();
    	return new BookDetailPage();
    }
    
    public boolean viewSubmit() {
    	WebElement loginButton = wait.until(ExpectedConditions.visibilityOf(submit));
    	return loginButton.isDisplayed();
    }
    
    public String errorMassage() {
    	return error.getText();
    }
    
    public void loginWithCredentials(String userName, String pwd) {
        typeUserName(userName);
        typePassword(pwd);
    }
}

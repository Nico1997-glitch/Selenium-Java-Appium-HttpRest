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
//	@FindBy (className = "fc-button-label")
//	private WebElement accetta;
	@FindBy (className = "authorization-link")
	private WebElement login;
    @FindBy(id = "email")
    private WebElement emailUser;
    @FindBy(id = "pass")
    private WebElement password;
    @FindBy(css = "fieldset[class='fieldset login'] div[class='primary'] span")
    private WebElement submit;
    private WebDriverWait wait;
    
	public LoginPage(String url) {
		WebDriver driver = DriverManagerHelper.getDriver();
		driver.get(url);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
    }
	
	public void clickOnLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(login));
		login.click();
	}
	
    public void typeUserName(String text) {
        emailUser.sendKeys(text);
    }
    public void typePassword(String text) {
        password.sendKeys(text);
    }

    public HomePage clickSubmit() {
    	submit.click();
        return new HomePage();
    }
    public HomePage loginWithCredentials(String userName, String pwd) {
    	clickOnLogin();
        typeUserName(userName);
        typePassword(pwd);
        return clickSubmit();
    }
}

package it.smartpaper.PageFactory;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import it.smartpaper.DriverManagerHelper.DriverManagerHelper;

public class AccountPage {
	@FindBy(id = "firstname")
	private WebElement firstname;
	@FindBy(id = "lastname")
	private WebElement lastname;
	@FindBy(id = "email_address")
	private WebElement email;
	@FindBy(id = "password")
	private WebElement password;
	@FindBy(id = "password-confirmation")
	private WebElement passwordConf;
	@FindBy(css = "button[title='Create an Account'] span")
	private WebElement submit;
	@FindBy(className = "messages")
	private WebElement messages;
	WebDriverWait wait;

	public AccountPage(String url) {
		WebDriver driver = DriverManagerHelper.getDriver();
		driver.get(url);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	private void scrollToElement(WebElement element) {
		WebDriver driver = DriverManagerHelper.getDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void typeName(String text) {
		scrollToElement(firstname);
		firstname.click();
		firstname.sendKeys(text);
	}

	public void typeLastname(String text) {
		lastname.click();
		lastname.sendKeys(text);
	}

	public void typeEmail(String text) {
		email.click();
		email.sendKeys(text);
	}

	public void typePassword(String text) {
		password.click();
		password.sendKeys(text);
	}

	public void typeConfPass(String text) {
		passwordConf.click();
		passwordConf.sendKeys(text);
	}
	
	public HomePage clickOnSubmit() {
		scrollToElement(submit);
		submit.click();
		return new HomePage();
	}

	public String readMessages() {
		return messages.getText();
	}
	
    public void creationAccount(String userName, String last, String email, String pwd, String cpwd) {
    	typeName(userName);
    	typeLastname(last);
    	typeEmail(email);
    	typePassword(pwd);
    	typeConfPass(cpwd);
    }

}

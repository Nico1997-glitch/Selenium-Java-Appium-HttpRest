package it.smartpaper.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import it.smartpaper.DriverManagerHelper.DriverManagerHelper;

public class RegisterPage {
    @FindBy(id = "firstname")
    WebElement firstNameField;

    @FindBy(id = "lastname")
    WebElement lastNameField;

    @FindBy(id = "userName")
    WebElement userNameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "register")
	WebElement registerButton;

    @FindBy(id = "name")
    WebElement passwordMessage;

    public RegisterPage() {
    	WebDriver driver = DriverManagerHelper.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void registerUser(String firstName, String lastName, String userName, String password) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        userNameField.sendKeys(userName);
        passwordField.sendKeys(password);
    }

    public String getPasswordMessage() {
        return passwordMessage.getText();
    }
    
    public boolean isRegisterButtonDisplayed() {
        return registerButton.isDisplayed();
    }
    
    public void clickSubmit() {
    	registerButton.click();
    }
}



package it.smartpaper.PageFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import it.smartpaper.DriverManagerHelper.DriverManagerHelper;

public class BookDetailPage {
    
    @FindBy(id = "submit")
    WebElement logOutButton;

    @FindBy(id = "userName-value")
    WebElement userNameValue;

    @FindBy(xpath = "//li[@id='item-2' and .//span[text()='Book Store']]")
    WebElement bookStoreLink;

    @FindBy(id = "see-book-You Don't Know JS")
    WebElement specificBookLink;
   

    public BookDetailPage() {
    	WebDriver driver = DriverManagerHelper.getDriver();
        PageFactory.initElements(driver, this);
    }
    
    public void scrollToElement(WebElement element) {
    	WebDriver driver = DriverManagerHelper.getDriver();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public boolean isLogOutButtonDisplayed() {
        return logOutButton.isDisplayed();
    }

    public String getUserName() {
        return userNameValue.getText();
    }

    public void clickBookStoreLink() {
    	scrollToElement(bookStoreLink);
        bookStoreLink.click();
    }

    public void clickSpecificBookLink() {
    	scrollToElement(specificBookLink);
        specificBookLink.click();
    }

}

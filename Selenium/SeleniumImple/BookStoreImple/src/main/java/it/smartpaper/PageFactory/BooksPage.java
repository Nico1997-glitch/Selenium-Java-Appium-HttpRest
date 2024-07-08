package it.smartpaper.PageFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import it.smartpaper.DriverManagerHelper.DriverManagerHelper;

public class BooksPage {
    @FindBy(xpath = "//span[text()='Profile']")
    WebElement profileLink;
    
	public BooksPage(String url) {
		WebDriver driver = DriverManagerHelper.getDriver();
		driver.get(url);
		PageFactory.initElements(driver, this);
	}
    
    public void scrollToElement(WebElement element) {
    	WebDriver driver = DriverManagerHelper.getDriver();
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public ProfilePage clickProfileLink() {
    	scrollToElement(profileLink);
        profileLink.click();
        return new ProfilePage();
    }
}

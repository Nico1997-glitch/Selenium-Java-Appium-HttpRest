package it.smartpaper.PageFactory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import it.smartpaper.DriverManagerHelper.DriverManagerHelper;

public class BookStorePage {

    @FindBy(id = "see-book-You Don't Know JS")
    private WebElement bookLink;

    @FindBy(id = "userName-value")
    private WebElement userNameValue;

    private WebDriverWait wait;

    public BookStorePage() {
    	WebDriver driver = DriverManagerHelper.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickOnBook() {
        wait.until(ExpectedConditions.visibilityOf(bookLink)).click();
    }

    public String getActualUserName() {
        WebElement userActual = wait.until(ExpectedConditions.visibilityOf(userNameValue));
        return userActual.getAttribute("value");
    }

}

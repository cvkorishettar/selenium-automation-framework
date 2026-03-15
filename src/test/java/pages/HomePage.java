package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
    @FindBy(xpath = "//span[@class='title']")
    WebElement ProductsTitle;

    @FindBy(xpath = "//button[text()='Add to cart']")
    WebElement addToCartBtn;


     public  boolean isAddToCartDisplayed(){
         return addToCartBtn.isDisplayed();
     }

    public String getTagText(){
        return ProductsTitle.getText();
    }
    public boolean isHomePageDisplayed() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(ProductsTitle)).isDisplayed();
        return ProductsTitle.isDisplayed();
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;

import java.time.Duration;
import java.util.List;
@Listeners
public class CartPage {
    WebDriver driver;
    public CartPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//div[@class='inventory_item_price']")
    List<WebElement> cartProductPrice;

    @FindBy(className = "shopping_cart_badge")
    WebElement cartBadge;



    public double getCartTotal() {
        double actualPrice=0;
//
        System.out.println("Cart Price Size = " + cartProductPrice.size());
        System.out.println("Current URL = " + driver.getCurrentUrl());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_item")));
        for (WebElement cartPrice : cartProductPrice){
            System.out.println("111");
            String price = cartPrice.getText().replace("$", "");
            actualPrice += Double.parseDouble(price);
        }
        return actualPrice;
    }

    public int getCartCount(){
        return Integer.parseInt(cartBadge.getText());
    }
}

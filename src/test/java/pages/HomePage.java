package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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

    @FindBy(xpath = "//div[@class='inventory_item_name ']")
    List<WebElement> productsLoaded;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    List<WebElement> productPrice;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    WebElement carticon;



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
//    WebDriverWait wait =
//            new WebDriverWait(driver, Duration.ofSeconds(10));

//        wait.until(ExpectedConditions
//                .visibilityOfElementLocated(By.className("inventory_item")));
    public double addProductsAndGetTotal(List<String> productNames){
        double expectedTotal = 0;

        for(String product : productNames){

            By productContainer =
                    By.xpath("//div[text()='"+product+"']/ancestor::div[@class='inventory_item']");

            String priceText =
                    driver.findElement(productContainer)
                            .findElement(By.className("inventory_item_price"))
                            .getText()
                            .replace("$","");

            expectedTotal += Double.parseDouble(priceText);

            driver.findElement(productContainer)
                    .findElement(By.tagName("button"))
                    .click();
        }

        return expectedTotal;
    }
    public boolean isProductLoaded(){
        System.out.println(productsLoaded.size());
         return productsLoaded.size()>0;

    }
    public void clickOnCarticon(){
         carticon.click();
    }
}

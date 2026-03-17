package pages;

import enums.LoginResult;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    WebElement userName;
    @FindBy(id = "password")
    WebElement password;
    @FindBy(id = "login-button")
    WebElement loginBtn;
    @FindBy(xpath = "//h3[@data-test='error']")
    WebElement errorMessage;

    public LoginResult login(String user, String pwd) {
        userName.clear();
        password.clear();

        userName.sendKeys(user);
        password.sendKeys(pwd);
        loginBtn.click();

        try {

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

//            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            wait.until(ExpectedConditions.textToBePresentInElement(
                    errorMessage, "Epic sadface"));
            String msg = errorMessage.getText();

            if (msg.contains("locked out")) {
                return LoginResult.LOCKED_USER;
            }

            return LoginResult.INVALID_CREDENTIALS;

        } catch (Exception e) {

            return LoginResult.SUCCESS;
        }
    }

    public void enterUsername(String user) {
        userName.sendKeys(user);
    }

    public void enterPwd(String pwd) {
        password.sendKeys(pwd);
    }

    public boolean clickOnLogin() {
        loginBtn.click();
        try {
            if (errorMessage.isDisplayed()) {
                return false;   // ❌ Invalid login
            }
            return true;        // ✅ Valid login
        } catch (Exception e) {
            return true;        // error element not found → login success
        }
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
//    public LoginResult login(String user, String pwd){
//
//        userName.clear();
//        password.clear();
//
//        userName.sendKeys(user);
//        password.sendKeys(pwd);
//        loginBtn.click();
//
//        WebDriverWait wait =
//                new WebDriverWait(driver, Duration.ofSeconds(12));
//
//        try {
//
//            wait.until(ExpectedConditions.or(
//
//                    ExpectedConditions.urlContains("inventory"),
//                    ExpectedConditions.visibilityOfElementLocated(
//                            By.xpath("//h3[@data-test='error']")
//                    )
//            ));
//
//        } catch (TimeoutException e){
//            System.out.println("Login response slow");
//        }
//
//        // ⭐ SUCCESS CASE
//        if(driver.getCurrentUrl().contains("inventory")){
//            return LoginResult.SUCCESS;
//        }
//
//        // ⭐ ERROR CASE
//        if(driver.findElements(
//                By.xpath("//h3[@data-test='error']")).size() > 0){
//
//            String msg = driver.findElement(
//                    By.xpath("//h3[@data-test='error']")).getText();
//
//            System.out.println("Actual Error: " + msg);
//
//            if(msg.contains("locked out")){
//                return LoginResult.LOCKED_USER;
//            }
//            else{
//                return LoginResult.INVALID_CREDENTIALS;
//            }
//        }
//
//        return LoginResult.INVALID_CREDENTIALS;
//    }


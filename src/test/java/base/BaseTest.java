package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilils.globalIntilize;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    public String ExpectedErrorMessage="Epic sadface: Username and password do not match any user in this service";
    public String homePageurl="https://www.saucedemo.com/inventory.html";
    public WebDriver driver;
    public globalIntilize global;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--incognito");
        options.addArguments("--guest");
        driver=new ChromeDriver(options);
        global=new globalIntilize(driver);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

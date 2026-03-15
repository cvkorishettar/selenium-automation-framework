import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestRun {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");

//        driver.get("https://support.saucelabs.com/s/login/?language=en_US");
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text']")));
//        ele.sendKeys("chennaveerakumar1998@gmail.com");
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        Wait <WebDriver> wait = new FluentWait<>(driver)
//                .withTimeout(Duration.ofSeconds(10))
//                        .pollingEvery(Duration.ofMillis(500))
//                                .ignoring(NoSuchElementException.class);
//        WebElement ele2 = wait.until(d -> d.findElement(By.xpath("//input[@type='text']")));
//        ele2.sendKeys("chennaveerakumar1998@gmail.com");
////        WebElement ele2 = wait.until(driver.findElement(By.xpath("//input[@type='text']")));
////        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("chennaveerakumar1998@gmail.com");
//        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Justdial@123");
//        driver.findElement(By.xpath("//span[text()='Log in']/..")).click();
//        System.out.println(driver.getTitle());
//        System.out.println(driver.getCurrentUrl());

        try {
            Alert a1 = driver.switchTo().alert();
            a1.accept();
        }catch (NoAlertPresentException e){
            System.out.println("No alert");
        }
        JavascriptExecutor js = (JavascriptExecutor ) driver;
//        js.executeScript("window.scrollTo(0.300)");
        WebElement element = driver.findElement(By.id("alertBtn"));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        WebElement element1 = driver.findElement(By.id("confirmBtn"));
        js.executeScript("arguments[0].scrollIntoView(true);", element1);
        element1.click();
//        js.executeScript("window.scrollTo(0, 0)");
        alert.dismiss();
        WebElement element2 = driver.findElement(By.id("promptBtn"));
        Thread.sleep(5000);
        js.executeScript("arguments[0].scrollIntoView(true)",element2);
        Thread.sleep(5000);
        element2.click();
//        js.executeScript("window.scrollTo(0, 0)");
        alert.sendKeys("test");
        Thread.sleep(5000);
        alert.accept();
        driver.close();

    }
}

import io.github.bonigarcia.wdm.WebDriverManager;
import org.bouncycastle.dvcs.DVCSRequestInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HandleFrames {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.automationtesting.in/Frames.html");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollTo(0,400);");
        driver.switchTo().frame("singleframe");
//        js.executeScript("arguments[0].scrollIntoView(true),ele")
        String singleFrame = driver.findElement(By.xpath("//div[@class='row']/preceding::h5[text()='iFrame Demo'][1]")).getText();
        System.out.println(singleFrame);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.switchTo().defaultContent();
        WebElement multiFrame = driver.findElement(By.xpath("//a[contains(text(),'Iframe with in an Iframe')]"));
        wait.until(ExpectedConditions.elementToBeClickable(multiFrame));
        multiFrame.click();
//  Nested Frame
        WebElement frame1 = driver.findElement(By.xpath("//div[@id='Multiple']/iframe"));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame1));
        String nestedFirstFrame = driver.findElement(By.xpath("//h5[text()='Nested iFrames']")).getText();
        System.out.println(nestedFirstFrame);
//Nested Iframe
        WebElement frame2 = driver.findElement(By.xpath("//h5[text()='Nested iFrames']/following-sibling::iframe"));
        driver.switchTo().frame(frame2);
        String nestedSecondFrame = driver.findElement(By.xpath("//h5[contains(text(),'iFrame Demo')]")).getText();
        System.out.println(nestedSecondFrame);
// Accessing nested frame text in nested Iframe
//        driver.switchTo().parentFrame();
//        String accessingnested = driver.findElement(By.xpath("//h5[text()='Nested iFrames']")).getText();
//        System.out.println(accessingnested);
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[text()='Single Iframe ']")).click();
        Thread.sleep(5000);
        driver.quit();
    }
}

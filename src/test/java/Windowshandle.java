import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class Windowshandle {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://testautomationpractice.blogspot.com/");
        driver.manage().window().maximize();
        String parentwindow = driver.getWindowHandle();
        System.out.println(parentwindow);
        driver.findElement(By.xpath("//button[text()='New Tab']")).click();
        Set<String> windows = driver.getWindowHandles();
        for(String window:windows){
            if (!window.equals(parentwindow)){
                driver.switchTo().window(window);
                break;
            }
        }
        String childwindow = driver.getWindowHandle();
        System.out.println(childwindow);
        String childWindowtext = driver.findElement(By.xpath("//h1[@class='title']")).getText();
        String text="SDET-QA Blog";
        if(text.equals(childWindowtext)){
            System.out.println("childWindow text verified");
        }else
            System.out.println("childWindow text Not verified");
        driver.switchTo().window(parentwindow);
        String parentwindowTwice = driver.getWindowHandle();
        System.out.println(parentwindowTwice);
        if (parentwindow.equals(parentwindowTwice)){
            System.out.println("Swifted to parent window and verified");
        }else {
            System.out.println("Mismatch");
        }
        driver.navigate();
        String ParentWindowText = driver.findElement(By.xpath("//h1[@class='title']")).getText();
        System.out.println(ParentWindowText);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement startButton = driver.findElement(By.cssSelector("button[name='start']"));
        js.executeScript("arguments[0].scrollIntoView(true)",startButton);
        startButton.click();
//        driver.close();
        driver.quit();


    }
}

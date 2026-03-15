import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FetchElements {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("https://testautomationpractice.blogspot.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = driver.findElement(By.tagName("option"));
        js.executeScript("arguments[0].scrollIntoView(true);",element);
//        List<WebElement> colors = driver.findElements(By.tagName("option"));
        List<WebElement> colors = driver.findElements(By.xpath("//select[@id='colors']/option"));
        ArrayList<String> colorList = new ArrayList<>();

        for(WebElement color:colors ){
            colorList.add(color.getText().trim());

        }
        System.out.println(colorList);
        System.out.println("---------------------------");
//       ** To remove Duplicates we are adding into hashset **

        Set<String> hs = new HashSet<>(colorList);
        for (String name:hs){
            System.out.println(name);
        }

        driver.quit();

    }
}

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ActionsClass {
    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement visibleText = driver.findElement(By.xpath("//p[contains(text(),'Move the mouse over the button to open the dropdown menu.')]"));
        js.executeScript("arguments[0].scrollIntoView(true);", visibleText);
        Actions action = new Actions(driver);

       WebElement point = driver.findElement(By.xpath("//button[text()='Point Me']"));
        action.moveToElement(point).perform();
//        Thread.sleep(3000);
//  Double click
        WebElement doubleClick = driver.findElement(By.id("field1"));
        action.doubleClick(doubleClick);
//        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[contains(.,'Copy Text')]")).click();
        driver.findElement(By.id("field2")).sendKeys("Text coppied");
// Drag and Drop
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement dragElement = driver.findElement(By.id("draggable"));
//        js.executeScript("arguments[0].scrollIntoView(true)",dragElement);
//        wait.until(ExpectedConditions.visibilityOf(dragElement));

        WebElement dropPoint = driver.findElement(By.id("droppable"));
        action.dragAndDrop(dragElement,dropPoint).perform();
// Right click / context click

//        WebElement ele = driver.findElement(By.id("droppable"));
//        js.executeScript("arguments[0].scrollIntoView(true)",ele);
//        action.contextClick().perform();
//        Thread.sleep(3000);
//        action.contextClick(ele).perform();
//        Thread.sleep(3000);
//  Click And hold
        WebElement clickAndHold = driver.findElement(By.id("draggable"));
//        action.clickAndHold(clickAndHold).perform();
//  Click And hold & Drop to destiny
//        WebElement HoldDrop = driver.findElement(By.id("droppable"));
//        action.clickAndHold(clickAndHold)
//                        .moveToElement(HoldDrop)
//                                .release()
//                                        .build()
//                                                .perform();
//  Click on MouseHovered Element
//        js.executeScript("arguments[0].scrollIntoView(true);",visibleText);
//        WebElement menu = driver.findElement(By.xpath("//button[@class='dropbtn']"));
//        action.moveToElement(menu).perform();
//        driver.findElement(By.xpath("//a[text()='Mobiles']")).click();
// Keys action
        driver.findElement(By.id("Wikipedia1_wikipedia-search-input")).sendKeys("Mobiles");
        action.sendKeys(Keys.ENTER).perform();
//        ArrayList<String> list = new ArrayList<>();

//        List<WebElement> searchedElemets = driver.findElements(By.id("//div[@id='wikipedia-search-result-link']"));
//        for (WebElement searchele:searchedElemets){
////            list.add(searchele.getText().trim());
//            System.out.println(list.add(searchele.getText().trim()));
//        }
//        System.out.println(list);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement moreButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'More »')]")));;
        js.executeScript("window.scrollBy(0,300)");
//        moreButton.click();
//        Thread.sleep(4000);
//        String parentWindow = driver.getWindowHandle();
//        System.out.println(parentWindow);
//        Set<String> windows = driver.getWindowHandles();
//        for (String window:windows){
//            if(!window.equals(parentWindow)){
//                driver.switchTo().window(window);
////                break;
//            }
//        }
//        String childWindow = driver.getWindowHandle();
//        System.out.println(childWindow);
//        @Nullable String storeUrl = driver.getCurrentUrl();
//        System.out.println(storeUrl);
//        String wikiText = driver.findElement(By.id("firstHeading")).getText();
//        System.out.println(wikiText);
//        if(wikiText.equals("Search results")){
//            System.out.println("Pass");
//        }else{
//            System.out.println("Fail");
//        }
//        js.executeScript("arguments[0].scrollIntoView(true)",moreButton);
//        js.executeScript("arguments[0].click()",moreButton);

//        Thread.sleep(4000);
//        String parentWindow = driver.getWindowHandle();

//        moreButton.click();
//        Set<String> allWindows = driver.getWindowHandles();
//
//
//        for (String window:allWindows){
//            if (!parentWindow.equals(window)){
//
//                driver.switchTo().window(window);
//                break;
//            }
//        }

        /**Takes screnshot for Full web page*/

        TakesScreenshot ts = (TakesScreenshot) driver;
//        File file = ts.getScreenshotAs(OutputType.FILE);
//
//        FileUtils.copyFile(file,new File("./screenshotsfiles/text1.png"));

  //    Current date and time concat on screenshots
        String timeStamp = new SimpleDateFormat("dd-MM-yyy_HHmmss").format(new Date());
        File src = ts.getScreenshotAs(OutputType.FILE);
        File desctination = new File("./screenshotsfiles/"+timeStamp+".png");
        FileHandler.copy(src,desctination);
        driver.quit();
    }
}

package utilils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class screenshotUtil {


    public static String takeScreenshot(String name){
//        try {
//            TakesScreenshot ts = (TakesScreenshot) driver;
//            File src = ts.getScreenshotAs(OutputType.FILE);
//
//            FileUtils.copyFile(src, new File("./screenshot/" + timestamp + ".png"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        return "./failedScreenshots/" + name + ".png";
    }

}

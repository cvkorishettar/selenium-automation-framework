package listeners;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilils.ExtentManager;
import utilils.screenshotUtil;
import com.aventstack.extentreports.*;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    private static final Logger log = LoggerFactory.getLogger(TestListener.class);
    ExtentReports extent = ExtentManager.getReport();
        ExtentTest test;

        public void onTestStart(ITestResult result){
            test = extent.createTest(result.getName());
            System.out.println("onTestStart");
        }

        public void onTestSuccess(ITestResult result){
            test.pass("Test Passed");
            System.out.println("onTestSuccess");
        }

        public void onTestFailure(ITestResult result){
            log.info("fail step");
            test.fail(result.getThrowable());
            if(result.getStatus()==ITestResult.FAILURE) {

                Object testClass = result.getInstance();

                WebDriver driver = ((BaseTest) testClass).driver;

                TakesScreenshot ts = (TakesScreenshot) driver;
                File src = ts.getScreenshotAs(OutputType.FILE);

                try {
                    String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HHmmss").format(new Date());
                    FileUtils.copyFile(src,new File("./screenshotfiles/"+timeStamp+".png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            String path = screenshotUtil.takeScreenshot(result.getName());
            System.out.println("onTestFailure");
            test.addScreenCaptureFromPath(path);
        }

        public void onFinish(ITestContext context){
            System.out.println("onFinish");
            extent.flush();
        }
    }


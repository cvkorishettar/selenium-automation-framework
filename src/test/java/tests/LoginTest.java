package tests;

import base.BaseTest;
import enums.LoginResult;
import listeners.TestListener;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import utilils.screenshotUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {
    static Logger log = Logger.getLogger(String.valueOf(LoginTest.class));
    @DataProvider(name = "loginData")
    public Object[][] data(){
        return new Object[][]{
                {"standard_user","secret_sauce"},
                {"locked_out_user","secret_sauce"},
//                {"problem_user","secret_sauce"},
//                {"performance_glitch_user","secret_sauce"},
//                {"error_user","secret_sauce"},
//                {"visual_user","secret_sauce"},
                {"visual","secret_sauce"}
        };
    }
    @Test(dataProvider = "loginData")
    public void loginScenarioTest(String user,String pwd){
        LoginPage lg = new LoginPage(driver);
        HomePage hg=new HomePage(driver);
        LoginResult result =
                lg.login(user,pwd);

        switch (result){

            case SUCCESS:
                System.out.println("Login success → continue flow");
                System.out.println(hg.getTagText());
                break;

            case INVALID_CREDENTIALS:

                String invalidActual = lg.getErrorMessage();
                System.out.println(invalidActual);
                Assert.assertEquals(invalidActual,ExpectedErrorMessage,"Invalid user");
                Assert.fail("Invalid credentials login failed");
                break;

            case LOCKED_USER:
                String actual =
                        lg.getErrorMessage();
                System.out.println(actual);
                Assert.assertEquals(actual,
                        "Epic sadface: Sorry, this user has been locked out.",
                        "Locked user message mismatch");

                Assert.fail("User account is locked → stop execution");

                break;
        }
    }

//    @Test(groups = "smoke",dataProvider = "loginData")
    public void runLogincase(String user,String pwd) throws InterruptedException {
        LoginPage lg = new LoginPage(driver);

//        log.info("browser launched");
        lg.enterUsername(user);
//        log.info("Entered username ");
        lg.enterPwd(pwd);
//        log.info("Entered password");
        boolean value = lg.clickOnLogin();
        if (value){
            System.out.println("Valid user continue the flow");
            HomePage hg=new HomePage(driver);
            boolean isLoginSuccess =
                    driver.getCurrentUrl().equals(homePageurl);

            Assert.assertTrue(isLoginSuccess,"Login Failed for valid user: " + user);

        }else{
            System.out.println("Invalid user stop the execution");
            Assert.assertEquals(lg.getErrorMessage(),ExpectedErrorMessage,"Invalid user");
            Assert.fail("Invalid user will continue for execution");
        }


        //Soft Assert if above execution fails it will continue futher execution and gives result

//        SoftAssert sa = new SoftAssert();
//        sa.assertEquals(true,hg.isHomePageDisplayed(),"Element is not Displayed");
//        sa.assertEquals(true,hg.isAddToCartDisplayed(),"Element is not Displayed");
//        sa.assertAll();
//        sa.assertTrue(hg.isHomePageDisplayed());
//        System.out.println("Executed after login Soft Assert");

        // Hard Assert will stop execution when single line fails
//        Assert.assertTrue(hg.isHomePageDisplayed());
//        Assert.assertTrue(hg.isAddToCartDisplayed());
//        Assert.assertEquals(true,hg.isHomePageDisplayed());
//        Assert.assertEquals(true,hg.isAddToCartDisplayed());
//        System.out.println("Executed after login Hard Assert");
//        sa.assertEquals(hg.isHomePageDisplayed(),"Products","Fail");
//        sa.assertEquals(hg.isAddToCartDisplayed(),"Add to cart","Fail");


//        log.info("login successfully");


//        if(driver.getCurrentUrl().equals(homePageurl)) {
//            System.out.println("Login Successfull");
//            System.out.println("Verified valid user is login into the apllication");
//        }
//        else {
//            if(user.equals("visual")){
//                System.out.println("Verified Invalid user to restricting to enter into the application");
//            }else{
//                System.out.println("valid user failed to login");
//            }
//            String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HHmmss").format(new Date());
//            screenshotUtil.takeScreenshot(driver,timeStamp);
//            System.out.println("Login UnSuccessfull ");
//            Assert.fail("Login Failed");
//
//        }

//        Assert.assertTrue(hg.isHomePageDisplayed(),"Home Page WebElement is Not Displayed"+ user);
//        Assert.assertEquals("Add to cart",hg.isAddToCartDisplayed(),"success");

    }
}

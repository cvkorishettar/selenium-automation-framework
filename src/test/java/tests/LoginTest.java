package tests;

import base.BaseTest;
import enums.LoginResult;
import listeners.TestListener;
import org.jspecify.annotations.Nullable;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilils.globalIntilize;
import java.util.logging.Logger;


@Listeners(TestListener.class)
public class LoginTest extends BaseTest {
    static Logger log = Logger.getLogger(String.valueOf(LoginTest.class));

    @DataProvider(name = "loginData")
    public Object[][] data() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
//                {"problem_user","secret_sauce"},
//                {"performance_glitch_user","secret_sauce"},
//                {"error_user","secret_sauce"},
//                {"visual_user","secret_sauce"},
                {"visual", "secret_sauce"}
        };
    }

    @Test(dataProvider = "loginData")
    public void loginScenarioTest(String user, String pwd) {
        LoginResult result = global.lg.login(user, pwd);
        SoftAssert sa = new SoftAssert();
        switch (result) {

            case SUCCESS:
                Assert.assertTrue(true,"Login success");
                System.out.println(global.hg.getTagText());
                @Nullable String url = driver.getCurrentUrl();
                Assert.assertTrue(url.contains("https://www.saucedemo.com/inventory.html"));
                break;

            case INVALID_CREDENTIALS:

                String invalidActual =global.lg.getErrorMessage();
                System.out.println(invalidActual);
//                *< used Soft Assert for invalid user validation >*
                sa.assertEquals(invalidActual,ExpectedErrorMessage,"Invalid user");
                sa.fail("Invalid credentials login failed");

                break;

            case LOCKED_USER:
                String actual =
                        global.lg.getErrorMessage();
//                *< used Soft Assert for Locked user validation >*
                sa.fail("User account is locked → stop execution");
                sa.assertEquals(actual,"Epic sadface: Sorry, this user has been locked out.",
                "Locked user message mismatch");
                break;
        }

    }
}




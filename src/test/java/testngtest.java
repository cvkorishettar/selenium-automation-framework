import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeoutException;

public class testngtest {

        @BeforeMethod
        public void setup() {
            System.out.println("Before method");
        }
//expectedExceptions = TimeoutException.class
        @Test(enabled = true,invocationCount = 1)
        public void test1() {
            System.out.println("Test Case 1");
        }

        @Test(dependsOnMethods = "test1",dataProvider = "sampleData")
        public void test2(String username,String pwd) {
            System.out.println("Test Case 2");
            System.out.println(username+" "+pwd);
        }

        @AfterMethod
        public void closeBrowser() {
            System.out.println("After method");
        }
        @DataProvider(name = "sampleData")
        public Object[][] data(){
                return new Object[][]{{"user1","123"},{"user2","456"}
                };
    }

}

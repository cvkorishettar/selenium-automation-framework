package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.Arrays;
import java.util.List;

public class addToCart extends BaseTest {
    @Test
    public void addCartValidation() throws InterruptedException {
        LoginPage lg = new LoginPage(driver);
        HomePage hg = new HomePage(driver);
        CartPage cg = new CartPage(driver);
        lg.enterUsername("standard_user");
        lg.enterPwd("secret_sauce");
        lg.clickOnLogin();
        Assert.assertTrue(hg.isProductLoaded());
        List<String> items = Arrays.asList(
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt"
        );

        double expected = hg.addProductsAndGetTotal(items);
//        System.out.println(hg.addProductsAndGetTotal());
        hg.clickOnCarticon();
        double actualPrice = cg.getCartTotal();
        Assert.assertEquals(actualPrice,expected);
        Assert.assertEquals(cg.getCartCount(),3);

    }
}

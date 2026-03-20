package utilils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.LoginPage;
import pages.HomePage;
import pages.CartPage;

public class globalIntilize {
    WebDriver driver;
    public LoginPage lg;
    public HomePage hg;
    public CartPage cg;
    public globalIntilize(WebDriver driver) {
        this.driver = driver;
        lg = new LoginPage(driver);
        hg = new HomePage(driver);
        cg = new CartPage(driver);
    }


}

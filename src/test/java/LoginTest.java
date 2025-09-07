import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testLoginAndAddToCart() throws InterruptedException {
        // Log in
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Add item to cart
        WebElement item = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        item.click();

        // Assert item is in cart
        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(cartBadge.getText(), "1");

        // Pause to observe browser actions
        Thread.sleep(5000); // waits 5 seconds
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit(); // closes the browser after pause
        }
    }
}

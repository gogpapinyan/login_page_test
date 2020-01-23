package AssignMentOne;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

/**
 * This class implements some tests over http://the-internet.herokuapp.com/ website Form Authentication page.
 * Check if page is opened successfully.
 * Check if user can successfully sign in
 * Check if error message is shown when user input wrong username/pass
 * Check if error message is shown when one of fields is missing.
 *
 *
 * @author gohar.papinyan
 * @version 1.0
 * @since 20-Jan-20
 */
public class LoginTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }
    @Test
    public void openTheInternet(){

        driver.get("http://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        String headerText = driver.findElement(By.className("heading")).getText();
        Assert.assertEquals(headerText,"Welcome to the-internet", "Link is not opened, something went wrong.");
    }
    @Test
    public void openFormAuthenticationByLink() {

        driver.get("http://the-internet.herokuapp.com/login");
        Assert.assertEquals(getFormAuthenticationHeadText(),"Login Page",
                "Something went wrong when opening form authentication by link.");

    }

    @Test
    public void openFormAuthenticationByClick() {
        driver.get("http://the-internet.herokuapp.com/");
        driver.findElement(By.linkText("Form Authentication")).click();
        Assert.assertEquals(getFormAuthenticationHeadText(),"Login Page",
                "Something went wrong when opening form authentication by click.");
    }
    @Test
    public void verifyLogin() {
        driver.get("http://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button.radius")).click();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl,"http://the-internet.herokuapp.com/secure", "Login failed");
    }

    private String getFormAuthenticationHeadText(){
        return driver.findElement(By.cssSelector("h2")).getText();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

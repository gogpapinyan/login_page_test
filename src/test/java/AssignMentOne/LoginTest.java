package AssignMentOne;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

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

    @BeforeMethod
    public void OpenTheInternet() {
        setWebDriverPath();

        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        WebElement header = driver.findElement(By.className("heading"));
        String headerText = header.getText();
        Assert.assertEquals(headerText,"Welcome to the-internet", "Link is not opened, something went wrong.");
    }

    @Test
    public void OpenFormAuthenticationByLink() {
        driver.get("http://the-internet.herokuapp.com/login");
       checkOpenFormAuthentication();
    }

    @Test
    public void OpenFormAuthentication() {
        WebElement formAuthenticationRef = driver.findElement(By.cssSelector("#content > ul > li:nth-child(21) > a"));
        formAuthenticationRef.click();
        checkOpenFormAuthentication();
    }
    @Test
    public void Login() {
        OpenFormAuthentication();
        WebElement usernameInputField = driver.findElement(By.id("username"));
        usernameInputField.sendKeys("tomsmith");
        WebElement passwordInputField = driver.findElement(By.id("password"));
        passwordInputField.sendKeys("SuperSecretPassword!");

        WebElement loginButton = driver.findElement(By.cssSelector("#login > button"));
        loginButton.click();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl,"http://the-internet.herokuapp.com/secure", "Login failed");
    }

    public void checkOpenFormAuthentication(){
        WebElement loginPageHeader = driver.findElement(By.className("example"));
        String loginPageHeaderText = loginPageHeader.findElement(By.tagName("h2")).getText();
        Assert.assertEquals(loginPageHeaderText, "Login Page", "Login Page is not opened by link.");
    }
    public void setWebDriverPath() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}

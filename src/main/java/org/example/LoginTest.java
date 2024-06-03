package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

public class LoginTest {
    WebDriver driver;
    WebElement username;
    WebElement password;
    WebElement login;
    @BeforeMethod
    public void setup()
    {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        username = driver.findElement(By.id("user-name"));
        password = driver.findElement(By.id("password"));
        login = driver.findElement(By.id("login-button"));

    }
    @AfterMethod
    public void quit()
    {
        driver.quit();
    }
    @Test (priority = 1)
    public void tc1CheckFields()
    {
        Assert.assertTrue(username.isDisplayed());
        Assert.assertTrue(password.isDisplayed());
        Assert.assertTrue(login.isDisplayed());
    }
    @Test(priority = 2)
    public void tc2CorrectLogin()
    {
        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        login.click();
        WebElement swaglabs = driver.findElement(By.xpath("//div[text()='Swag Labs']"));
        Assert.assertTrue(swaglabs.isDisplayed());
    }
    @Test (priority = 3)
    public void tc3FailedLogin()
    {
        username.sendKeys("aaa");
        password.sendKeys("aaa");
        login.click();
        WebElement error = driver.findElement(By.xpath("//div[@class='error-message-container error']"));
        WebElement errorMessage = driver.findElement(By.xpath("//h3[text()='Epic sadface: Username and password do not match any user in this service']"));
        Assert.assertTrue(error.isDisplayed());
        Assert.assertTrue(errorMessage.isDisplayed());
    }
    @Test ( priority = 4)
    public void tc4EmptyUserName()
    {
        password.sendKeys("aaa");
        login.click();
        WebElement error = driver.findElement(By.xpath("//div[@class='error-message-container error']"));
        WebElement errorMessage = driver.findElement(By.xpath("//h3[text()='Epic sadface: Username is required']"));
        Assert.assertTrue(error.isDisplayed());
        Assert.assertTrue(errorMessage.isDisplayed());
    }
    @Test (priority = 5)
    public void tc5EmptyPassword()
    {
        username.sendKeys("aaa");
        login.click();
        WebElement error2 = driver.findElement(By.xpath("//div[@class='error-message-container error']"));
        WebElement error2Message = driver.findElement(By.xpath("//h3[text()='Epic sadface: Password is required']"));
        Assert.assertTrue(error2.isDisplayed());
        Assert.assertTrue(error2Message.isDisplayed());
    }
}

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GoogleLoginTest {

    WebDriver driver;

    @BeforeMethod
    void setUP(){
        driver = new ChromeDriver();
        driver.get("https://www.google.com.ua/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Войти")).click();
    }

    @Test(priority = 1, description = "negative email")
    public void loginToGoogleNegativeEmail() throws InterruptedException {
        WebElement emailField = driver.findElement(By.xpath(".//*[@id='identifierId']"));
        emailField.sendKeys("lihoy92ivan@gmail.ru");
        WebElement nextButton = driver.findElement(By.cssSelector("#identifierNext"));
        nextButton.click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='view_container']/form/div[2]/div/div[1]/div[1]/div/div[2]/div[2]/div")).getText(), "Не удалось найти аккаунт Google");
    }

    @Test(priority = 2, description = "positive email")
    void loginToGooglePositiveEmail() throws InterruptedException {
        WebElement emailField = driver.findElement(By.xpath(".//*[@id='identifierId']"));
        emailField.sendKeys("lihoy92ivan@gmail.com");
        WebElement nextLoginButton = driver.findElement(By.cssSelector("#identifierNext"));
        nextLoginButton.click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='password']/div[1]/div/div[1]/input")).isDisplayed());
    }

    @Test(priority = 3, description = "negative password")
    void loginToGoogleNegativePassword() throws InterruptedException {
        loginToGooglePositiveEmail();
        WebElement passField = driver.findElement(By.xpath(".//*[@id='password']/div[1]/div/div[1]/input"));
        passField.sendKeys("ivan12345");
        WebElement nextPassButton = driver.findElement(By.cssSelector("#passwordNext"));
        nextPassButton.click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='password']/div[2]/div[2]")).getText(), "Неверный пароль. Повторите попытку.");
    }

    @Test(priority = 4, description = "positive password")
    void loginToGooglePositivePassword() throws InterruptedException {
        loginToGooglePositiveEmail();
        WebElement passField = driver.findElement(By.xpath(".//*[@id='password']/div[1]/div/div[1]/input"));
        passField.sendKeys("ivan54321");
        WebElement nextPassButton = driver.findElement(By.cssSelector("#passwordNext"));
        nextPassButton.click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.linkText("Почта")).isDisplayed());
    }


    @AfterMethod
    void tearDown(ITestResult testResult){
        System.out.println(testResult.getMethod().getDescription());
        driver.close();
    }
}

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
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

    private final String negativeEmail = "lihoy92ivan@gmail.ru";
    private final String positiveEmail = "lihoy92ivan@gmail.com";
    private final String negativePassword = "ivan12345";
    private final String positivePassword = "ivan54321";

    @BeforeMethod
    void setUP(){
        driver = new ChromeDriver();
        driver.get("https://www.google.com.ua/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Войти")).click();
    }

    private void checkEmail(String email){
        WebElement emailField = driver.findElement(By.xpath(".//*[@id='identifierId']"));
        emailField.sendKeys(email);
        WebElement nextButton = driver.findElement(By.cssSelector("#identifierNext"));
        nextButton.click();
    }

    private void checkPassword(String password){
        WebElement passField = driver.findElement(By.xpath(".//*[@id='password']/div[1]/div/div[1]/input"));
        passField.sendKeys(password);
        WebElement nextPassButton = driver.findElement(By.cssSelector("#passwordNext"));
        nextPassButton.click();
    }

    private boolean checkElementPresence(String selector) {
        try {
            driver.findElement(By.xpath(selector));
            return true;
        } catch (ElementNotFoundException e) {
            return false;
        }
    }

    @Test(priority = 1, description = "negative email")
    void loginToGoogleNegativeEmail() throws InterruptedException {
        checkEmail(negativeEmail);
        Thread.sleep(2000);
        checkElementPresence(".//*[@id='view_container']/form/div[2]/div/div[1]/div[1]/div/div[2]/div[2]/div");
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='view_container']/form/div[2]/div/div[1]/div[1]/div/div[2]/div[2]/div")).getText(), "Не удалось найти аккаунт Google");
    }

    @Test(priority = 2, description = "positive email")
    void loginToGooglePositiveEmail() throws InterruptedException {
        checkEmail(positiveEmail);
        Thread.sleep(2000);
        Assert.assertTrue(checkElementPresence(".//*[@id='password']/div[1]/div/div[1]/input"));
    }

    @Test(priority = 3, description = "negative password")
    void loginToGoogleNegativePassword() throws InterruptedException {
        checkEmail(positiveEmail);
        checkPassword(negativePassword);
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='password']/div[2]/div[2]")).getText(), "Неверный пароль. Повторите попытку.");
    }

    @Test(priority = 4, description = "positive password")
    void loginToGooglePositivePassword() throws InterruptedException {
        checkEmail(positiveEmail);
        checkPassword(positivePassword);
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.linkText("Почта")).isDisplayed());
    }

    @AfterMethod
    void tearDown(ITestResult testResult){
        System.out.println(testResult.getMethod().getDescription());
        driver.close();
    }
}

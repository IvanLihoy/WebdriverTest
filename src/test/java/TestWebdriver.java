import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class TestWebdriver {

    public static WebDriver driver;

    @BeforeMethod
    public static void setup(){
        driver = new ChromeDriver();
        driver.get("https://www.random.org/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
    }

    @Test
    public static void test() throws InterruptedException {
        WebElement min = driver.findElement(By.cssSelector("#true-random-integer-generator-min"));
        min.clear();
        min.sendKeys("10");
        Thread.sleep(1000);
        WebElement max = driver.findElement(By.cssSelector("#true-random-integer-generator-max"));
        max.clear();
        max.sendKeys("30");
        Thread.sleep(1000);
        WebElement button = driver.findElement(By.cssSelector("#true-random-integer-generator-button"));
        button.click();
        Thread.sleep(1000);
        Assert.assertTrue(driver.findElement(By.cssSelector("#true-random-integer-generator-result")).isDisplayed());
        WebElement element = driver.findElement(By.cssSelector("#true-random-integer-generator-result"));
        System.out.println(element.getText());
        Integer integer = Integer.parseInt(element.getText());
        Assert.assertTrue(integer > 0 && integer < 100);
    }

    @AfterMethod
    public static void teardown(){
        driver.close();
    }
}

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class TestWebDriver {

    private static WebDriver driver;

    @BeforeMethod
    public static void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.random.org/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
    }

    @Test
    public static void numberGeneration() throws InterruptedException {
        WebElement min = driver.findElement(By.cssSelector("#true-random-integer-generator-min"));
        min.clear();
        min.sendKeys("10");
        Thread.sleep(1000);
        WebElement max = driver.findElement(By.cssSelector("#true-random-integer-generator-max"));
        max.clear();
        max.sendKeys("50");
        Thread.sleep(1000);
        WebElement button = driver.findElement(By.cssSelector("#true-random-integer-generator-button"));
        button.click();
        Thread.sleep(2000);
        WebElement result = driver.findElement(By.cssSelector("#true-random-integer-generator-result"));
        System.out.println("Generated number: " + result.getText());
        Integer integer = Integer.parseInt(result.getText());
        Assert.assertTrue(integer >= 10 && integer <= 50);
    }

    @AfterMethod
    public static void teardown() {
        driver.close();
    }
}

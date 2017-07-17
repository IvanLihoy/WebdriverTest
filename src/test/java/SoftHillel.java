
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class SoftHillel {

    WebDriver driver;

    @BeforeTest
    void setUP(){
        driver = new ChromeDriver();
        driver.get("http://soft.it-hillel.com.ua:3000/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createUser() throws InterruptedException {
        WebElement plusButton = driver.findElement(By.cssSelector(".mdi-content-add"));
        plusButton.click();
        sleep(2);
        WebElement nameField = driver.findElement(By.cssSelector("#icon_prefix"));
        nameField.clear();
        nameField.sendKeys("Ivan");
        WebElement phoneField = driver.findElement(By.cssSelector("#icon_telephone"));
        phoneField.clear();
        phoneField.sendKeys("111-111-1111");
        driver.findElement(By.xpath("html/body/div[1]/div[2]/a")).click();
        Actions action = new Actions(driver);
        WebElement elem = driver.findElement(By.xpath("html/body/div[1]/div[1]/div"));
        action.moveToElement(elem);
        action.perform();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,450)", "");
        sleep(10);
    }

    @AfterTest
    void tearDown(){
        driver.quit();
    }
}

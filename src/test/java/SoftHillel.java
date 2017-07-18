
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SoftHillel {

    WebDriver driver;

    @BeforeTest
    void setUP() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.get("http://soft.it-hillel.com.ua:3000/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:"), new DesiredCapabilities());


    }

    void stuff() throws AWTException {
        ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));

        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");

        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_ESCAPE);

        Actions a = new Actions(driver);
        a.contextClick().build().perform();

        ((JavascriptExecutor)driver).executeScript("");


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

        driver.findElement(By.cssSelector(".select-dropdown")).click();

        Actions action = new Actions(driver);
        WebElement elem = driver.findElement(By.xpath("html/body/div[1]/div[1]/div"));
        action.moveToElement(elem);
        action.perform();

//        driver.findElement(By.xpath("html/body/div[1]/div[2]/a")).click();
//        Actions action = new Actions(driver);
//        WebElement elem = driver.findElement(By.xpath("html/body/div[1]/div[1]/div"));
//        action.moveToElement(elem);
//        action.perform();
//        JavascriptExecutor jse = (JavascriptExecutor)driver;
//        jse.executeScript("window.scrollBy(0,450)", "");
//        sleep(10);
    }

    @AfterTest
    void tearDown(){
        driver.quit();
    }
}

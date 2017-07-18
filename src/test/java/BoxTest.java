import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;

import java.util.concurrent.TimeUnit;


public class BoxTest {
    WebDriver driver;

    @BeforeTest
    void setUP() throws MalformedURLException {
        driver = new ChromeDriver();
        driver.get("https://www.box.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    void uploadFile() throws InterruptedException, AWTException {
        driver.findElement(By.linkText("Log in")).click();
        driver.findElement(By.cssSelector("input[name='login']")).sendKeys("rvalek@intersog.de");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("welcome2hillel");
        driver.findElement(By.cssSelector("div.form-buttons")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='mod-action-bar-1']/div[2]/div[2]/div[2]/a/span[1]")).click();
        driver.findElement(By.xpath(".//*[@id='upload-menu']/li[1]/label")).click();
        Thread.sleep(2000);

        setClipboardData("/home/hillel/IdeaProjects/WebdriverTest/chromedriver");
        Thread.sleep(2000);

        Robot robot = new Robot();
        robot.delay(1000);
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_CONTROL);

        robot.delay(300);
        robot.keyPress(KeyEvent.VK_V);
        Thread.sleep(2000);

        robot.delay(300);
        Thread.sleep(2000);
        robot.keyRelease(KeyEvent.VK_V);
        Thread.sleep(2000);

        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(2000);

        robot.delay(300);
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(2000);


        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);

        robot.delay(300);
        Thread.sleep(5000);

    }
     void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }


    @AfterTest
    void tearDown(){
        driver.quit();
    }
}

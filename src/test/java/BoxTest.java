import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
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
        driver.get("https://account.box.com/login");
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
    void uploadFile() {
        driver.findElement(By.cssSelector("input[name='login']")).sendKeys("rvalek@intersog.de");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("welcome2hillel");
        driver.findElement(By.cssSelector("div.form-buttons")).click();
        sleep(2);
        driver.findElement(By.cssSelector("a[aria-owns='upload-menu']")).click();
        driver.findElement(By.cssSelector("li[class='upload-file-handler-target']")).click();
        sleep(2);
        driver.findElement(By.cssSelector("input[class='upload-handler-picker']")).sendKeys("C:\\Users\\Ivan\\IvanLihoyBoxTest.txt");
        sleep(5);
        Assert.assertTrue(driver.findElement(By.linkText("IvanLihoyBoxTest.txt")).isDisplayed());
    }

//    @Test
//    void uploadFileRobot() throws AWTException {
//        driver.findElement(By.cssSelector("input[name='login']")).sendKeys("rvalek@intersog.de");
//        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("welcome2hillel");
//        driver.findElement(By.cssSelector("div.form-buttons")).click();
//        sleep(2);
//        driver.findElement(By.cssSelector("a[aria-owns='upload-menu']")).click();
//        driver.findElement(By.cssSelector("li[class='upload-file-handler-target']")).click();
//        sleep(2);
//
//        setClipboardData("IvanLihoyBoxTest.txt");
//        sleep(2);
//
//        Robot robot = new Robot();
//        robot.delay(1000);
//        robot.keyPress(KeyEvent.VK_CONTROL);
//        sleep(2);
//        robot.delay(300);
//        robot.keyPress(KeyEvent.VK_V);
//        sleep(2);
//        robot.delay(300);
//        robot.keyRelease(KeyEvent.VK_V);
//        sleep(2);
//        robot.delay(300);
//        robot.keyRelease(KeyEvent.VK_CONTROL);
//        sleep(2);
//        robot.delay(300);
//        robot.keyPress(KeyEvent.VK_ENTER);
//        sleep(2);
//        robot.delay(300);
//        robot.keyRelease(KeyEvent.VK_ENTER);
//        sleep(2);
//        robot.delay(300);
//        sleep(5);
//
//        Assert.assertTrue(driver.findElement(By.linkText("IvanLihoyBoxTest.txt")).isDisplayed());
//    }
//
//     private void setClipboardData(String string) {
//        StringSelection stringSelection = new StringSelection(string);
//        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
//    }

    @AfterTest
    void tearDown(){
        driver.quit();
    }
}

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by hillel on 14.07.17.
 */
public class SoftHillel {

    WebDriver driver;

    @BeforeMethod
    void setUP(){
        driver = new ChromeDriver();
        driver.get("http://soft.it-hillel.com.ua:3000/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    void createUser() throws InterruptedException {
        WebElement plusButton = driver.findElement(By.cssSelector(".mdi-content-add"));
        plusButton.click();
        Thread.sleep(2000);
        WebElement nameField = driver.findElement(By.cssSelector("#icon_prefix"));
        nameField.clear();
        nameField.sendKeys("Ivan");
        WebElement phoneField = driver.findElement(By.cssSelector("#icon_telephone"));
        phoneField.clear();
        phoneField.sendKeys("111-111-1111");
        WebElement dropdown = driver.findElement(By.cssSelector(".select-dropdown"));
        dropdown.click();
//        Select select = new Select(driver.findElement(By.cssSelector(".initialized")));
//        select.selectByVisibleText("Administrator");
        driver.findElement(By.xpath(".//*[@id='select-options-dc96ea45-38fd-2d54-b41c-f8ef088d3f9d']/li[1]/span")).click();
    }

    @AfterMethod
    void tearDown(ITestResult testResult){
        //System.out.println(testResult.getMethod().getDescription());
        driver.close();
    }
}

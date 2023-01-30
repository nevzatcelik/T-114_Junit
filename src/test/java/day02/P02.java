package day02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class P02 {
    /*
    1. Launch browser
    2. Navigate to url 'http://automationexercise.com'
    3. Verify that categories are visible on left side bar

     */
    WebDriver driver;
    String https="https://www.";

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void exerciseTest() throws InterruptedException {
         //  1. Launch browser
        //    2. Navigate to url 'http://automationexercise.com'
        driver.get(https+"automationexercise.com");
        //3. Verify that categories are visible on left side bar`
        WebElement categoryYazisi= driver.findElement(By.xpath("//h2[text()='Category']"));
        Assert.assertTrue(categoryYazisi.isDisplayed());
        //  4. Click on 'Women' category
        driver.findElement(By.xpath("//a[@href='/products']")).click();
        Thread.sleep(2000);
        //reklamin
      //  driver.findElement(By.xpath("//div[@id='dismiss-button']")).click();
        WebElement womenButton= driver.findElement(By.xpath("//a[@href='#Women']"));
        womenButton.click();

    }

    @After
    public void tearDown(){
        //driver.close();
    }
}

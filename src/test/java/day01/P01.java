package day01;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class P01 {
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
    public void test01(){
        driver.get(https+"google.com");
        driver.navigate().to(https+"amazon.com");
        driver.navigate().refresh();
        driver.navigate().back();
        driver.navigate().forward();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to(https+"facebook.com");
     //   driver.switchTo().frame("webelement").switchTo();





    }


    @After
    public void tearDown(){
     // driver.close();
    }
}

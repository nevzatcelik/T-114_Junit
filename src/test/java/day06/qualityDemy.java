package day06;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class qualityDemy {
    WebDriver driver;
    // baslangic kurulumlarini tamamlayin
    // https://qualitydemy.com/ sayfasina gidin
    // cookies i kabul edin
    // login linkine tiklayin
    // email box ve password box kutusuna giris bilgilerini girin
    // siteye giris yapildigini test edin


    @Before
    public void setUp(){
        // ilgili kurulumlari tamamlayalim
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        // https://qualitydemy.com/ sayfasina gidin
        driver.get("https://qualitydemy.com/");
        // cookies i kabul edin
        WebElement cookies= driver.findElement(By.xpath("//*[text()='Accept']"));
        cookies.click();
        // login linkine tiklayin
        driver.findElement(By.xpath("//*[text()='Log in']")).click();
    }


    @Test
    public void qualityPositiveTest(){
        WebElement emailBox=driver.findElement(By.xpath("//input[@type='email']"));
        WebElement loginButton= driver.findElement(By.xpath("(//*[text()='Login'])[2]"));
        Actions actions=new Actions(driver);
        actions.sendKeys(emailBox,"anevzatcelik@gmail.com")
                .sendKeys(Keys.TAB)
                .sendKeys("Nevzat152032")
                .click(loginButton).perform();

        WebElement instructorText= driver.findElement(By.xpath("//*[text()='Instructor']"));
        Assert.assertTrue(instructorText.isDisplayed());

    }

    @Test
    public void negativeTest() throws InterruptedException {

        WebElement emailBox=driver.findElement(By.xpath("//input[@type='email']"));
        WebElement loginButton= driver.findElement(By.xpath("(//*[text()='Login'])[2]"));
        Actions actions=new Actions(driver);
        actions.sendKeys(emailBox,"anevzatcelik@gmailcom")
                .sendKeys(Keys.TAB)
                .sendKeys("Nevzat152030")
                .click(loginButton).perform();
        Thread.sleep(1000);

        WebElement message= driver.findElement(By.xpath("//div[@class='toast-message']"));
        Assert.assertTrue(message.isDisplayed());

    }


    @After
    public void tearDown(){
        driver.close();
    }
}

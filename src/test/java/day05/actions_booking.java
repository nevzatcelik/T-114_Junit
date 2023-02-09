package day05;

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

public class actions_booking {
    // driverimiz ile ilgili kurulumlari baslatalim
    // https://www.booking.com/ sayfasina gidelim
    // para birimi olarak TL secelim
    // ulke olarak Turkiye yi secelim
    // sayfanin en altindan ulkeler kismini secelim
    // ulkeler sayfasindan turkiye yi secelim
    // turkiye sayfasinin secildigini test edin
WebDriver driver;
    @Before
    public void setUp(){
        // ilgili kurulumlari tamamlayalim
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void bookingTest() throws InterruptedException {
        // https://www.booking.com/ sayfasina gidelim
        driver.get("https://www.booking.com/");
        driver.findElement(By.xpath("//*[text()='Accept']")).click();
        Thread.sleep(1500);
        WebElement paraBirimi= driver.findElement(By.xpath("//button[@data-testid='header-currency-picker-trigger']"));
        Actions actions=new Actions(driver);
        actions.moveToElement(paraBirimi).click().perform();
        WebElement turkLirasi= driver.findElement(By.xpath("//*[text()='TRY']"));
        //  actions.scrollToElement(turkLirasi).click(turkLirasi).perform();
        Thread.sleep(1000);
        actions.sendKeys(Keys.ARROW_UP).click(turkLirasi).perform();
        WebElement dil= driver.findElement(By.xpath("//button[@data-testid='header-language-picker-trigger']"));
        actions.click(dil).perform();
        WebElement dilsecimi= driver.findElement(By.xpath("(//button[@data-testid='selection-item'])[31]"));
        actions.click(dilsecimi).perform();
        // sayfanin en altindan ulkeler kismini secelim
        actions.sendKeys(Keys.END).build().perform();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//a[@data-ga='seoindexlinks'])[1]")).click();
        // ulkeler sayfasindan turkiye yi secelim
        WebElement Turkiye= driver.findElement(By.xpath("//*[@id=\"countryTmpl\"]/div[43]/div[1]/h2/a"));
        actions.scrollToElement(Turkiye).click(Turkiye).perform();
        // turkiye sayfasinin secildigini test edin
        WebElement turkiyeYazisi= driver.findElement(By.xpath("//span[@class='sb-searchbox__title-text']"));

        Assert.assertTrue(turkiyeYazisi.getText().contains("Türkiye"));
        //  String expected="";
       // String actual=turkiyeYazisi.getText();

    }

    @After
    public void tearDown(){
        //driver.close();
    }
}

package day03;

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

import java.time.Duration;

public class P03 {
      // ilgili kurulumlari tamamlayalim
     // Kullanici https://www.google.com adresine gider
    // Kullanici cookies i kabul eder
   // Arama Kutusuna karsilastirma yapmak istedigi para birimlerini girer
   // Para birimlerinin karsilastirmasini alir
  // Karsilastirilacak olan para biriminin 1.5 birimden kucuk oldugu test edilir

    WebDriver driver;
    String https="https://www.";
    @Before
    public void setUp(){
        // ilgili kurulumlari tamamlayalim
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @Test
    public void google(){
        // Kullanici https://www.google.com adresine gider
        driver.get(https+"google.com");
        // Kullanici cookies i kabul eder
        driver.findElement(By.xpath("//*[text()='Accept all']")).click();
        // Arama Kutusuna karsilastirma yapmak istedigi para birimlerini girer
        WebElement googleAramaKutusu= driver.findElement(By.xpath("//*[@class='gLFyf']"));
        googleAramaKutusu.sendKeys("Euro to Dollar"+ Keys.ENTER);
        // Para birimlerinin karsilastirmasini alir
        WebElement paraninDegeri=driver.findElement(By.xpath("//span[@class='DFlfde SwHCTb']"));
       String dollar=paraninDegeri.getText();
        // Karsilastirilacak olan para biriminin 1.5 birimden kucuk oldugu test edilir
        Assert.assertTrue(Double.parseDouble(dollar)<1.5);

    }


    @After
    public void tearDown(){
        driver.close();
    }
}

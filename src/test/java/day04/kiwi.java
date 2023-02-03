package day04;

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
import org.openqa.selenium.support.ui.Select;

import java.security.Key;
import java.time.Duration;

public class kiwi {
    WebDriver driver;
    // https://www.kiwi.com sayfasina gidin
    // Cookies i reddedin
    // Sayfa basliginin "kiwi" icerdigini test edin
    // Sag ust kisimdan dil ve para birimi secenegini Turkiye ve TL olarak secin
    // Sectiginiz dil ve para birimini dogrulayin
    // Ucus secenegi olarak tek yon secelim
    // Kalkis ve varis boxlarini temizleyerek kalkis ve varis ulkesini kendimiz belirleyelim
    // Gidis tarihi kismina erisim saglayarak gidecegimiz gunu secelim ve booking i iptal edelim
    // Sadece aktarmasiz ucuslar olarak filtreleme yapalim ve en ucuz secenegine tiklayalim
    // Filtreleme yaptigimiz en ucuz ucusun fiyatini getirerek 5000 tl den kucuk oldgunu dogurlayalim

    @Before
    public void setUp(){
        // ilgili kurulumlari tamamlayalim
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void kiwi() throws InterruptedException {
        // https://www.kiwi.com sayfasina gidin
        driver.get("https://www.kiwi.com");
        // Cookies i reddedin
        driver.findElement(By.xpath("//*[text()='Reject all']")).click();
        // Sayfa basliginin "kiwi" icerdigini test edin
        Assert.assertTrue(driver.getTitle().contains("iwi"));
        // Sag ust kisimdan dil ve para birimi secenegini Turkiye ve TL olarak secin
        driver.findElement(By.xpath("(//div[@class='ButtonPrimitiveContent__StyledButtonPrimitiveContent-sc-1nfggrh-0 jIgbVC'])[7]"))
                .click();

        WebElement dropDownLanguage= driver.findElement(By.xpath("//select[@data-test='LanguageSelect']"));
        Select select=new Select(dropDownLanguage);
        select.selectByValue("tr");

        WebElement dropDownCurrency=driver.findElement(By.xpath("//select[@data-test='CurrencySelect']"));
        Select select1=new Select(dropDownCurrency);
        select1.selectByVisibleText("Turkish lira - TRY");

        driver.findElement(By.xpath("(//div[@class='ButtonPrimitiveContent__StyledButtonPrimitiveContent-sc-1nfggrh-0 iFkUjC'])[21]")).click();
        // Sectiginiz dil ve para birimini dogrulayin
        Assert.assertTrue(driver.getCurrentUrl().contains("tr"));
        // Ucus secenegi olarak tek yon secelim
        driver.findElement(By.xpath("(//*[text()='Gidiş Dönüş'])[1]")).click();
        driver.findElement(By.xpath("(//*[text()='Tek Yön'])[1]")).click();
        // Kalkis ve varis boxlarini temizleyerek kalkis ve varis ulkesini kendimiz belirleyelim
        driver.findElement(By.xpath("//*[@data-test='PlacePickerInputPlace-close']")).click();

        WebElement kalkisNoktasi=driver.findElement(By.xpath("(//*[@data-test='SearchField-input'])[1]"));
        Thread.sleep(2000);
        kalkisNoktasi.sendKeys("Istanbul");
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//div[@data-test='PlacePickerRow-wrapper'])[1]")).click();

      WebElement varisNoktasi=driver.findElement(By.xpath("(//*[@data-test='SearchField-input'])[2]"));
      varisNoktasi.sendKeys("Varsova");
      Thread.sleep(1500);
        driver.findElement(By.xpath("(//div[@data-test='PlacePickerRow-wrapper'])[1]")).click();
        // Gidis tarihi kismina erisim saglayarak gidecegimiz gunu secelim ve booking i iptal edelim
        driver.findElement(By.xpath("//input[@data-test=\"SearchFieldDateInput\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@data-value='2023-03-23']")).click();
        driver.findElement(By.xpath("//*[text()='Tarihleri ayarla']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@class='Icon__StyledIcon-sc-psgqgs-0 jjVoBX']")).click();
        driver.findElement(By.xpath("//*[text()='Ara']")).click();
        Thread.sleep(1500);

        // Sadece aktarmasiz ucuslar olarak filtreleme yapalim ve en ucuz secenegine tiklayalim
        driver.findElement(By.xpath("(//div[@class='Radio__StyledIconContainer-sc-1e6hy4x-1 cQMXav'])[1]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//div[@data-test='SortBy-price']")).click();
        Thread.sleep(2000);
        // Filtreleme yaptigimiz en ucuz ucusun fiyatini getirerek 5000 tl den kucuk oldgunu dogurlayalim
        WebElement fiyatText=driver.findElement(By.xpath("(//strong[@data-test='ResultCardPrice'])[1]"));
        System.out.println(fiyatText.getText());
       String fiyat=fiyatText.getText();
       fiyat=fiyat.replaceAll(" TL","").replaceAll("\\.","");
        System.out.println(fiyat);
        Assert.assertTrue(Integer.parseInt(fiyat)<5000);

    }


    @After
    public void tearDown(){
         driver.close();
    }
}

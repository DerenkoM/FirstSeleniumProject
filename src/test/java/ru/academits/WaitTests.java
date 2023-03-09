package ru.academits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WaitTests {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {

        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
    }

    @Test
    public void s7WithoutWaitTest(){
        driver.get("https://news.s7.ru/news?id=13441");
        driver.manage().window().maximize();

        WebElement nameInput = driver.findElement(By.cssSelector("#author"));
        nameInput.sendKeys("name");

        Assertions.assertEquals("name", nameInput.getAttribute("value"));
    }

    @Test
    public void s7ImplicitWaitTest() throws InterruptedException {
        driver.get("https://news.s7.ru/news?id=13441");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement nameInput = driver.findElement(By.cssSelector("#author"));
        nameInput.sendKeys("name");

        Thread.sleep(3000);
        Assertions.assertEquals("name", nameInput.getAttribute("value"));
    }

    @Test
    public void s7ExplicitWaitTest() throws InterruptedException {
        driver.get("https://news.s7.ru/news?id=13441");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, 30,500);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".comments-block-wrapper")));

        WebElement nameInput = driver.findElement(By.cssSelector("#author"));
        nameInput.sendKeys("name");

        Thread.sleep(3000);
        Assertions.assertEquals("name", nameInput.getAttribute("value"));
    }

    public static class OpenBrowsersTest {

        @Test
        public void OpenBrowsers(){

            WebDriver driver = null;
            String browser = System.getProperty("browser");

            if(browser.equals("chrome")){
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            else if (browser.equals("edge")){
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            else{
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }


            driver.get("http://ya.ru");
            driver.manage().window().maximize();

        }

        @Test
        public void yandexSearchTest(){
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();

            driver.get("http://ya.ru");
            driver.findElement(By.xpath("//*[@id = 'text']")).sendKeys("Selenium");
            driver.findElement(By.xpath("//*[@type= 'submit']")).click();

            String firstText = driver.findElement(By.xpath("//*[@id= 'search-result']//li[1]/div")).getText();
            Assertions.assertTrue(firstText.contains("Selenium"));

        }
    }
}

package ru.academits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class OpenBrowsersTest {

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

        driver.quit();

    }

    @Test
    public void yandexSearchTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://ya.ru");
        driver.findElement(By.xpath("//*[@id = 'text']")).sendKeys("Selenium");
        driver.findElement(By.xpath("//*[@type= 'submit']")).click();

        String firstText = driver.findElement(By.xpath("//*[@id= 'search-result']//li[1]/div")).getText();
        Assertions.assertTrue(firstText.contains("Selenium"));
        Thread.sleep(3000);
        driver.quit();

    }

}

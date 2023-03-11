package ru.academits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class SeleniumTests {

@Test
    public void simpleTest(){
        WebDriverManager.chromedriver().setup(); //установка хром драйвера
        WebDriver driver = new ChromeDriver();
        driver.get("http://ya.ru");
        driver.quit();
    }
    @Test
    public void simpleTest2(){
        WebDriverManager.edgedriver().setup(); //установка эдж драйвера
        WebDriver driver = new EdgeDriver();
        driver.get("http://ya.ru");
        driver.quit();
    }





}

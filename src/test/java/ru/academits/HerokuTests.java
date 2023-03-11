package ru.academits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.List;

public class HerokuTests {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        /*WebDriver driver = null;
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
        }*/

        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();


        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void checkUrlTest() {
        Assertions.assertEquals("https://the-internet.herokuapp.com/", driver.getCurrentUrl(), "не пройден checkUrlTest");
    }

    @Test
    public void checkTitleTest() {
        Assertions.assertEquals("The Internet", driver.getTitle(), "The Internet");
    }

    @Test
    public void checkDropdownTitle() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement titleElement = driver.findElement(By.xpath("//*[@class='example']//h3"));
        //Thread.sleep(3000);
        String titleText = titleElement.getText();
        Assertions.assertEquals("Dropdown List", titleText);

    }

    @Test
    public void checkboxesListTest() throws InterruptedException {
       //driver.get("https://the-internet.herokuapp.com/checkboxes");
        driver.findElement(By.xpath("//*[text() = 'Checkboxes']")).click();
        WebElement checkboxesForm = driver.findElement(By.cssSelector("#checkboxes"));
        List<WebElement> list = checkboxesForm.findElements(By.cssSelector("input"));
        Assertions.assertEquals(2,list.size());

        Thread.sleep(3000);
        for (WebElement i:list){
            System.out.println("Type = " + i.getAttribute("type"));
            Assertions.assertEquals("checkbox", i.getAttribute("type"));
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

package ru.academits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.bouncycastle.util.test.FixedSecureRandom;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.text.SimpleDateFormat;
import java.util.*;

public class DemoqaTests {
    private static WebDriver driver = null;
    @BeforeAll
    public static void initAll() {
        String browser = System.getProperty("browser");
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().window().maximize();
    }

    @Test
    public void checkUrlTest() {
        Assertions.assertEquals("https://demoqa.com/automation-practice-form", driver.getCurrentUrl(), "не пройден checkUrlTest");
    }

    @Test
    public void checkTitleTest() throws InterruptedException {
        Thread.sleep(1000);
        Assertions.assertEquals("DEMOQA", driver.getTitle(), "Title error");
    }

    @Test
    public void checkFormNameTest() throws InterruptedException {
        WebElement formElement = driver.findElement(By.xpath("//*[@class = 'practice-form-wrapper']/h5"));
        String formText = formElement.getText();
        Assertions.assertEquals("Student Registration Form", formText, "FormName error");
        Thread.sleep(1000);
    }

    @Test
    public void iconVisibleTest() throws InterruptedException {
        WebElement iconElement = driver.findElement(By.xpath("//header//img"));
        Assertions.assertTrue(iconElement.isDisplayed());
        Thread.sleep(1000);
    }

    @Test
    public void checkboxesListTest() throws InterruptedException {

        WebElement checkboxesElement = driver.findElement(By.xpath("//input[@type = 'checkbox']"));
        List<WebElement> list = checkboxesElement.findElements(By.xpath("//input[@type = 'checkbox']"));
        Assertions.assertEquals(3,list.size());
        Thread.sleep(1000);
        for (WebElement i:list){
            System.out.println("Id = " + i.getAttribute("id"));
        }
    }
//*[@id= 'city']
    @Test
    public void stateAndCityTest() throws InterruptedException {
        WebElement stateElement = driver.findElement(By.xpath("//*[@id= 'state']"));
        WebElement cityElement = driver.findElement(By.xpath("//*[@id= 'city']"));
        Assertions.assertTrue(stateElement.isDisplayed());
        Assertions.assertTrue(cityElement.isEnabled());
        WebElement stateField = driver.findElement(By.xpath("//*[@id = 'react-select-3-input'] "));
        stateField.sendKeys("Rajasthan");
        stateField.sendKeys(Keys.RETURN); // нажатие Enter
        Assertions.assertTrue(cityElement.isDisplayed());
        Thread.sleep(1000);
        driver.navigate().refresh(); // обновление страницы

    }


    @Test
    public void placeholdersTest() {
        WebElement placeholderFirstNameElement = driver.findElement(By.xpath("//*[@id= 'firstName']"));
        Assertions.assertEquals("First Name", placeholderFirstNameElement.getAttribute("placeholder"));
        WebElement placeholderLastNameElement = driver.findElement(By.xpath("//*[@id= 'lastName']"));
        Assertions.assertEquals("Last Name", placeholderLastNameElement.getAttribute("placeholder"));
        WebElement placeholderEmailElement = driver.findElement(By.xpath("//*[@id= 'userEmail']"));
        Assertions.assertEquals("name@example.com", placeholderEmailElement.getAttribute("placeholder"));
        WebElement placeholderNumberElement = driver.findElement(By.xpath("//*[@id= 'userNumber']"));
        Assertions.assertEquals("Mobile Number", placeholderNumberElement.getAttribute("placeholder"));
        WebElement placeholderAddressElement = driver.findElement(By.xpath("//*[@id= 'currentAddress']"));
        Assertions.assertEquals("Current Address", placeholderAddressElement.getAttribute("placeholder"));

    }
    @Test
    public void checkDateTest() {
        WebElement dataElement = driver.findElement(By.xpath("//*[@id = 'dateOfBirthInput']"));
        String dataText = dataElement.getAttribute("value");
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        Date date = new Date();
        System.out.println("формат " + formatter.format(date)); // выводила в консоль, чтоб увидеть, что пишется одинаково
        System.out.println("текст " + dataText); // выводила в консоль, чтоб увидеть, что пишется одинаково
        Assertions.assertEquals(formatter.format(date), dataText);
    }

    @Test
    public void checkLoadFileTest() throws InterruptedException {
        WebElement loadFileElement = driver.findElement(By.xpath("//*[@class = 'form-file-label']"));
       // String loadFileText = loadFileElement.getText();
      //  System.out.println("формат " + loadFileText); // выводила в консоль, чтоб увидеть, что пишется одинаково
        // loadFileElement.click();
        loadFileElement.sendKeys("/travel.jpeg");
        loadFileElement.click();
        if(driver.getPageSource().contains("travel.jpeg")) {
            System.out.println("file uploaded");
        }
        else{
            System.out.println("file not uploaded");
        }

    }


    @Test
    public void completeTheFormTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id = 'firstName']")).sendKeys("Name");
        driver.findElement(By.xpath("//*[@id = 'lastName']")).sendKeys("Last Name");
        driver.findElement(By.xpath("//*[@id = 'userNumber']")).sendKeys("9131234567");
        //Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@id='genterWrapper']/div[2]/div[2]/label")).click();
        //Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id = 'submit']")).click();
        Thread.sleep(3000);
        String nameCompleteText = driver.findElement(By.xpath("//*[@class='table table-dark table-striped table-bordered table-hover']/tbody/tr[1]/td[2]")).getText();
        Assertions.assertTrue(nameCompleteText.contains("Name"));
        String lastNameCompleteText = driver.findElement(By.xpath("//*[@class='table table-dark table-striped table-bordered table-hover']/tbody/tr[1]/td[2]")).getText();
        Assertions.assertTrue(lastNameCompleteText.contains("Last Name"));
        String phoneCompleteText = driver.findElement(By.xpath("//*[@class='table table-dark table-striped table-bordered table-hover']/tbody/tr[4]/td[2]")).getText();
        //Assertions.assertTrue(phoneCompleteText.contains("9131234567"));
        Assertions.assertEquals("9131234567", phoneCompleteText);

    }











    @AfterAll
    public static void endEach() {
        if(driver!=null){
            driver.quit();
        }
    }

}

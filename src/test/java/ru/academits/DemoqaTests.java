package ru.academits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.concurrent.TimeUnit;

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
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //ожидание загрузки страницы
        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().window().maximize();
    }

    private String firstNameAdd = "Masha";
    private String lastNameAdd = "Lomova";
    private String userEmailAdd = "lomova@inbox.ru";
    private String genterAdd = "Female";
    private String userNumberAdd = "9135634567";
    private String dataMonthAdd = "October";
    private String dataYearAdd = "2011";
    private String dataDayAdd = "12"; // 01,02....09, 10, 11 и тд
    private String subjectsAdd1 = "Ma";
    private String subjectsAdd2 = "Co";
    private String hobbyValueAdd1 = "Sports";
    private String hobbyValueAdd2 = "Music";
    private String filePuth = "sssss.jpeg";
    private String adressValueAdd = "129556, Novosibirsk city, Main Street, 16";
    private String statesAdd = "Haryana";
    private String citiesAdd = "Panipat";

    @Test
    public void completeTheFormTest() throws InterruptedException {

        //проверяемые элементы с первой формы
        WebElement firstNameElement = driver.findElement(By.xpath("//*[@id = 'firstName']"));
        WebElement lastNameElement = driver.findElement(By.xpath("//*[@id = 'lastName']"));
        WebElement userEmailElement = driver.findElement(By.xpath("//*[@id = 'userEmail']"));
        WebElement genterWrapperElement = driver.findElement(By.xpath("//label[contains(text(), " + "\'" + genterAdd + "\'" + ")]"));
        WebElement userNumberElement = driver.findElement(By.xpath("//*[@id = 'userNumber']"));
        WebElement dateOfBirthInputElement = driver.findElement(By.xpath("//*[@id = 'dateOfBirthInput']"));
        WebElement subjectsInputElement = driver.findElement(By.xpath("//*[@id = 'subjectsInput']"));
        WebElement hobbyValue1Element = driver.findElement(By.xpath("//label[contains(text(), " + "\'" + hobbyValueAdd1 + "\'" + ")]"));
        WebElement hobbyValue2Element = driver.findElement(By.xpath("//label[contains(text(), " + "\'" + hobbyValueAdd2 + "\'" + ")]"));
        File loadFile = new File(filePuth);
        WebElement loadFileElement = driver.findElement(By.xpath("//input[@id='uploadPicture']"));
        WebElement currentAddressElement = driver.findElement(By.xpath("//*[@id = 'currentAddress']"));
        WebElement statesElement = driver.findElement(By.xpath("//*[@id= 'react-select-3-input']"));
        WebElement citiesElement = driver.findElement(By.xpath("//*[@id= 'react-select-4-input']"));

        //заполнение первой формы
        firstNameElement.sendKeys(firstNameAdd); //имя
        lastNameElement.sendKeys(lastNameAdd); //фамилия
        userEmailElement.sendKeys(userEmailAdd); //почта
        genterWrapperElement.click(); // пол.
        userNumberElement.sendKeys(userNumberAdd); //телефон
        dateOfBirthInputElement.click(); //дата рождения
        Thread.sleep(1000);
        WebElement elemMonth = driver.findElement(By.xpath("//select[@class='react-datepicker__month-select']"));
        Select dataMonthSelect = new Select(elemMonth);
        dataMonthSelect.selectByVisibleText(dataMonthAdd); // месяц
        WebElement elemYear = driver.findElement(By.xpath("//select[@class='react-datepicker__year-select']"));
        Select dataYearSelect = new Select(elemYear);
        dataYearSelect.selectByValue(dataYearAdd);// год
        WebElement elemDay = driver.findElement(By.xpath("//*[contains(@class, " + "\'day--0" + dataDayAdd + "\'" + ")]")); // число
        elemDay.click();
        Thread.sleep(1000);
        subjectsInputElement.sendKeys(subjectsAdd1); //предмет 1
        subjectsInputElement.sendKeys(Keys.RETURN); // нажатие Enter
        subjectsInputElement.sendKeys(subjectsAdd2); //предмет 2
        subjectsInputElement.sendKeys(Keys.RETURN); // нажатие Enter
        hobbyValue1Element.click(); // хобби.
        hobbyValue2Element.click(); // хобби.
        loadFileElement.sendKeys(loadFile.getAbsolutePath());  //файл
        Thread.sleep(3000);
        currentAddressElement.sendKeys(adressValueAdd); //адрес
        statesElement.sendKeys(statesAdd); //штат
        statesElement.sendKeys(Keys.RETURN);
        citiesElement.sendKeys(citiesAdd); //город
        citiesElement.sendKeys(Keys.RETURN);
        Thread.sleep(5000);

        // переход на вторую форму
        driver.findElement(By.xpath("//*[@id = 'submit']")).click();
        //проверка заголовка, что мы на нужной форме
        WebElement title = driver.findElement(By.xpath("//*[@id='example-modal-sizes-title-lg']"));
        String titleText = title.getText();
        Assertions.assertEquals("Thanks for submitting the form", titleText, "Несоответствует название формы");

        //проверка данных на форме
        Thread.sleep(10000);
        // поле имя
        WebElement nameComplete = driver.findElement(By.xpath("//td[text()='Student Name']/../td[2]"));
        String nameCompleteText = nameComplete.getText();
        Assertions.assertTrue(nameCompleteText.contains(firstNameAdd), "invalid value of firstName");
        // поле фамилия
        WebElement lastNameComplete = driver.findElement(By.xpath("//td[text()='Student Name']/../td[2]"));
        String lastNameCompleteText = lastNameComplete.getText();
        Assertions.assertTrue(lastNameCompleteText.contains(lastNameAdd));
        // поле почта
        WebElement emailComplete = driver.findElement(By.xpath("//td[text()='Student Email']/../td[2]"));
        String emailCompleteText = emailComplete.getText();
        Assertions.assertEquals(userEmailAdd, emailCompleteText);
        // проверка пола
        WebElement genderComplete = driver.findElement(By.xpath("//td[text()='Gender']/../td[2]"));
        String genderCompleteText = genderComplete.getText();
        Assertions.assertEquals(genterAdd, genderCompleteText, "invalid value of gender");
        // поле телефон
        WebElement phoneComplete = driver.findElement(By.xpath("//td[text()='Mobile']/../td[2]"));
        String phoneCompleteText = phoneComplete.getText();
        Assertions.assertEquals(userNumberAdd, phoneCompleteText);
        // поле дата
        WebElement dataComplete = driver.findElement(By.xpath("//td[text()='Date of Birth']/../td[2]"));
        String dataCompleteText = dataComplete.getText();
        WebElement dataElement = driver.findElement(By.xpath("//*[@id = 'dateOfBirthInput']"));
        String dataText = dataElement.getAttribute("value");
        String[] words = dataText.split(" "); // разбила строку на отдельные слова
        for (int i = 0; i < words.length; i++) {
            Assertions.assertTrue(dataCompleteText.contains(words[i])); //проверка каждого отдельго слова в составе dataCompleteText второй формы
        }
        //проверка Subjects
        WebElement subjectsInputFirst = driver.findElement(By.xpath("//div[starts-with(text(), " + "\"" + subjectsAdd1 + "\"" + ")]")); //div появляется после введенного предмета
        String subjectsInputFirstText = subjectsInputFirst.getText();
        Assertions.assertTrue(subjectsInputFirstText.contains(subjectsAdd1));
        subjectsInputFirst = driver.findElement(By.xpath("//div[starts-with(text(), " + "\'" + subjectsAdd2 + "\' " + ")]")); //переопределяю значение чтоб не вводить вторую переменную
        subjectsInputFirstText = subjectsInputFirst.getText();
        Assertions.assertTrue(subjectsInputFirstText.contains(subjectsAdd2));
        // проверка хобби
        WebElement hobbyComplete = driver.findElement(By.xpath("//td[text()='Hobbies']/../td[2]"));
        String hobbyCompleteText = hobbyComplete.getText();
        Assertions.assertEquals(hobbyValueAdd1 + ", " + hobbyValueAdd2, hobbyCompleteText, "invalid value of file"); //проверка по точному совпадению
        Assertions.assertTrue(hobbyCompleteText.contains(hobbyValueAdd1)); // проверка по частичному совпадению
        Assertions.assertTrue(hobbyCompleteText.contains(hobbyValueAdd2)); // проверка по частичному совпадению
        // проверка файла
        WebElement fileComplete = driver.findElement(By.xpath("//td[text()='Picture']/../td[2]"));
        String fileCompleteText = fileComplete.getText();
        Assertions.assertEquals(filePuth, fileCompleteText, "invalid value of file");
        // поле адрес
        WebElement adressElement = driver.findElement(By.xpath("//*[@id = 'currentAddress']"));
        String adressText = adressElement.getAttribute("value");
        Assertions.assertEquals(adressValueAdd, adressText);
        // проверка штата и города
        WebElement stateAndCityComplete = driver.findElement(By.xpath("//td[text()='State and City']/../td[2]"));
        String stateAndCityCompleteText = stateAndCityComplete.getText();
        Assertions.assertTrue(stateAndCityCompleteText.contains(statesAdd)); // проверка по частичному совпадению
        Assertions.assertTrue(stateAndCityCompleteText.contains(citiesAdd)); // проверка по частичному совпадению

        WebElement closeForm = driver.findElement(By.xpath("//button[@id = 'closeLargeModal']"));
        closeForm.click();
    }

    @AfterAll
    public static void endEach() {
        if (driver != null) {
            driver.quit();
        }
    }

}

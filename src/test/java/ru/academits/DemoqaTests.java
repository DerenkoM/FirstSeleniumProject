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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //еще вставлю ожидания
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
    public void checkNameOfRegistrationFormTest() throws InterruptedException {
        WebElement nameOfRegistrationFormElement = driver.findElement(By.xpath("//*[@class = 'practice-form-wrapper']/h5"));
        String nameOfRegistrationFormText = nameOfRegistrationFormElement.getText();
        Assertions.assertEquals("Student Registration Form", nameOfRegistrationFormText, "FormName error");
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
        Assertions.assertEquals(3, list.size());
        Thread.sleep(1000);
        for (WebElement i : list) {
            System.out.println("Id = " + i.getAttribute("id"));
        }
    }

    @Test
    public void checkboxesTextTest() throws InterruptedException {
        WebElement checkboxesElement1 = driver.findElement(By.xpath("//*[@id= 'hobbies-checkbox-1']"));
        String checkboxesText1 = checkboxesElement1.getAttribute("value");
        Assertions.assertEquals("1", checkboxesText1);
        WebElement checkboxesElement2 = driver.findElement(By.xpath("//*[@id= 'hobbies-checkbox-2']"));
        String checkboxesText2 = checkboxesElement2.getAttribute("value");
        Assertions.assertEquals("2", checkboxesText2);
        WebElement checkboxesElement3 = driver.findElement(By.xpath("//*[@id= 'hobbies-checkbox-3']"));
        String checkboxesText3 = checkboxesElement3.getAttribute("value");
        Assertions.assertEquals("3", checkboxesText3);
    }

    @Test
    public void radioListTest() throws InterruptedException {

        WebElement radioElement = driver.findElement(By.xpath("//input[@type = 'radio']"));
        List<WebElement> radioList = radioElement.findElements(By.xpath("//input[@type = 'radio']"));
        Assertions.assertEquals(3, radioList.size());
        Thread.sleep(1000);
        for (WebElement i : radioList) {
            System.out.println("Id = " + i.getAttribute("id"));
        }

    }

    @Test
    public void radiobuttonTextTest() throws InterruptedException {
        WebElement radioElement1 = driver.findElement(By.xpath("//*[@id= 'gender-radio-1']"));
        String radioText1 = radioElement1.getAttribute("value");
        Assertions.assertEquals("Male", radioText1);
        WebElement radioElement2 = driver.findElement(By.xpath("//*[@id= 'gender-radio-2']"));
        String radioText2 = radioElement2.getAttribute("value");
        Assertions.assertEquals("Female", radioText2);
        WebElement radioElement3 = driver.findElement(By.xpath("//*[@id= 'gender-radio-3']"));
        String radioText3 = radioElement3.getAttribute("value");
        Assertions.assertEquals("Other", radioText3);
    }

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
    public void placeholdersTextTest() {
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
        WebElement placeholderStateElement = driver.findElement(By.xpath("//*[@id= 'state']"));
        String stateText = placeholderStateElement.getText();
        Assertions.assertEquals("Select State", stateText);
        WebElement placeholderCityElement = driver.findElement(By.xpath("//*[@id= 'city']"));
        String cityText = placeholderCityElement.getText();
        Assertions.assertEquals("Select City", cityText);
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
        WebElement loadFileElement = driver.findElement(By.xpath("//input[@type='file']"));
        loadFileElement.sendKeys("C:\\Users\\Alex\\IdeaProjects\\FirstSeleniumProject\\src\\test\\java\\ru\\academits\\sssss.jpeg"); // не получается по относительному пути
        Thread.sleep(3000);
        if (driver.getPageSource().contains("sssss")) { // переходит в else
            System.out.println("file uploaded");
        } else {
            System.out.println("file not uploaded");
        }
        String nameFile = driver.getPageSource(); // при выводе текста страницы на страницу нет фрагмента "sssss"
        System.out.println("page  " + nameFile);
    }

    @Test
    public void completeTheFormTest() throws InterruptedException {

        //заполнение формы
        driver.findElement(By.xpath("//*[@id = 'firstName']")).sendKeys("Mila"); //имя
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id = 'lastName']")).sendKeys("Derenko"); //фамилия
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id = 'userEmail']")).sendKeys("derenko-m@inbox.ru"); //почта
        driver.findElement(By.xpath("//div[@id='genterWrapper']/div[2]/div[2]/label")).click(); // пол. тут не смогла обратиться к input. в ошибке что вызывается label.
        // Кликнула на label и выбрался input. Так должно быть? Они связаны через айдишник.
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id = 'userNumber']")).sendKeys("9131234567"); //телефон
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id = 'dateOfBirthInput']")).click(); //дата рождения
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@aria-label= 'Previous Month']")).click(); //переход на прошлый месяц. подумаю еще как выбрать более позднюю дату
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@class= 'react-datepicker__month']/div[4]/div[5]")).click(); //выбор числа 23 в месяце
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id = 'subjectsInput']")).sendKeys("ma"); //предметы, пока не получается вставить из выпадающего списка
        driver.findElement(By.xpath("//*[@id = 'subjectsInput']")).sendKeys(Keys.RETURN); // нажатие Enter
        Thread.sleep(1000);
        List<WebElement> listItems = driver.findElements(By.xpath("//*[@id='subjectsContainer']"));
        System.out.println("size " + listItems.size()); //тут пока не получается выбрать элемент из списка. буду еще думать
        listItems.get(0).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='hobbiesWrapper']/div[2]/div[2]/label")).click(); // хобби. тут не смогла обратиться к input напрямую
        driver.findElement(By.xpath("//div[@id='hobbiesWrapper']/div[2]/div[3]/label")).click(); // хобби. тут не смогла обратиться к input напрямую
        Thread.sleep(1000);
        WebElement loadFileElement = driver.findElement(By.xpath("//input[@type='file']"));
        loadFileElement.sendKeys("C:\\Users\\Alex\\IdeaProjects\\FirstSeleniumProject\\src\\test\\java\\ru\\academits\\sssss.jpeg"); // не получается по относительному пути
        driver.findElement(By.xpath("//*[@id = 'currentAddress']")).sendKeys("123456, Novosibirsk city, Main Street, 163");
        Thread.sleep(1000);

        /*driver.findElement(By.xpath("//*[@id = 'state']")).click();
        List <WebElement> stateItems = driver.findElements(By.xpath("//div[@id='state']/div/div"));
        System.out.println("size " + stateItems.size()); //тут пока не получается выбрать элемент из списка. буду еще думать
        stateItems.get(0).click();
        List <WebElement> cityItems = driver.findElements(By.xpath("//div[@id='city']/div/div/div"));
        System.out.println("size " + cityItems.size()); //тут пока не получается выбрать элемент из списка. буду еще думать
        cityItems.get(0).click();*/

        //проверка обязательных полей по условию
        //Assertions.assertNotEquals(0, driver.findElement(By.xpath("//input[@id = 'firstName']")).getText().length()); //имя
        //Assertions.assertNotEquals(0, driver.findElement(By.xpath("//*[@id = 'lastName']")).getText().length()); //фамилия
        //Assertions.assertNotEquals(0, driver.findElement(By.xpath("//*[@id = 'userNumber']")).getText().length()); //телефон
        //Assertions.assertTrue(driver.findElement(By.xpath("//div[@id='genterWrapper']/div[2]/div[2]/label")).isDisplayed()); //телефон

        driver.findElement(By.xpath("//*[@id = 'submit']")).click();
        WebElement title = driver.findElement(By.xpath("//*[@id='example-modal-sizes-title-lg']")); //проверка заголовка, что мы на нужной странице
        String titleText = title.getText();

        //проверка данных на форме
        Thread.sleep(5000);
        // поле имя
        String nameCompleteText = driver.findElement(By.xpath("//*[@class='table table-dark table-striped table-bordered table-hover']/tbody/tr[1]/td[2]")).getText();
        Assertions.assertTrue(nameCompleteText.contains("Mila"), "invalid value"); //равнозначные проверки далее применяла только эту
        // поле фамилия
        String lastNameCompleteText = driver.findElement(By.xpath("//*[@class='table table-dark table-striped table-bordered table-hover']/tbody/tr[1]/td[2]")).getText();
        Assertions.assertTrue(lastNameCompleteText.contains("Derenko"));
        // поле телефон
        String phoneCompleteText = driver.findElement(By.xpath("//*[@class='table table-dark table-striped table-bordered table-hover']/tbody/tr[4]/td[2]")).getText();
        Assertions.assertEquals(10, phoneCompleteText.length()); //проверка на количество цифр в телефоне
        Assertions.assertEquals("9131234567", phoneCompleteText);
        // поле почта
        String emailCompleteText = driver.findElement(By.xpath("//*[@class='table table-dark table-striped table-bordered table-hover']/tbody/tr[2]/td[2]")).getText();
        Assertions.assertTrue(emailCompleteText.contains("-")); //проверка обязательных символов
        Assertions.assertTrue(emailCompleteText.contains("@")); //проверка обязательных символов
        Assertions.assertTrue(emailCompleteText.contains(".")); //проверка обязательных символов
        Assertions.assertEquals("derenko-m@inbox.ru", emailCompleteText);
        // поле дата
        String dataCompleteText = driver.findElement(By.xpath("//*[@class='table table-dark table-striped table-bordered table-hover']/tbody/tr[5]/td[2]")).getText();
        WebElement dataElement = driver.findElement(By.xpath("//*[@id = 'dateOfBirthInput']"));
        String dataText = dataElement.getAttribute("value");
        String[] words = dataText.split(" "); // разбила строку на отдельные слова
        for (int i = 0; i < words.length; i++) {
            Assertions.assertTrue(dataCompleteText.contains(words[i])); //проверка каждого отдельго слова в составе dataCompleteText второй формы
        }
        // поле адрес
        String adressCompleteText = driver.findElement(By.xpath("//*[@class='table table-dark table-striped table-bordered table-hover']/tbody/tr[9]/td[2]")).getText();
        WebElement adressElement = driver.findElement(By.xpath("//*[@id = 'currentAddress']"));
        String adressText = adressElement.getAttribute("value");
        String[] adresswords = adressText.split(" "); // разбила строку на отдельные слова
        for (int i = 0; i < adresswords.length; i++) {
            Assertions.assertTrue(adressCompleteText.contains(adresswords[i])); //проверка каждого отдельго слова в составе adressCompleteText второй формы
        }
        // проверка файла
        String fileCompleteText = driver.findElement(By.xpath("//*[@class='table table-dark table-striped table-bordered table-hover']/tbody/tr[8]/td[2]")).getText();
        Assertions.assertEquals("sssss.jpeg", fileCompleteText, "invalid value of file");
        Assertions.assertTrue(fileCompleteText.contains(".jpeg")); //проверка форматов можно уточнить в ТЗ и дополнить
        // проверка пола
        String genderCompleteText = driver.findElement(By.xpath("//*[@class='table table-dark table-striped table-bordered table-hover']/tbody/tr[3]/td[2]")).getText();
        Assertions.assertEquals("Female", genderCompleteText, "invalid value of file");
        // проверка хобби
        String hobbyCompleteText = driver.findElement(By.xpath("//*[@class='table table-dark table-striped table-bordered table-hover']/tbody/tr[7]/td[2]")).getText();
        Assertions.assertEquals("Reading, Music", hobbyCompleteText, "invalid value of file"); //проверка по точному совпадению
        Assertions.assertTrue(hobbyCompleteText.contains("Reading")); // проверка по частичному совпадению
        Assertions.assertTrue(hobbyCompleteText.contains("Music")); // проверка по частичному совпадению


        driver.findElement(By.xpath("//button[@id = 'closeLargeModal']")).click();
    }

    @AfterAll
    public static void endEach() {
        if (driver != null) {
            driver.quit();
        }
    }

}

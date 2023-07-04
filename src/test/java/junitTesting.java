import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class junitTesting {
    WebDriver driver;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


    }

    @DisplayName("get the website title")
   // @Test
    public void getTitle() {

        String title_actual = driver.getTitle();
        String title_expected = "Practice webform for learners | Digital Unite";
        Assertions.assertTrue(title_actual.contains(title_expected));
        System.out.println(title_actual);


    }


    @DisplayName("Fill the webform with necessary information")
    @Test
    public void submitForm() {

        driver.get("https://www.digitalunite.com/practice-webform-learners");
        driver.findElement(By.id("onetrust-reject-all-handler")).click();


        //name
        WebElement inputName = driver.findElement(By.id("edit-name"));
        inputName.sendKeys("Afsana");
        String inputValue = inputName.getAttribute("value");
        Assertions.assertEquals("Afsana", inputValue);


        //number
        WebElement inputNumber = driver.findElement(By.id("edit-number"));
        inputNumber.sendKeys("01999965000");
        String inputNumberValue = inputNumber.getAttribute("value");
        Assertions.assertEquals("01999965000", inputNumberValue);

        //age
        Actions action = new Actions(driver);
        List<WebElement> inputAge = driver.findElements(By.className("ui-checkboxradio-label"));
        action.click(inputAge.get(1)).perform();


        //date
        driver.findElement(By.id("edit-date")).click();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();
        String currentDate = dateFormat.format(date);

        driver.findElement(By.id("edit-date")).sendKeys(currentDate, Keys.ENTER);



        // email
        WebElement inputEmail = driver.findElement(By.id("edit-email"));
        inputEmail.sendKeys("afsana@gmail.com");
        String inputEmailValue = inputEmail.getAttribute("value");
        Assertions.assertEquals("afsana@gmail.com", inputEmailValue);


        // About yourself
        WebElement inputYourSelf = driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-"));
        inputYourSelf.sendKeys("Hi!, My name is Afsana. I have completed my graduation in CSE.");
        String inputYourSelfValue = inputYourSelf.getAttribute("value");
        Assertions.assertEquals("Hi!, My name is Afsana. I have completed my graduation in CSE.", inputYourSelfValue);


        //upload file
//        WebElement uploadElement = driver.findElement(By.id("edit-uploadocument-upload"));
//        uploadElement.sendKeys("G:\\pic\\Smuct-promo.jpg");

        WebElement uploadElement = driver.findElement(By.id("edit-uploadocument-upload"));
        File file = new File(".\\src\\test\\resources\\Resume.pdf");
        uploadElement.sendKeys(file.getAbsolutePath());

        //CHECK

        driver.findElement(By.id("edit-age")).click();


        // submit

        driver.findElement(By.id("edit-submit")).click();




        driver.switchTo().alert().accept();
        String actualText = driver.findElement(By.className("page-title")).getText();
        String expectedText = "Thank you for your submission!";
        Assertions.assertEquals(actualText,expectedText);




    }

    @AfterAll
    public void closeDriver() {
        driver.quit();
    }
}

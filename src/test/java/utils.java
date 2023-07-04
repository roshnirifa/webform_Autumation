import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class utils {
    public static void doScroll( WebDriver driver){
        JavascriptExecutor js =(JavascriptExecutor) driver;
        js.executeScript("window.scrollBY(0, document.body.scrollHeight)");

    }
}

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearcherParallel1 {

    private WebDriver driver;
    public static final String SEARCH_FIELD = "/html/body/div[1]/div[3]/form/div[2]/div[1]/div[1]/div/div[2]/input";
    public static final String SEARCH_ITEM = "iPhone 12";
    public static final String SEARCH_BUTTON = "/html/body/div[1]/div[3]/form/div[2]/div[1]/div[3]/center/input[1]";

    @BeforeTest
    public void preCondition() {
        // set up sources for search activities
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        String urlHomePage = "https://www.google.com/";
        driver.get(urlHomePage);
    }

    @Test
    public void searchItem() {
        // put item name into search field and click "Search in Google"
        driver.findElement(By.xpath(SEARCH_FIELD)).sendKeys(SEARCH_ITEM);
        driver.findElement(By.xpath(SEARCH_BUTTON)).click();
    }

    @AfterTest
    public void afterTest() throws InterruptedException {
        // wait 5 seconds after search test is done and terminate browser
        Thread.sleep(5000);
        driver.close();
    }
}

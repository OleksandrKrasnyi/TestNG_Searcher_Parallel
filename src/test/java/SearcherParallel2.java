import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearcherParallel2 {

    private WebDriver driver;
    public static final String SEARCH_FIELD = "/html/body/div[1]/div[3]/form/div[2]/div[1]/div[1]/div/div[2]/input";
    public static final String SEARCH_ITEM = "iPhone 12 Pro";
    public static final String SEARCH_BUTTON = "/html/body/div[1]/div[3]/form/div[2]/div[1]/div[3]/center/input[1]";

    @BeforeTest
    public void preCondition() {
        // set up source for search activities
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        String urlHomePage = "https://www.google.com/";
        driver.get(urlHomePage);
    }

    @Test
    public void searchItem() {
        // go to google.com / take
        driver.findElement(By.xpath(SEARCH_FIELD)).sendKeys(SEARCH_ITEM);
        driver.findElement(By.xpath(SEARCH_BUTTON)).click();
    }

    @AfterTest
    public void afterTest() throws InterruptedException {
        Thread.sleep(5000);
        driver.close();
    }
}



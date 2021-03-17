import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class SearcherDataDriven {

    private WebDriver driver;
    public static final String SEARCH_FIELD = "/html/body/div[1]/div[3]/form/div[2]/div[1]/div[1]/div/div[2]/input";
    public static final String SEARCH_BUTTON = "/html/body/div[1]/div[3]/form/div[2]/div[1]/div[3]/center/input[1]";

    @BeforeMethod
    public void setUp() {
        // set up browser basic stage and site for searching
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        String urlHomePage = "https://www.google.com/";
        driver.get(urlHomePage);
    }

    @DataProvider(name = "testData")
    public Object[][] getData() {
        // create array of data to input
        Object[][] data = new Object[3][1];

        data[0][0] = "iPhone 12";
        data[1][0] = "iPhone 12 Pro";
        data[2][0] = "iPhone 12 Pro Max";

        return data;
    }

    @Test(dataProvider = "testData")
    public void searchItem(String SEARCH_ITEM) {
        // put item name into search field and click "Search in Google"
        driver.findElement(By.xpath(SEARCH_FIELD)).clear();
        driver.findElement(By.xpath(SEARCH_FIELD)).sendKeys(SEARCH_ITEM);
        driver.findElement(By.xpath(SEARCH_BUTTON)).click();

        String title = driver.getTitle();
        Assert.assertEquals(title, SEARCH_ITEM + " - Поиск в Google", "Title is not matched");
    }

    @AfterMethod
    public void afterTest() {
        // terminate browser after search test is done
        driver.quit();
    }
}

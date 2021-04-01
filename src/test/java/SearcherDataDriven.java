import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class SearcherDataDriven {

    private WebDriver driver;
    public static final String SEARCH_FIELD = "//input[@name='q']";

    @BeforeMethod
    public void setUp() {
        // set up browser basic stage and site for searching
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();

        //Implicit wait:
        //1. always applied globally -- is available for all web-elements
        //2. Dynamic in nature
        //3. Can be changed anywhere and anytime in code
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
        driver.findElement(By.xpath(SEARCH_FIELD)).sendKeys(SEARCH_ITEM + Keys.ENTER);

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        WebElement firstLink = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return driver.findElement(By.xpath("//*[@id='tads']/descendant::a[1]/child::div[1]"));
            }
        });

        String title = driver.getTitle();
        Assert.assertEquals(title, SEARCH_ITEM + " - Поиск в Google", "Title is not matched");
    }

    @AfterMethod
    public void afterTest() {
        // terminate browser after search test is done
        driver.quit();
    }

//    //Explicit Wait:
//    //1. No explicit keyword or method
//    //2. Dynamic in nature
//    //3. Specific to element
//    //4. Available with WebDriverWait with some ExpectedConditions
//    public static void sendKeys(WebDriver driver, WebElement element, int timeout, String value) {
//
//        new WebDriverWait(driver, Duration.ofSeconds(timeout))
//                .until(ExpectedConditions.visibilityOf(element));
//        element.sendKeys(value);
//
//    }
//
//    public static void clickOn(WebDriver driver, WebElement element, int timeout) {
//        new WebDriverWait(driver, Duration.ofSeconds(timeout))
//                .until(ExpectedConditions.elementToBeClickable(element));
//        element.click();
//    }
//
//    //Implicit and Explicit Waits should never be used together
//    //-- Selenium WebDriver will wait fot the element first because of Impl.w and the Expl.w will be applied
//    //hence, total sync wait will be increased for each element
}

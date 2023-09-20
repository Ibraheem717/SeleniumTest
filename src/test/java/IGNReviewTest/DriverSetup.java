package IGNReviewTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class DriverSetup {

    Duration standardWait = Duration.ofSeconds(5);

    protected WebDriver driver;
    protected String alienHost = "https://www.ign.com/games/alien-isolation";

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    protected void setUp(@Optional("chrome") String browser) {
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", "src/test/resources/msedgedriver.exe");
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("Default browser chrome");
                System.setProperty("webdriver.edge.driver", "src/test/resources/msedgedriver.exe");
                driver = new ChromeDriver();
                break;
        }
        driver.get(alienHost);
    }
    @AfterMethod(alwaysRun = true)
    protected void tearDown() {
        driver.quit();
    }

    protected void RejectCookiesNotTest() {
        new WebDriverWait( driver, standardWait ).until(
                ExpectedConditions.presenceOfElementLocated(By.id("onetrust-reject-all-handler"))).click();
    }
}

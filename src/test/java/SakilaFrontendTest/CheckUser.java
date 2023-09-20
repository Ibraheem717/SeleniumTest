package SakilaFrontendTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class CheckUser {
//    private String path = "/src/main/java/resources/msedgedriver.exe";
    private WebDriver driver;
    private String reactHost = "http://localhost:3000/";

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    private void setUp(@Optional("chrome") String browser) {
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
    }
    @AfterMethod(alwaysRun = true)
    private void tearDown() {
        driver.quit();
    }
    private void sleep(long t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
    @Test
    public void PositiveCheckTest() {
        driver.get(reactHost);
        WebElement actorMenuElement = driver.findElement(By.id("ActorMenuSelect"));
        actorMenuElement.click();
    }

    @Test
    public void SearchUserIndividual() {
        this.PositiveCheckTest();
        WebElement actorSearchIndivisual = driver.findElement(By.id("ActorGetIndivisual"));
        actorSearchIndivisual.click();
    }
    @Test(priority = 1, groups = {"negativeTest", "positiveTest"})
    @Parameters({"username", "password", "expected"})
    private void ActorSearchIndividualInput(String forename, String surname, String expected) {
        this.SearchUserIndividual();
        WebElement actorFirstNameInput = driver.findElement(By.id("inputfirstName"));
        WebElement actorLastNameInput = driver.findElement(By.id("inputlastName"));
        WebElement actorSearchIndividualSubmit = driver.findElement(By.id("submit"));
        actorFirstNameInput.sendKeys(forename);
        actorLastNameInput.sendKeys(surname);
        actorSearchIndividualSubmit.click();
        sleep(300);
        Assert.assertEquals(driver.findElement(By.id("SearchIndivisualOutcome")).getText(), (expected));
    }

    @Test
    public void SearchAllActors () {
        this.PositiveCheckTest();
        driver.findElement(By.id("ActorSearchAll")).click();
        driver.findElement(By.id("SearchAllButton")).click();
        sleep(300);
        Assert.assertNotEquals(driver.findElement(By.id("AllActorResponses")).getText(), "No responses to display.");
    }
}

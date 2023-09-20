package IGNReviewTest;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class Home extends DriverSetup {
    @Test(groups = {"rejectCookies", "acceptCookies"})
    @Parameters({"css"})
    private void RejectCookies() {
        new WebDriverWait( driver, standardWait ).until(
                ExpectedConditions.presenceOfElementLocated(By.id("onetrust-reject-all-handler"))).click();
    }

    @Test
    private void AcceptCookies() {
        new WebDriverWait( driver, standardWait ).until(
                ExpectedConditions.presenceOfElementLocated(By.id("onetrust-accept-btn-handler"))).click();
    }

    @Test
    private void TestTabs() {
        this.RejectCookiesNotTest();
        WebElement overview = driver.findElement(By.id("overview"));
        WebElement playlist = driver.findElement(By.id("playlists"));
        WebElement review = driver.findElement(By.id("reviews"));
        overview.click();
        Assert.assertTrue(overview.isEnabled());
        playlist.click();
        Assert.assertTrue(playlist.isEnabled());
        review.click();
        Assert.assertTrue(review.isEnabled());
    }

    @Test
    private void OpenGuide() {
        this.RejectCookiesNotTest();
        this.driver.findElement(By.cssSelector(".button--secondary-alt")).click();
        new WebDriverWait(driver, standardWait).until(ExpectedConditions.urlMatches("^(?!.*\\bgames\\b).*"));

        Assert.assertNotEquals(this.alienHost, driver.getCurrentUrl());
    }


}

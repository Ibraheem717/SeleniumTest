package IGNReviewTest;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Navigation  extends DriverSetup{

    @Test
    @Parameters({"search"})
    private void SearchNewGame(String search) {
        this.RejectCookiesNotTest();
        driver.findElement(By.cssSelector(".stack > .navigation-item:nth-child(2)")).click();
        new WebDriverWait( driver, standardWait ).until(ExpectedConditions.presenceOfElementLocated(By.id("term"))).sendKeys(search);
        new WebDriverWait( driver, standardWait ).until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector(".search-results > .stack > .stack:nth-child(1)"))).click();
        new WebDriverWait( driver, standardWait ).until(ExpectedConditions.urlMatches("^(?!.*alien-isolation).*"));
        Assert.assertNotEquals(this.alienHost, driver.getCurrentUrl());
    }

    @Test
    private void GoToHome() {
        this.RejectCookiesNotTest();
        driver.findElement(By.cssSelector(".navigation-item:nth-child(1)")).click();
        new WebDriverWait( driver, standardWait ).until(ExpectedConditions.urlMatches("^(?!.*alien-isolation).*"));
        Assert.assertNotEquals(this.alienHost, driver.getCurrentUrl());
    }

    @Test
    @Parameters({"tab"})
    private void SeeReviews(String tab) {
        this.RejectCookiesNotTest();
        String regexPattern = "(?i).*\\b(?:" + tab.replace(" ", "|") + ")\\b.*";
        driver.findElement(By.cssSelector(".navigation-item:nth-child(3)")).click();
        new WebDriverWait( driver, standardWait ).until(ExpectedConditions.presenceOfElementLocated(By.linkText(tab))).click();
        new WebDriverWait(driver, standardWait).until(ExpectedConditions.urlMatches(regexPattern));
        Assert.assertNotEquals(this.alienHost, driver.getCurrentUrl());
    }

}

package org.elena.finalproject.elements;

import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DashboardMenu {

    private static final By HOME_LOCATOR = By.xpath("//a[contains(text(),'Home')]");
    private static final By UPDATES_LOCATOR = By.xpath("//a[contains(text(),'Updates')]");

    public void goToDashboardPage() {
        WebElement homeElement = Browser.waitForElementToBeVisible(HOME_LOCATOR);
        Browser.moveToElement(homeElement).click().perform();
    }

    public void goToUpdatesPage() {
        WebElement updatesElement = Browser.waitForElementToBeVisible(UPDATES_LOCATOR);
        Browser.moveToElement(updatesElement).click().perform();
    }
}

package org.elena.finalproject.elements;

import io.qameta.allure.Step;
import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DashboardMenu {

    private static final By HOME_LOCATOR = By.xpath("//a[contains(text(),'Home')]");
    private static final By UPDATES_LOCATOR = By.xpath("//a[contains(text(),'Updates')]");

    @Step("Clicking 'Home' in the Dashboard menu. Opening 'Dashboard' page")
    public void goToDashboardPage() {
        WebElement homeElement = Browser.waitForElementToBeVisible(HOME_LOCATOR);
        Browser.moveToElement(homeElement).click().perform();
    }

    @Step("Clicking 'Updates' in the Dashboard menu. Opening 'WordPress Updates' page")
    public void goToUpdatesPage() {
        WebElement updatesElement = Browser.waitForElementToBeVisible(UPDATES_LOCATOR);
        Browser.moveToElement(updatesElement).click().perform();
    }
}

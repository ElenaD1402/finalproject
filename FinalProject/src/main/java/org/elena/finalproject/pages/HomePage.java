package org.elena.finalproject.pages;

import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    private static final By PAGE_LOCATOR = By.xpath("//nav[@aria-label='Secondary menu']");

    @Override
    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }
}

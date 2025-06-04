package org.elena.finalproject.pages;

import io.qameta.allure.Step;
import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    private static final By PAGE_LOCATOR = By.xpath("//nav[@aria-label='Secondary menu']");

    public HomePage() {
        super();
    }

    @Step("Checking whether 'Home' page is opened")
    @Override
    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }
}

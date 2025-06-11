package org.elena.finalproject.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.elena.finalproject.webDriver.Browser;
import org.elena.finalproject.webDriver.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProfilePage extends BasePage {

    private static final By PAGE_LOCATOR = By.id("profile-page");

    private Logger logger = Logger.getLogger(this.getClass());

    @Step("User opens 'Profile' page")
    public void openProfilePage() {
        Browser.getWebDriver().get(Configuration.getBaseUrl() + "/profile.php");
        logger.info("Opening 'Profile' page");
    }

    @Step("Checking whether 'Profile' page is opened")
    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }
}

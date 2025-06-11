package org.elena.finalproject.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.elena.finalproject.webDriver.Browser;
import org.elena.finalproject.webDriver.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private static final By PAGE_LOCATOR = By.id("loginform");
    private static final By USERNAME_FIELD_LOCATOR = By.id("user_login");
    private static final By PASSWORD_FIELD_LOCATOR = By.id("user_pass");
    private static final By LOGIN_BUTTON_LOCATOR = By.id("wp-submit");

    private Logger logger = Logger.getLogger(this.getClass());

    @Step("User opens 'Login' page")
    public void openLoginPage() {
        Browser.getWebDriver().get(Configuration.getBaseUrl());
        logger.info("Opening 'Login' page");
    }

    @Step("Checking whether 'Login' page is opened")
    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }

    @Step("User enters a username")
    private void enterUsername(String username) {
        WebElement usernameElement = Browser.waitForElementToBeVisible(USERNAME_FIELD_LOCATOR);
        usernameElement.clear();
        usernameElement.sendKeys(username);
        logger.info("Entering a username");
    }

    @Step("User enters a password")
    private void enterPassword(String password) {
        WebElement passwordElement = Browser.waitForElementToBeVisible(PASSWORD_FIELD_LOCATOR);
        passwordElement.clear();
        passwordElement.sendKeys(password);
        logger.info("Entering a password");
    }

    @Step("User clicks 'Log In' button")
    private void clickLogInButton() {
        WebElement logInElement = Browser.waitForElementToBeClickable(LOGIN_BUTTON_LOCATOR);
        logInElement.click();
        logger.info("Clicking 'Log In' button");
    }

    @Step("User logs in")
    public void logIn(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogInButton();
        logger.info("Logging in");
    }
}

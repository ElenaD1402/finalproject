package org.elena.finalproject.pages;

import org.elena.finalproject.credentials.UserEnum;
import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private static final By PAGE_LOCATOR = By.id("loginform");
    private static final By USERNAME_FIELD_LOCATOR = By.id("user_login");
    private static final By PASSWORD_FIELD_LOCATOR = By.id("user_pass");
    private static final By LOGIN_BUTTON_LOCATOR = By.id("wp-submit");

    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }

    public void logIn(UserEnum userEnum) {
        WebElement usernameElement = Browser.waitForElementToBeVisible(USERNAME_FIELD_LOCATOR);
        usernameElement.clear();
        usernameElement.sendKeys(userEnum.getUsername());
        WebElement passwordElement = Browser.waitForElementToBeVisible(PASSWORD_FIELD_LOCATOR);
        passwordElement.clear();
        passwordElement.sendKeys(userEnum.getPassword());
        WebElement logInElement = Browser.waitForElementToBeClickable(LOGIN_BUTTON_LOCATOR);
        logInElement.click();
    }
}

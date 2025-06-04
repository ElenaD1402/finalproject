package org.elena.finalproject.elements;

import io.qameta.allure.Step;
import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Header {

    private static final By LOGO_LOCATOR = By.xpath("//li[@id='wp-admin-bar-wp-logo'][@class='menupop']");
    private static final By LOGO_HOVER_LOCATOR = By.xpath("//li[@id='wp-admin-bar-wp-logo'][@class='menupop hover']");
    private static final By PROFILE_MENU_LOCATOR = By.xpath("//li[@id='wp-admin-bar-my-account'][@class='menupop with-avatar']");
    private static final By PROFILE_MENU_HOVER_LOCATOR = By.xpath("//li[@id='wp-admin-bar-my-account'][@class='menupop with-avatar hover']");
    private static final By EDIT_PROFILE_LOCATOR = By.xpath("//a[(contains(text(),'Edit Profile'))]");
    private static final By LOG_OUT_LOCATOR = By.xpath("//a[(contains(text(),'Log Out'))]");

    @Step("Clicking 'Logo' in the header. Opening 'Home' page")
    public void goToHomePage() {
        WebElement logoElement = Browser.waitForElementToBeVisible(LOGO_LOCATOR);
        Browser.moveToElement(logoElement).perform();
        Browser.waitExpanding(LOGO_HOVER_LOCATOR);
        WebElement logoHoverElement = Browser.waitForElementToBeClickable(LOGO_HOVER_LOCATOR);
        logoHoverElement.click();
    }

    @Step("Logging out")
    public void logOut() {
        WebElement profileMenuElement = Browser.waitForElementToBeVisible(PROFILE_MENU_LOCATOR);
        Browser.moveToElement(profileMenuElement).perform();
        Browser.waitExpanding(PROFILE_MENU_HOVER_LOCATOR);
        WebElement logOutElement = Browser.waitForElementToBeClickable(LOG_OUT_LOCATOR);
        Browser.moveToElement(logOutElement).click().perform();
    }
}

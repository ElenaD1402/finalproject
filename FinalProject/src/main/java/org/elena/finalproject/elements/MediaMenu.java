package org.elena.finalproject.elements;

import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MediaMenu {

    private static final By LIBRARY_LOCATOR = By.xpath("//li/a[contains(text(),'Library')]");
    private static final By ADD_NEW_MEDIA_FILE_LOCATOR = By.xpath("//li/a[contains(text(),'Add New Media File')]");

    public void goToMediaPage() {
        WebElement libraryElement = Browser.waitForElementToBeVisible(LIBRARY_LOCATOR);
        Browser.moveToElement(libraryElement).click().perform();
    }

    public void addNewMediaFile() {
        WebElement addNewMediaFileElement = Browser.waitForElementToBeVisible(ADD_NEW_MEDIA_FILE_LOCATOR);
        Browser.moveToElement(addNewMediaFileElement).click().perform();
    }
}

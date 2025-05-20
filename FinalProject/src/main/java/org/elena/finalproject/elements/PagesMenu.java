package org.elena.finalproject.elements;

import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PagesMenu {

    private static final By ALL_PAGES_LOCATOR = By.xpath("//a[contains(text(),'All Pages')]");
    private static final By ADD_NEW_PAGE_LOCATOR = By.xpath("//li/a[contains(text(),'Add New Page')]");

    public void goToPagesPage() {
        WebElement libraryElement = Browser.waitForElementToBeVisible(ALL_PAGES_LOCATOR);
        Browser.moveToElement(libraryElement).click().perform();
    }

    public void addNewPage() {
        WebElement addNewPageElement = Browser.waitForElementToBeVisible(ADD_NEW_PAGE_LOCATOR);
        Browser.moveToElement(addNewPageElement).click().perform();
    }
}

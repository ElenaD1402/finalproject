package org.elena.finalproject.pages;

import org.elena.finalproject.elements.Header;
import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DeletionPage {

    private static final By PAGE_LOCATOR = By.xpath("//h1[contains(text(),'commitment to innovation')]");

    private final Header header = new Header();

    public Header getHeader() {
        return header;
    }

    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }
}

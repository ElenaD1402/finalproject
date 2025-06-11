package org.elena.finalproject.elements;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PagesMenu {

    private static final By ALL_PAGES_LOCATOR = By.xpath("//a[contains(text(),'All Pages')]");
    private static final By ADD_NEW_PAGE_LOCATOR = By.xpath("//li/a[contains(text(),'Add New Page')]");

    private Logger logger = Logger.getLogger(this.getClass());

    @Step("User clicks 'All Pages' in the Pages menu")
    public void goToPagesPage() {
        WebElement allPagesElement = Browser.waitForElementToBeVisible(ALL_PAGES_LOCATOR);
        Browser.moveToElement(allPagesElement).click().perform();
        logger.info("Clicking 'All Pages' in the Pages menu. Opening 'Pages' page");
    }

    @Step("User clicks 'Add New Page' in the Pages menu")
    public void addNewPage() {
        WebElement addNewPageElement = Browser.waitForElementToBeVisible(ADD_NEW_PAGE_LOCATOR);
        Browser.moveToElement(addNewPageElement).click().perform();
        logger.info("Clicking 'Add New Page' in the Pages menu. Opening 'Add New Page' page");
    }
}

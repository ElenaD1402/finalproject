package org.elena.finalproject.pages.pages;

import org.elena.finalproject.models.Page;
import org.elena.finalproject.pages.BasePage;
import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PagesPage extends BasePage {

    private static final By PAGE_LOCATOR = By.xpath("//div[@class='wrap']/h1[contains(text(),'Pages')]");

    public PagesPage() {
        super();
    }

    @Override
    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }

    public boolean isDraftPageAdded(Page page) {
        WebElement addedDraftPageElement = Browser.waitForElementToBeVisible(By.xpath("//a[contains(text(),'" + page.getTitle()
                + "')]/following-sibling::span[contains(text(),'Draft')]/../.."));
        return addedDraftPageElement != null && addedDraftPageElement.isDisplayed();
    }

    public boolean isPageAdded(Page page) {
        WebElement addedPageElement = Browser.waitForElementToBeVisible(By.xpath("//a[contains(text(),'" + page.getTitle() + "')]"));
        return addedPageElement != null && addedPageElement.isDisplayed();
    }

    public void deletePage(Page page) {
        WebElement pageElement = Browser.waitForElementToBeVisible(By.xpath("//a[contains(text(),'" + page.getTitle() + "')]"));
        Browser.moveToElement(pageElement).perform();
        WebElement deletePageElement = Browser.waitForElementToBeClickable(By.xpath("//a[@aria-label='Move “" + page.getTitle() + "” to the Trash']"));
        Browser.click(deletePageElement);
    }

    public boolean isPageDeleted(Page page) {
        WebElement deletedPageElement = Browser.waitForElementToBeVisible(By.xpath("//a[contains(text(),'" + page.getTitle() + "')]"));
        return deletedPageElement == null;
    }
}

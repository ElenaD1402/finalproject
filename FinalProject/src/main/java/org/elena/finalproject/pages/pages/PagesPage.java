package org.elena.finalproject.pages.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.elena.finalproject.models.Page;
import org.elena.finalproject.pages.BasePage;
import org.elena.finalproject.webDriver.Browser;
import org.elena.finalproject.webDriver.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PagesPage extends BasePage {

    private static final By PAGE_LOCATOR = By.xpath("//div[@class='wrap']/h1[contains(text(),'Pages')]");
    private static final By SEARCH_PAGES_FIELD_LOCATOR = By.id("post-search-input");
    private static final By SEARCH_PAGES_BUTTON_LOCATOR = By.id("search-submit");
    private static final By NO_PAGES_FOUND_LOCATOR = By.xpath("//*[contains(text(),'No pages found.')]");

    private Logger logger = Logger.getLogger(this.getClass());

    public PagesPage() {
        super();
    }

    @Step("User opens 'Pages' page")
    public void openPagesPage() {
        Browser.getWebDriver().get(Configuration.getBaseUrl() + "/edit.php?post_type=page");
        logger.info("Opening 'Pages' page");
    }

    @Step("Checking whether 'Pages' page is opened")
    @Override
    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }

    @Step("User searches for the page")
    private void searchPage(String title) {
        WebElement searchPagesFieldElement = Browser.waitForElementToBeVisible(SEARCH_PAGES_FIELD_LOCATOR);
        searchPagesFieldElement.clear();
        searchPagesFieldElement.sendKeys(title);
        WebElement searchPagesButtonElement = Browser.waitForElementToBeClickable(SEARCH_PAGES_BUTTON_LOCATOR);
        searchPagesButtonElement.click();
        logger.info("Searching for the page");
    }

    @Step("Checking whether the 'Draft' page is added")
    public boolean isDraftPageAdded(Page page) {
        searchPage(page.getTitle());
        WebElement addedDraftPageElement = Browser.waitForElementToBePresent(By.xpath("//a[contains(text(),'" + page.getTitle()
                + "')]/following-sibling::span[contains(text(),'Draft')]/../../..//*[contains(text(),'Last Modified')]"));
        return addedDraftPageElement != null && addedDraftPageElement.isDisplayed();
    }

    @Step("Checking whether the page is added")
    public boolean isPageAdded(Page page) {
        searchPage(page.getTitle());
        WebElement addedPageElement = Browser.waitForElementToBePresent(By.xpath("//a[contains(text(),'" + page.getTitle()
                + "')]/../../..//*[contains(text(),'Published')]"));
        return addedPageElement != null && addedPageElement.isDisplayed();
    }

    @Step("User deletes a page")
    public void deletePage(Page page) {
        searchPage(page.getTitle());
        WebElement pageElement = Browser.waitForElementToBePresent(By.xpath("//a[contains(text(),'" + page.getTitle() + "')]"));
        Browser.moveToElement(pageElement).perform();
        WebElement deletePageElement = Browser.waitForElementToBeClickable(By.xpath("//a[@aria-label='Move “" + page.getTitle()
                + "” to the Trash']"));
        Browser.click(deletePageElement);
        logger.info("Deleting a page");
    }

    @Step("Checking whether the page is deleted")
    public boolean isPageDeleted(Page page) {
        searchPage(page.getTitle());
        WebElement noPagesFoundElement = Browser.waitForElementToBeVisible(NO_PAGES_FOUND_LOCATOR);
        return noPagesFoundElement != null && noPagesFoundElement.isDisplayed();
    }
}

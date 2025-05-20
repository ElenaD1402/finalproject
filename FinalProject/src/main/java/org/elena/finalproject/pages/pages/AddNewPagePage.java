package org.elena.finalproject.pages.pages;

import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AddNewPagePage {

    private static final By PAGE_LOCATOR = By.xpath("//div[contains(text(),'Template')]/..//button[contains(text(),'Single Posts')]");
    private static final By ADD_TITLE_LOCATOR = By.xpath("//span[@data-rich-text-placeholder='Add title']");
    private static final By TEXT_BLOCK_LOCATOR = By.xpath("//span[@data-rich-text-placeholder='Type / to choose a block']");
    private static final By CATEGORIES_LOCATOR = By.xpath("//button[contains(text(),'Categories')][@aria-expanded='false']");
    private static final By CATEGORIES_EXPANDED_LOCATOR = By.xpath("//button[contains(text(),'Categories')][@aria-expanded='true']");
    private static final By ADD_NEW_CATEGORY_LOCATOR = By.xpath("//button[contains(text(),'Add New Category')][@aria-expanded='false']");
    private static final By NEW_CATEGORY_NAME_FIELD_LOCATOR = By.id("id='inspector-text-control-1");
    private static final By ADD_NEW_CATEGORY_BUTTON_LOCATOR = By.xpath("//button[contains(text(),'Add New Category')][@type='submit']");
    private static final By TAGS_LOCATOR = By.xpath("//button[contains(text(),'Tags')][@aria-expanded='false']");
    private static final By TAGS_EXPANDED_LOCATOR = By.xpath("//button[contains(text(),'Tags')][@aria-expanded='true']");
    private static final By ADD_NEW_TAG_FIELD_LOCATOR = By.id("components-form-token-input-1");
    private static final By PUBLISH_BUTTON_LOCATOR = By.xpath("//button[contains(text(),'Publish')]");

    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }


}

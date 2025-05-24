package org.elena.finalproject.pages.posts;

import org.elena.finalproject.models.Tag;
import org.elena.finalproject.pages.BasePage;
import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TagsPage extends BasePage {

    private static final By PAGE_LOCATOR = By.xpath("//div[@class='wrap nosubsub']/h1[contains(text(),'Tags')]");
    private static final By NEW_TAG_NAME_LOCATOR = By.id("tag-name");
    private static final By NEW_TAG_SLUG_LOCATOR = By.id("tag-slug");
    private static final By NEW_TAG_DESCRIPTION_LOCATOR = By.id("tag-description");
    private static final By ADD_NEW_TAG_BUTTON_LOCATOR = By.id("submit");
    private static final By TAG_IS_ADDED_LOCATOR = By.xpath("//div[@id='ajax-response']//*[contains(text(),'Tag added.')]");

    public TagsPage() {
        super();
    }

    @Override
    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }

    public void addNewTag(Tag tag) {
        WebElement nameElement = Browser.waitForElementToBeVisible(NEW_TAG_NAME_LOCATOR);
        nameElement.clear();
        nameElement.sendKeys(tag.getName());
        WebElement slugElement = Browser.waitForElementToBeVisible(NEW_TAG_SLUG_LOCATOR);
        slugElement.clear();
        slugElement.sendKeys(tag.getSlug());
        WebElement descriptionElement = Browser.waitForElementToBeVisible(NEW_TAG_DESCRIPTION_LOCATOR);
        descriptionElement.clear();
        descriptionElement.sendKeys(tag.getDescription());
        WebElement addNewTagButtonElement = Browser.waitForElementToBeClickable(ADD_NEW_TAG_BUTTON_LOCATOR);
        addNewTagButtonElement.click();
    }

    public boolean isTagAdded() {
        WebElement addedTagElement = Browser.waitForElementToBeVisible(TAG_IS_ADDED_LOCATOR);
        return addedTagElement != null && addedTagElement.isDisplayed();
    }

    public void deleteTag(Tag tag) {
        WebElement tagElement = Browser.waitForElementToBeVisible(By.xpath("//a[contains(text(),'" + tag.getName() + "')]"));
        Browser.moveToElement(tagElement).perform();
        WebElement deleteTagElement = Browser.waitForElementToBeClickable(By.xpath("//a[@aria-label='Delete “" + tag.getName() + "”']"));
        Browser.click(deleteTagElement);
        Browser.acceptAlert();
        Browser.waitDeleting(tagElement);
    }

    public boolean isTagDeleted(Tag tag) {
        WebElement deletedTagElement = Browser.waitForElementToBeVisible(By.xpath("//a[contains(text(),'" + tag.getName() + "')]"));
        return deletedTagElement == null;
    }
}

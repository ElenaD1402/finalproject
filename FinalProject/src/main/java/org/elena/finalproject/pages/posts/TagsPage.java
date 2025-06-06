package org.elena.finalproject.pages.posts;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.elena.finalproject.models.Tag;
import org.elena.finalproject.pages.BasePage;
import org.elena.finalproject.webDriver.Browser;
import org.elena.finalproject.webDriver.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TagsPage extends BasePage {

    private static final By PAGE_LOCATOR = By.xpath("//div[@class='wrap nosubsub']/h1[contains(text(),'Tags')]");
    private static final By NEW_TAG_NAME_LOCATOR = By.id("tag-name");
    private static final By NEW_TAG_SLUG_LOCATOR = By.id("tag-slug");
    private static final By NEW_TAG_DESCRIPTION_LOCATOR = By.id("tag-description");
    private static final By ADD_NEW_TAG_BUTTON_LOCATOR = By.id("submit");
    private static final By TAG_IS_ADDED_LOCATOR = By.xpath("//div[@id='ajax-response']//*[contains(text(),'Tag added.')]");
    private static final By SEARCH_TAGS_FIELD_LOCATOR = By.id("tag-search-input");
    private static final By SEARCH_TAGS_BUTTON_LOCATOR = By.id("search-submit");
    private static final By NO_TAGS_FOUND_LOCATOR = By.xpath("//*[contains(text(),'No tags found.')]");

    private Logger logger = Logger.getLogger(this.getClass());

    public TagsPage() {
        super();
    }

    @Step("User opens 'Tags' page")
    public void openTagsPage() {
        Browser.getWebDriver().get(Configuration.getBaseUrl() + "/edit-tags.php?taxonomy=post_tag");
        logger.info("Opening 'Tags' page");
    }

    @Step("Checking whether 'Tags' page is opened")
    @Override
    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }

    @Step("User enters a tag name")
    private void enterTagName(String name) {
        WebElement nameElement = Browser.waitForElementToBeVisible(NEW_TAG_NAME_LOCATOR);
        nameElement.clear();
        nameElement.sendKeys(name);
        logger.info("Entering a tag name");
    }

    @Step("User enters a tag slug")
    private void enterTagSlug(String slug) {
        WebElement slugElement = Browser.waitForElementToBeVisible(NEW_TAG_SLUG_LOCATOR);
        slugElement.clear();
        slugElement.sendKeys(slug);
        logger.info("Entering a tag slug");
    }

    @Step("User enters a tag description")
    private void enterTagDescription(String description) {
        WebElement descriptionElement = Browser.waitForElementToBeVisible(NEW_TAG_DESCRIPTION_LOCATOR);
        descriptionElement.clear();
        descriptionElement.sendKeys(description);
        logger.info("Entering a tag description");
    }

    @Step("User clicks 'Add New Tag' button")
    private void clickAddNewTagButton() {
        WebElement addNewTagButtonElement = Browser.waitForElementToBeClickable(ADD_NEW_TAG_BUTTON_LOCATOR);
        addNewTagButtonElement.click();
        logger.info("Clicking 'Add New Tag' button");
    }

    @Step("User adds a new tag")
    public void addNewTag(Tag tag) {
        enterTagName(tag.getName());
        enterTagSlug(tag.getSlug());
        enterTagDescription(tag.getDescription());
        clickAddNewTagButton();
        logger.info("Adding a new tag");
    }

    @Step("User searches for the tag with name = '{name}'")
    public void searchTag(String name) {
        WebElement searchTagsFieldElement = Browser.waitForElementToBeVisible(SEARCH_TAGS_FIELD_LOCATOR);
        searchTagsFieldElement.clear();
        searchTagsFieldElement.sendKeys(name);
        WebElement searchTagsButtonElement = Browser.waitForElementToBeClickable(SEARCH_TAGS_BUTTON_LOCATOR);
        searchTagsButtonElement.click();
        logger.info("Searching for the tag");
    }

    @Step("Checking whether the tag is added")
    public boolean isTagAdded() {
        WebElement addedTagElement = Browser.waitForElementToBeVisible(TAG_IS_ADDED_LOCATOR);
        return addedTagElement != null && addedTagElement.isDisplayed();
    }

    @Step("User deletes a tag")
    public void deleteTag(Tag tag) {
        WebElement tagElement = Browser.waitForElementToBeVisible(By.xpath("//a[contains(text(),'" + tag.getName() + "')]"));
        Browser.moveToElement(tagElement).perform();
        WebElement deleteTagElement = Browser.waitForElementToBeClickable(By.xpath("//a[@aria-label='Delete “" + tag.getName() + "”']"));
        Browser.click(deleteTagElement);
        Browser.acceptAlert();
        Browser.waitDeleting(deleteTagElement);
        logger.info("Deleting a tag");
    }

    @Step("Checking whether the tag is deleted")
    public boolean isTagDeleted(Tag tag) {
        searchTag(tag.getName());
        WebElement noTagsFoundElement = Browser.waitForElementToBeVisible(NO_TAGS_FOUND_LOCATOR);
        return noTagsFoundElement != null && noTagsFoundElement.isDisplayed();
    }
}

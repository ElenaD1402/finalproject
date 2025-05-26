package org.elena.finalproject.pages.posts;

import org.elena.finalproject.models.Category;
import org.elena.finalproject.models.Post;
import org.elena.finalproject.models.Tag;
import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class AddNewPostPage {

    private static final By PAGE_LOCATOR = By.xpath("//div[contains(text(),'Template')]/..//button[contains(text(),'Single Posts')]");
    private static final By IFRAME_LOCATOR = By.xpath("//iframe[@title='Editor canvas']");
    private static final By ADD_TITLE_LOCATOR = By.xpath("//h1[contains(@class,'wp-block-post-title')]");
    private static final By TEXT_BLOCK_LOCATOR = By.xpath("//div[contains(@class,'wp-block-post-content')]/p");
    private static final By POST_LIST_LOCATOR = By.id("tabs-0-edit-post/document");
    private static final By CATEGORIES_LOCATOR = By.xpath("//button[contains(text(),'Categories')][@aria-expanded='false']");
    private static final By CATEGORIES_EXPANDED_LOCATOR = By.xpath("//button[contains(text(),'Categories')][@aria-expanded='true']");
    private static final By ADD_NEW_CATEGORY_LOCATOR = By.xpath("//button[contains(text(),'Add New Category')][@aria-expanded='false']");
    private static final By NEW_CATEGORY_NAME_FIELD_LOCATOR = By.xpath("//input[contains(@id,'inspector-text-control-')]");
    private static final By ADD_NEW_CATEGORY_BUTTON_LOCATOR = By.xpath("//button[contains(text(),'Add New Category')][@type='submit']");
    private static final By TAGS_LOCATOR = By.xpath("//button[contains(text(),'Tags')][@aria-expanded='false']");
    private static final By TAGS_EXPANDED_LOCATOR = By.xpath("//button[contains(text(),'Tags')][@aria-expanded='true']");
    private static final By ADD_NEW_TAG_FIELD_LOCATOR = By.xpath("//input[contains(@id,'components-form-token-input-')]");
    private static final By TAG_FIELD_REFRESHED_LOCATOR = By.xpath("//div[contains(@class,'components-form-token-field__input-container')]");
    private static final By SAVE_DRAFT_BUTTON_LOCATOR = By.xpath("//button[contains(text(),'Save draft')]");
    private static final By PUBLISH_BUTTON_LOCATOR = By.xpath("//button[contains(text(),'Publish')]");
    private static final By POST_PUBLISH_BUTTON_LOCATOR = By.xpath("//div[@class='editor-post-publish-panel__header-publish-button']");
    private static final By VIEW_POSTS_LOCATOR = By.xpath("//a[@href='edit.php?post_type=post']");

    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }

    public void addNewPostDraft(Post post) {
        WebElement iframeElement = Browser.waitForElementToBePresent(IFRAME_LOCATOR);
        Browser.switchToFrame(iframeElement);
        WebElement titleElement = Browser.waitForElementToBeVisible(ADD_TITLE_LOCATOR);
        titleElement.clear();
        titleElement.sendKeys(post.getTitle());
        Browser.pressKey(Keys.ENTER);
        WebElement textBlockElement = Browser.waitForElementToBeVisible(TEXT_BLOCK_LOCATOR);
        textBlockElement.clear();
        textBlockElement.sendKeys(post.getBlock());
        Browser.switchToDefaultContent();
        WebElement saveDraftButtonElement = Browser.waitForElementToBeClickable(SAVE_DRAFT_BUTTON_LOCATOR);
        saveDraftButtonElement.click();
    }

    public void addNewPost(Post post) {
        WebElement iframeElement = Browser.waitForElementToBePresent(IFRAME_LOCATOR);
        Browser.switchToFrame(iframeElement);
        WebElement titleElement = Browser.waitForElementToBeVisible(ADD_TITLE_LOCATOR);
        titleElement.clear();
        titleElement.sendKeys(post.getTitle());
        Browser.pressKey(Keys.ENTER);
        WebElement textBlockElement = Browser.waitForElementToBeVisible(TEXT_BLOCK_LOCATOR);
        textBlockElement.clear();
        textBlockElement.sendKeys(post.getBlock());
        Browser.switchToDefaultContent();
        WebElement publishButtonElement = Browser.waitForElementToBeClickable(PUBLISH_BUTTON_LOCATOR);
        publishButtonElement.click();
        WebElement postPublishButtonElement = Browser.waitForElementToBeClickable(POST_PUBLISH_BUTTON_LOCATOR);
        postPublishButtonElement.click();
    }

    public void addNewPostWithCategory(Post post, Category category) {
        WebElement iframeElement = Browser.waitForElementToBePresent(IFRAME_LOCATOR);
        Browser.switchToFrame(iframeElement);
        WebElement titleElement = Browser.waitForElementToBeVisible(ADD_TITLE_LOCATOR);
        titleElement.clear();
        titleElement.sendKeys(post.getTitle());
        Browser.pressKey(Keys.ENTER);
        WebElement textBlockElement = Browser.waitForElementToBeVisible(TEXT_BLOCK_LOCATOR);
        textBlockElement.clear();
        textBlockElement.sendKeys(post.getBlock());
        Browser.switchToDefaultContent();
        WebElement postListElement = Browser.waitForElementToBeClickable(POST_LIST_LOCATOR);
        postListElement.click();
        WebElement addNewCategoryElement = Browser.waitForElementToBeClickable(ADD_NEW_CATEGORY_LOCATOR);
        addNewCategoryElement.click();
        WebElement newCategoryNameElement = Browser.waitForElementToBePresent(NEW_CATEGORY_NAME_FIELD_LOCATOR);
        Browser.scroll(newCategoryNameElement);
        newCategoryNameElement.clear();
        newCategoryNameElement.sendKeys(category.getName());
        WebElement addNewCategoryButtonElement = Browser.waitForElementToBeClickable(ADD_NEW_CATEGORY_BUTTON_LOCATOR);
        addNewCategoryButtonElement.click();
        By checkedCategoryLocator = By.xpath("//label[contains(text(),'" + category.getName() + "')]/preceding-sibling::span/input[@checked]");
        Browser.waitExpanding(checkedCategoryLocator);
        WebElement publishButtonElement = Browser.waitForElementToBeClickable(PUBLISH_BUTTON_LOCATOR);
        publishButtonElement.click();
        WebElement postPublishButtonElement = Browser.waitForElementToBeClickable(POST_PUBLISH_BUTTON_LOCATOR);
        postPublishButtonElement.click();
    }

    public void addNewPostWithTag(Post post, Tag tag) {
        WebElement iframeElement = Browser.waitForElementToBePresent(IFRAME_LOCATOR);
        Browser.switchToFrame(iframeElement);
        WebElement titleElement = Browser.waitForElementToBeVisible(ADD_TITLE_LOCATOR);
        titleElement.clear();
        titleElement.sendKeys(post.getTitle());
        Browser.pressKey(Keys.ENTER);
        WebElement textBlockElement = Browser.waitForElementToBeVisible(TEXT_BLOCK_LOCATOR);
        textBlockElement.clear();
        textBlockElement.sendKeys(post.getBlock());
        Browser.switchToDefaultContent();
        WebElement postListElement = Browser.waitForElementToBeClickable(POST_LIST_LOCATOR);
        postListElement.click();
        WebElement newTagNameElement = Browser.waitForElementToBePresent(ADD_NEW_TAG_FIELD_LOCATOR);
        Browser.scroll(newTagNameElement);
        newTagNameElement.clear();
        newTagNameElement.sendKeys(tag.getName());
        Browser.pressKey(Keys.ENTER);
        Browser.waitRefreshing(TAG_FIELD_REFRESHED_LOCATOR);
        WebElement publishButtonElement = Browser.waitForElementToBeClickable(PUBLISH_BUTTON_LOCATOR);
        publishButtonElement.click();
        WebElement postPublishButtonElement = Browser.waitForElementToBeClickable(POST_PUBLISH_BUTTON_LOCATOR);
        postPublishButtonElement.click();
    }

    public void addNewPostWithCategoryAndTag(Post post, Category category, Tag tag) {
        WebElement iframeElement = Browser.waitForElementToBePresent(IFRAME_LOCATOR);
        Browser.switchToFrame(iframeElement);
        WebElement titleElement = Browser.waitForElementToBeVisible(ADD_TITLE_LOCATOR);
        titleElement.clear();
        titleElement.sendKeys(post.getTitle());
        Browser.pressKey(Keys.ENTER);
        WebElement textBlockElement = Browser.waitForElementToBeVisible(TEXT_BLOCK_LOCATOR);
        textBlockElement.clear();
        textBlockElement.sendKeys(post.getBlock());
        Browser.switchToDefaultContent();
        WebElement postListElement = Browser.waitForElementToBeClickable(POST_LIST_LOCATOR);
        postListElement.click();
        WebElement addNewCategoryElement = Browser.waitForElementToBeClickable(ADD_NEW_CATEGORY_LOCATOR);
        addNewCategoryElement.click();
        WebElement newCategoryNameElement = Browser.waitForElementToBePresent(NEW_CATEGORY_NAME_FIELD_LOCATOR);
        Browser.scroll(newCategoryNameElement);
        newCategoryNameElement.clear();
        newCategoryNameElement.sendKeys(category.getName());
        WebElement addNewCategoryButtonElement = Browser.waitForElementToBeClickable(ADD_NEW_CATEGORY_BUTTON_LOCATOR);
        addNewCategoryButtonElement.click();
        By checkedCategoryLocator = By.xpath("//label[contains(text(),'" + category.getName() + "')]/preceding-sibling::span/input[@checked]");
        Browser.waitExpanding(checkedCategoryLocator);
        WebElement newTagNameElement = Browser.waitForElementToBePresent(ADD_NEW_TAG_FIELD_LOCATOR);
        Browser.scroll(newTagNameElement);
        newTagNameElement.clear();
        newTagNameElement.sendKeys(tag.getName());
        Browser.pressKey(Keys.ENTER);
        Browser.waitRefreshing(TAG_FIELD_REFRESHED_LOCATOR);
        WebElement publishButtonElement = Browser.waitForElementToBeClickable(PUBLISH_BUTTON_LOCATOR);
        publishButtonElement.click();
        WebElement postPublishButtonElement = Browser.waitForElementToBeClickable(POST_PUBLISH_BUTTON_LOCATOR);
        postPublishButtonElement.click();
    }

    public void goToPostsPage() {
        WebElement viewPostsElement = Browser.waitForElementToBeClickable(VIEW_POSTS_LOCATOR);
        viewPostsElement.click();
    }
}

package org.elena.finalproject.pages.posts;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.elena.finalproject.models.Category;
import org.elena.finalproject.models.Post;
import org.elena.finalproject.models.Tag;
import org.elena.finalproject.webDriver.Browser;
import org.elena.finalproject.webDriver.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class AddNewPostPage {

    private static final By PAGE_LOCATOR = By.xpath("//div[contains(text(),'Template')]/..//button[contains(text(),'Single Posts')]");
    private static final By IFRAME_LOCATOR = By.xpath("//iframe[@title='Editor canvas']");
    private static final By ADD_TITLE_LOCATOR = By.xpath("//h1[contains(@class,'wp-block-post-title')]");
    private static final By TEXT_BLOCK_LOCATOR = By.xpath("//div[contains(@class,'wp-block-post-content')]/p");
    private static final By ADD_NEW_CATEGORY_LOCATOR = By.xpath("//button[contains(text(),'Add New Category')]");
    private static final By NEW_CATEGORY_NAME_FIELD_LOCATOR = By.xpath("//*[contains(text(),'New Category Name')]/following-sibling::input[contains(@id,'inspector-text-control-')]");
    private static final By ADD_NEW_CATEGORY_BUTTON_LOCATOR = By.xpath("//button[contains(text(),'Add New Category')][@type='submit']");
    private static final By ADD_NEW_TAG_FIELD_LOCATOR = By.xpath("//input[contains(@id,'components-form-token-input-')]");
    private static final By TAG_FIELD_REFRESHED_LOCATOR = By.xpath("//div[contains(@class,'components-form-token-field__input-container')]");
    private static final By SAVE_DRAFT_BUTTON_LOCATOR = By.xpath("//button[contains(text(),'Save draft')]");
    private static final By PUBLISH_BUTTON_LOCATOR = By.xpath("//button[contains(text(),'Publish')]");
    private static final By POST_PUBLISH_BUTTON_LOCATOR = By.xpath("//div[@class='editor-post-publish-panel__header-publish-button']");
    private static final By VIEW_POSTS_LOCATOR = By.xpath("//a[@href='edit.php?post_type=post'][@aria-label='View Posts']");

    private Logger logger = Logger.getLogger(this.getClass());

    @Step("User opens 'Add New Post' page")
    public void openAddNewPostPage() {
        Browser.getWebDriver().get(Configuration.getBaseUrl() + "/post-new.php");
        logger.info("Opening 'Add New Post' page");
    }

    @Step("Checking whether 'Add New Post' page is opened")
    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }

    @Step("User enters a title")
    private void enterTitle(String title) {
        WebElement titleElement = Browser.waitForElementToBeVisible(ADD_TITLE_LOCATOR);
        titleElement.clear();
        titleElement.sendKeys(title);
        logger.info("Entering a title");
    }

    @Step("User enters a text block")
    private void enterTextBlock(String textBlock) {
        WebElement textBlockElement = Browser.waitForElementToBeVisible(TEXT_BLOCK_LOCATOR);
        textBlockElement.clear();
        textBlockElement.sendKeys(textBlock);
        logger.info("Entering a text block");
    }

    @Step("User adds a category")
    private void addCategory(Category category) {
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
        logger.info("Adding a category");
    }

    @Step("User adds a tag")
    private void addTag(Tag tag) {
        WebElement newTagNameElement = Browser.waitForElementToBePresent(ADD_NEW_TAG_FIELD_LOCATOR);
        Browser.scroll(newTagNameElement);
        newTagNameElement.clear();
        newTagNameElement.sendKeys(tag.getName());
        Browser.pressKey(Keys.ENTER);
        Browser.waitRefreshing(TAG_FIELD_REFRESHED_LOCATOR);
        logger.info("Adding a tag");
    }

    @Step("User clicks 'Save draft' button")
    private void clickSaveDraft() {
        WebElement saveDraftButtonElement = Browser.waitForElementToBeClickable(SAVE_DRAFT_BUTTON_LOCATOR);
        saveDraftButtonElement.click();
        logger.info("Clicking 'Save draft' button");
    }

    @Step("User clicks 'Publish' button")
    private void clickPublish() {
        WebElement publishButtonElement = Browser.waitForElementToBeClickable(PUBLISH_BUTTON_LOCATOR);
        publishButtonElement.click();
        WebElement postPublishButtonElement = Browser.waitForElementToBeClickable(POST_PUBLISH_BUTTON_LOCATOR);
        postPublishButtonElement.click();
        logger.info("Clicking 'Publish' button");
    }

    @Step("User creates a new 'Draft' post")
    public void addNewPostDraft(Post post) {
        WebElement iframeElement = Browser.waitForElementToBePresent(IFRAME_LOCATOR);
        Browser.switchToFrame(iframeElement);
        enterTitle(post.getTitle());
        Browser.pressKey(Keys.ENTER);
        enterTextBlock(post.getBlock());
        Browser.switchToDefaultContent();
        clickSaveDraft();
        logger.info("Adding a new 'Draft' post");
    }

    @Step("User creates a new post")
    public void addNewPost(Post post) {
        WebElement iframeElement = Browser.waitForElementToBePresent(IFRAME_LOCATOR);
        Browser.switchToFrame(iframeElement);
        enterTitle(post.getTitle());
        Browser.pressKey(Keys.ENTER);
        enterTextBlock(post.getBlock());
        Browser.switchToDefaultContent();
        clickPublish();
        logger.info("Adding a new post");
    }

    @Step("User creates a new post with category")
    public void addNewPostWithCategory(Post post, Category category) {
        addCategory(category);
        addNewPost(post);
        logger.info("Adding a new post with category");
    }

    @Step("User creates a new post with tag")
    public void addNewPostWithTag(Post post, Tag tag) {
        addTag(tag);
        addNewPost(post);
        logger.info("Adding a new post with tag");
    }

    @Step("User creates a new post with category and tag")
    public void addNewPostWithCategoryAndTag(Post post, Category category, Tag tag) {
        addCategory(category);
        addTag(tag);
        addNewPost(post);
        logger.info("Adding a new post with category and tag");
    }

    @Step("User clicks 'View Posts'")
    public void goToPostsPage() {
        WebElement viewPostsElement = Browser.waitForElementToBeClickable(VIEW_POSTS_LOCATOR);
        viewPostsElement.click();
        logger.info("Clicking 'View Posts'. Opening 'All Posts' page");
    }
}

package org.elena.finalproject.pages.posts;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.elena.finalproject.models.Category;
import org.elena.finalproject.models.Post;
import org.elena.finalproject.models.Tag;
import org.elena.finalproject.pages.BasePage;
import org.elena.finalproject.webDriver.Browser;
import org.elena.finalproject.webDriver.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PostsPage extends BasePage {

    private static final By PAGE_LOCATOR = By.xpath("//div[@class='wrap']/h1[contains(text(),'Posts')]");
    private static final By SEARCH_POSTS_FIELD_LOCATOR = By.id("post-search-input");
    private static final By SEARCH_POSTS_BUTTON_LOCATOR = By.id("search-submit");
    private static final By NO_POSTS_FOUND_LOCATOR = By.xpath("//*[contains(text(),'No posts found.')]");

    private Logger logger = Logger.getLogger(this.getClass());

    public PostsPage() {
        super();
    }

    @Step("User opens 'Posts' page")
    public void openPostsPage() {
        Browser.getWebDriver().get(Configuration.getBaseUrl() + "/edit.php");
        logger.info("Opening 'Posts' page");
    }

    @Step("Checking whether 'Posts' page is opened")
    @Override
    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }

    @Step("User searches for the post with title = '{title}'")
    private void searchPost(String title) {
        WebElement searchPostsFieldElement = Browser.waitForElementToBeVisible(SEARCH_POSTS_FIELD_LOCATOR);
        searchPostsFieldElement.clear();
        searchPostsFieldElement.sendKeys(title);
        WebElement searchPostsButtonElement = Browser.waitForElementToBeClickable(SEARCH_POSTS_BUTTON_LOCATOR);
        searchPostsButtonElement.click();
        logger.info("Searching for the post");
    }

    @Step("Checking whether the 'Draft' post is added")
    public boolean isDraftPostAdded(Post post) {
        searchPost(post.getTitle());
        WebElement addedDraftPostElement = Browser.waitForElementToBeVisible(By.xpath("//a[contains(text(),'" + post.getTitle()
                + "')]/following-sibling::span[contains(text(),'Draft')]/../../..//*[contains(text(),'Uncategorized')]/../..//*[contains(text(),'No tags')]/../..//*[contains(text(),'Last Modified')]"));
        return addedDraftPostElement != null && addedDraftPostElement.isDisplayed();
    }

    @Step("Checking whether the post without category and tag is added")
    public boolean isPostAdded(Post post) {
        searchPost(post.getTitle());
        WebElement addedPostElement = Browser.waitForElementToBeVisible(By.xpath("//a[contains(text(),'" + post.getTitle()
                + "')]/../../..//*[contains(text(),'Uncategorized')]/../..//*[contains(text(),'No tags')]/../..//*[contains(text(),'Published')]"));
        return addedPostElement != null && addedPostElement.isDisplayed();
    }

    @Step("Checking whether the post with category is added")
    public boolean isPostWithCategoryAdded(Post post, Category category) {
        searchPost(post.getTitle());
        WebElement addedPostElement = Browser.waitForElementToBeVisible(By.xpath("//a[contains(text(),'" + post.getTitle()
                + "')]/../../..//*[contains(text(),'" + category.getName() + "')]/../..//*[contains(text(),'No tags')]/../..//*[contains(text(),'Published')]"));
        return addedPostElement != null && addedPostElement.isDisplayed();
    }

    @Step("Checking whether the post with tag is added")
    public boolean isPostWithTagAdded(Post post, Tag tag) {
        searchPost(post.getTitle());
        WebElement addedPostElement = Browser.waitForElementToBeVisible(By.xpath("//a[contains(text(),'" + post.getTitle()
                + "')]/../../..//*[contains(text(),'Uncategorized')]/../..//*[contains(text(),'" + tag.getName() + "')]/../..//*[contains(text(),'Published')]"));
        return addedPostElement != null && addedPostElement.isDisplayed();
    }

    @Step("Checking whether the post with category and tag is added")
    public boolean isPostWithCategoryAndTagAdded(Post post, Category category, Tag tag) {
        searchPost(post.getTitle());
        WebElement addedPostElement = Browser.waitForElementToBeVisible(By.xpath("//a[contains(text(),'" + post.getTitle()
                + "')]/../../..//*[contains(text(),'" + category.getName() + "')]/../..//*[contains(text(),'" + tag.getName() + "')]/../..//*[contains(text(),'Published')]"));
        return addedPostElement != null && addedPostElement.isDisplayed();
    }

    @Step("User deletes a post")
    public void deletePost(Post post) {
        searchPost(post.getTitle());
        WebElement postElement = Browser.waitForElementToBeVisible(By.xpath("//a[contains(text(),'" + post.getTitle() + "')]"));
        Browser.moveToElement(postElement).perform();
        WebElement deletePostElement = Browser.waitForElementToBeClickable(By.xpath("//a[@aria-label='Move “" + post.getTitle() + "” to the Trash']"));
        Browser.click(deletePostElement);
        logger.info("Deleting a post");
    }

    @Step("Checking whether the post is deleted")
    public boolean isPostDeleted(Post post) {
        searchPost(post.getTitle());
        WebElement noPostsFoundElement = Browser.waitForElementToBeVisible(NO_POSTS_FOUND_LOCATOR);
        return noPostsFoundElement != null && noPostsFoundElement.isDisplayed();
    }
}

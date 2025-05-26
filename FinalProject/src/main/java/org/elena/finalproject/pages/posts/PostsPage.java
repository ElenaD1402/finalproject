package org.elena.finalproject.pages.posts;

import org.elena.finalproject.models.Category;
import org.elena.finalproject.models.Post;
import org.elena.finalproject.models.Tag;
import org.elena.finalproject.pages.BasePage;
import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PostsPage extends BasePage {

    private static final By PAGE_LOCATOR = By.xpath("//div[@class='wrap']/h1[contains(text(),'Posts')]");

    public PostsPage() {
        super();
    }

    @Override
    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }

    public boolean isDraftPostAdded(Post post) {
        WebElement addedDraftPostElement = Browser.waitForElementToBeVisible(By.xpath("//a[contains(text(),'" + post.getTitle()
                + "')]/following-sibling::span[contains(text(),'Draft')]/../.."));
        return addedDraftPostElement != null && addedDraftPostElement.isDisplayed();
    }

    public boolean isPostAdded(Post post) {
        WebElement addedPostElement = Browser.waitForElementToBeVisible(By.xpath("//a[contains(text(),'" + post.getTitle() + "')]"));
        return addedPostElement != null && addedPostElement.isDisplayed();
    }

    public boolean isPostWithCategoryAdded(Post post, Category category) {
        WebElement addedPostElement = Browser.waitForElementToBeVisible(By.xpath("//a[contains(text(),'" + post.getTitle()
                + "')]/../../..//a[contains(text(),'" + category.getName() + "')]"));
        return addedPostElement != null && addedPostElement.isDisplayed();
    }

    public boolean isPostWithTagAdded(Post post, Tag tag) {
        WebElement addedPostElement = Browser.waitForElementToBeVisible(By.xpath("//a[contains(text(),'" + post.getTitle()
                + "')]/../../..//a[contains(text(),'" + tag.getName() + "')]"));
        return addedPostElement != null && addedPostElement.isDisplayed();
    }

    public boolean isPostWithCategoryAndTagAdded(Post post, Category category, Tag tag) {
        WebElement addedPostElement = Browser.waitForElementToBeVisible(By.xpath("//a[contains(text(),'" + post.getTitle()
                + "')]/../../..//a[contains(text(),'" + category.getName() + "')]/../../..//a[contains(text(),'" + tag.getName() + "')]"));
        return addedPostElement != null && addedPostElement.isDisplayed();
    }

    public void deletePost(Post post) {
        WebElement postElement = Browser.waitForElementToBeVisible(By.xpath("//a[contains(text(),'" + post.getTitle() + "')]"));
        Browser.moveToElement(postElement).perform();
        WebElement deletePostElement = Browser.waitForElementToBeClickable(By.xpath("//a[@aria-label='Move “" + post.getTitle() + "” to the Trash']"));
        Browser.click(deletePostElement);
    }

    public boolean isPostDeleted(Post post) {
        WebElement deletedPostElement = Browser.waitForElementToBeVisible(By.xpath("//a[contains(text(),'" + post.getTitle() + "')]"));
        return deletedPostElement == null;
    }
}
